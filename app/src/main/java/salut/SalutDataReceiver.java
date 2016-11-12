package salut;

import android.app.Activity;
import android.content.Context;

import salut.Callbacks.SalutDataCallback;


public class SalutDataReceiver {

    protected SalutDataCallback dataCallback;
    protected Context context;
    protected Activity activity;

    public SalutDataReceiver(Activity activity, SalutDataCallback dataCallback) {
        this.dataCallback = dataCallback;
        this.context = activity.getApplicationContext();
        this.activity = activity;
    }
}
