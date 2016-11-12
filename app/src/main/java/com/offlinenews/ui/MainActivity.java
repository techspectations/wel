package com.offlinenews.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bluelinelabs.logansquare.LoganSquare;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.offlinenews.R;
import com.offlinenews.adapters.NewsAdapter;
import com.offlinenews.managers.APIManager;
import com.offlinenews.models.Message;
import com.offlinenews.models.NewsDetailVo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import salut.Callbacks.SalutCallback;
import salut.Callbacks.SalutDataCallback;
import salut.Callbacks.SalutDeviceCallback;
import salut.Salut;
import salut.SalutDataReceiver;
import salut.SalutDevice;
import salut.SalutServiceData;

/**
 * Created by idea on 12-11-2016.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SalutDataCallback {

    private final String TAG = "MainActivity";
    private List<NewsDetailVo> newsList;
    private Salut meshNetwork;
    private boolean isSender = false;
    private Handler handler;
    private RecyclerView newsRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initNewsRv();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        findViewById(R.id.host).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initHost();
            }
        });

        findViewById(R.id.client).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initClient();
            }
        });
    }

    private void initP2p(String instanceName) {
        SalutDataReceiver dataReceiver = new SalutDataReceiver(this, this);
        SalutServiceData serviceData = new SalutServiceData("SERVICE_NAME", 50489, instanceName);

        meshNetwork = new Salut(dataReceiver, serviceData, new SalutCallback() {
            @Override
            public void call() {
                Log.e(TAG, "Sorry, but this device does not support WiFi Direct.");
            }
        });
    }

    private void initHost() {
        isSender = true;
        //Host
        initP2p("HOST");
        meshNetwork.startNetworkService(new SalutDeviceCallback() {
            @Override
            public void call(SalutDevice device) {
                Log.d(TAG, device.readableName + " has connected!");
            }
        });
        sendMsg();
    }

    private void initClient() {
        isSender = false;
        //Client
        initP2p("CLIENT");
        meshNetwork.discoverNetworkServices(new SalutCallback() {
            @Override
            public void call() {
                Log.d(TAG, "All I know is that a device has connected.");
                Log.d(TAG, "MyNetwork: " + meshNetwork);

                if (meshNetwork.foundDevices != null && meshNetwork.foundDevices.size() > 0) {
                    for (int i = 0; i < meshNetwork.foundDevices.size(); i++) {
                        meshNetwork.registerWithHost(meshNetwork.foundDevices.get(i), new SalutCallback() {
                            @Override
                            public void call() {
                                Log.d(TAG, "We're now registered.");
                            }
                        }, new SalutCallback() {
                            @Override
                            public void call() {
                                Log.d(TAG, "We failed to register.");
                            }
                        });
                    }
                }
            }
        }, true);
    }

    private void sendMsg() {
        if (isSender) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (APIManager.newInstance().getNewsList() != null &&
                            APIManager.newInstance().getNewsList().size() > 0) {
                        String newsDetailsList = convertToString(APIManager.newInstance().getNewsList());

                        if (meshNetwork != null) {
                            Log.d(TAG, "Sending data.");
                            meshNetwork.sendToAllDevices(newsDetailsList, new SalutCallback() {
                                @Override
                                public void call() {
                                    Log.d(TAG, "Oh no! The data failed to send.");
                                }
                            });
                            Toast.makeText(MainActivity.this, "Data Sent", Toast.LENGTH_LONG).show();
                        }
                    }
                    handler.postDelayed(this, 9000);
                }
            }, 9000);
        }
    }

    private String convertToString(List<NewsDetailVo> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    private void initNewsRv() {
        newsList = new ArrayList<>();
        newsRv = (RecyclerView) findViewById(R.id.news_rv);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        newsRv.setLayoutManager(mLayoutManager);
        NewsAdapter newsAdapter = new NewsAdapter(this, newsList);
        newsRv.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();

        // Todo: Check if Internet is there.
        boolean isInternetConnected = true;
        if (isInternetConnected) {
            APIManager.newInstance().fetchNews(new Callback() {
                @Override
                public void success(Object o, Response response) {
                    if (o != null) {
                        newsList = ((List<NewsDetailVo>) o);
                        NewsAdapter newsAdapter = new NewsAdapter(MainActivity.this, newsList);
                        newsRv.setAdapter(newsAdapter);
                        newsAdapter.notifyDataSetChanged();
                        newsAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "Return object null",
                                Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(MainActivity.this, "Failed to fetch news article: " + error.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        } else {
            boolean isNewsUpdated = true;
            if (isNewsUpdated) {
                // Start Network Service to find CLIENT to share news
            } else {
                // Start Discovery Service to find HOST for news
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onDataReceived(Object data) {
        Toast.makeText(MainActivity.this, "Data received", Toast.LENGTH_LONG).show();
        Log.d(TAG, "Received network data.");
        try {
            String newMessage = LoganSquare.parse((String) data, String.class);
            Log.d(TAG, "Converting to string" + newMessage);  //See you on the other side!

            Type listType = new TypeToken<List<NewsDetailVo>>() {
            }.getType();
            String json_str = newMessage;
            List newsList = null;
            try {
                newsList = new Gson().fromJson(json_str, listType);

                APIManager.newInstance().setNewsList(newsList);
                // Populate from this list
                NewsAdapter newsAdapter = new NewsAdapter(MainActivity.this, newsList);
                newsRv.setAdapter(newsAdapter);
                newsAdapter.notifyDataSetChanged();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);

        if (isSender) {
            meshNetwork.stopNetworkService(true);
        } else {
            try {
                meshNetwork.unregisterClient(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
