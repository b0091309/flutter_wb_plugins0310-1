package com.flutter_webview_plugin.helper;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebResourceResponse;

import com.flutter_webview_plugin.FlutterWebviewPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ResAutoCacheHelper extends BaseHelper{

    private Context context;
    public ResAutoCacheHelper(Context cxt) {
        this.context = cxt;
    }

    public boolean hasLocalResource(String url) {
        return getDataFile(url).exists();
    }
    private File getDataFile(String url){
        String[] arr = url.split("/");
        String filePath =  arr[arr.length-2];
        String fileName =  arr[arr.length-1];

        File f = new File(Environment.getExternalStorageDirectory() + FILE_SAVING_PATH + filePath+"/"+fileName);
        return f;
    }

    private FileInputStream getSteamFromFile(String url){
        try {
            File f = getDataFile(url);
            if(f.exists()){
                return new FileInputStream(f);
            }
            throw new FileNotFoundException();
        }catch (Exception ex){
            Log.e("getSteamFromFile","FILE NOT FOUND=>" + url);
            return null;
        }
    }
    public WebResourceResponse getReplacedWebResourceResponse(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        InputStream is = null;
        try {
            is = getSteamFromFile(url);
        } catch (Exception e) {
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
