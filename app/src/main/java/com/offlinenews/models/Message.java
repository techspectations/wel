package com.offlinenews.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by idea on 12-11-2016.
 */

@JsonObject
public class Message {

    /*
     * Annotate a field that you want sent with the @JsonField marker.
     */
    @JsonField
    public String newsDetailVo;

    /*
     * Note that since this field isn't annotated as a
     * @JsonField, LoganSquare will ignore it when parsing
     * and serializing this class.
     */
    public int nonJsonField;
}
