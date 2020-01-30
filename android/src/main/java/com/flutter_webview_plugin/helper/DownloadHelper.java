package com.flutter_webview_plugin.helper;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.RequiresApi;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DownloadHelper extends BaseHelper {
    public static final String RES_VERSION = "1.0.0";

    Context context;

    public DownloadHelper(Context ctx) {
        this.context = ctx;

        String folderPath = Environment.getExternalStorageDirectory() + FILE_SAVING_PATH;

        File f = new File(folderPath);
        if(!f.exists()){
            f.mkdirs();
        }
    }

    /***
     * 写个下载任务
     */
    private final OkHttpClient client = new OkHttpClient();

    public void downloadFile(String fileUrl, final String folderName, final String intoFileName, boolean isPermissionOn) {
        Request request = new Request.Builder()
                .url(fileUrl)
                .build();
        if(!isPermissionOn){
            /***
             * 读写权限尚未开
             */
            Log.e("DownloadHelper","SD卡权限尚未开启");
            return ;
        }
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call call, Response response) {
                File file = null;
                        String url = response.request().url().toString();
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);


                    String folderPath = Environment.getExternalStorageDirectory() + FILE_SAVING_PATH + folderName;

                    file = new File(folderPath);
                    if(!file.exists()){
                        file.mkdirs();
                    }

                    file = new File(folderPath +"/"+ intoFileName);
                    if(!file.exists()){
                        file.createNewFile();
                    }


                    File nF = new File(folderPath +"/"+ intoFileName);
                    if (nF.exists()) {
                        FileOutputStream fos = new FileOutputStream(nF);
                        fos.write(response.body().bytes());
                        fos.close();

                    }else{
                        Log.e("downloadFile","FILE NOT EXIST");
                    }

                } catch (Exception e) {
                    Log.e("downloadFile","Response Failed"+e);
                    String folderPath = Environment.getExternalStorageDirectory() + FILE_SAVING_PATH + folderName;
                    try {
                        file = new File(folderPath);
                        file.delete();
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        });
    }

}
