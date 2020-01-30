package com.flutter_webview_plugin.helper;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebResourceResponse;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/***
 * Tool Data Package exchange
 */
public class DataHelper extends BaseHelper{
    private Map<String, String> mMap;
    public DataHelper() {
        mMap = new HashMap<>();
        initData();
    }

    private void initData() {
        String commonDir = "";
//        mMap.put("https://www.majsoul.com/1/v0.6.206.w/code.js",
//                commonDir+"code.js");
//        mMap.put("https://www.majsoul.com/1/v0.6.3.w/res/atlas/myres/get_character.png",
//                commonDir+"get_character.png");
//
//
//        mMap.put("https://www.majsoul.com/1/v0.6.74.w/res/atlas/myres/lobby.png",
//                commonDir+"lobby.png");
//        mMap.put("https://www.majsoul.com/1/v0.6.74.w/res/atlas/myres/lobby1.png",
//                commonDir+"lobby1.png");
//        mMap.put("https://www.majsoul.com/1/v0.6.74.w/res/atlas/myres/lobby2.png",
//                commonDir+"lobby2.png");
//        mMap.put("https://www.majsoul.com/1/v0.6.74.w/res/atlas/myres/lobby3.png",
//                commonDir+"lobby3.png");
//        mMap.put("https://www.majsoul.com/1/v0.6.74.w/res/atlas/myres/lobby4.png",
//                commonDir+"lobby4.png");
//
//        mMap.put("https://www.majsoul.com/1/v0.6.39.w/res/atlas/myres/yueka.png",
//                commonDir+"yueka.png");
//
//        mMap.put("https://www.majsoul.com/1/v0.6.129.w/res/atlas/myres/activity_fanpai.png",
//                commonDir+"activity_fanpai.png");
//
//        mMap.put("https://www.majsoul.com/1/v0.6.188.w/res/atlas/myres/activity_sign.png",
//                commonDir+"activity_sign.png");
//
//        mMap.put("https://www.majsoul.com/1/v0.6.204.w/res/config/lqc.lqbin",
//                commonDir+"lqc");
//
//        mMap.put("https://www.majsoul.com/1/v0.6.1.w/audio/music/lobby.mp3",
//                commonDir+"lobby.mp3");



    }
    public boolean hasLocalResource(String url) {
        return mMap.containsKey(url);
    }
    public WebResourceResponse getReplacedWebResourceResponse(Context context, String url) {
        String localResourcePath = mMap.get(url);
        if (TextUtils.isEmpty(localResourcePath)) {
            return null;
        }
        InputStream is = null;
        try {
            AssetManager ass = context.getApplicationContext().getAssets();
            is = ass.open(localResourcePath);
        } catch (Exception e) {
            Log.e("DataHelperE","e=>"+e);
            e.printStackTrace();
            return null;
        }
        String mimeType;
        if (url.contains(".js")) {
            mimeType = "text/js";
        } else if (url.contains(".css")) {
            mimeType = "text/css";
        } else if (url.contains(".jpg")) {
            mimeType = "image/jpeg";
        } else  if (url.contains(".png")) {
            mimeType = "image/png";
        } else if(url.contains(".mp3")){
            mimeType = "audio/x-mpeg";
        }else{
            ///二进数据
            mimeType = "application/octet-stream";
        }
        WebResourceResponse response = new WebResourceResponse(mimeType, "utf-8", is);
        return response;
    }
}