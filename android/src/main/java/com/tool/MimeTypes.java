package com.tool;
import android.webkit.MimeTypeMap;

import java.util.HashMap;
import java.util.Map;

/***
 *
 *
 Intent intent = new Intent();
 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
 intent.setAction(android.content.Intent.ACTION_VIEW);
 String type = MimeTypesTools.getMimeType(context, path);
 File file = new File(path);
 //添加这一句表示对目标应用临时授权该Uri所代表的文件
 intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
 if (file != null) {
 Uri uri = null;
 if (Build.VERSION.SDK_INT >= 24) {

 uri = FileProvider.getUriForFile(context, context.getPackageName()+".fileprovider", file);
 } else {
 uri = Uri.fromFile(file);
 }
 if (uri != null) {
 intent.setDataAndType(uri, type);
 context.startActivity(intent);
 }
 }
 } catch (Exception e) {
 e.printStackTrace();
 ToastUtil.show("手机上无可打开此格式的app");

 }

 **/
//public class MimeTypes {
//
//    private Map<String, String> mMimeTypes;
//    private Map<String, Integer> mIcons;
//
//    public MimeTypes() {
//        mMimeTypes = new HashMap<String,String>();
//        mIcons = new HashMap<String,Integer>();
//    }
//
//    /* I think the type and extension names are switched (type contains .png, extension contains x/y),
//     * but maybe it's on purpouse, so I won't change it.
//     */
//    public void put(String type, String extension, int icon){
//        put(type, extension);
//        mIcons.put(extension, icon);
//    }
//
//    public void put(String type, String extension) {
//        // Convert extensions to lower case letters for easier comparison
//        extension = extension.toLowerCase();
//
//        mMimeTypes.put(type, extension);
//    }
//
//    public String getMimeType(String filename) {
//
//        String extension =MimeTypesTools.getExtension(filename);
//
//        // Let's check the official map first. Webkit has a nice extension-to-MIME map.
//        // Be sure to remove the first character from the extension, which is the "." character.
//        if (extension.length() > 0) {
//            String webkitMimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.substring(1));
//
//            if (webkitMimeType != null) {
//                // Found one. Let's take it!
//                return webkitMimeType;
//            }
//        }
//
//        // Convert extensions to lower case letters for easier comparison
//        extension = extension.toLowerCase();
//
//        String mimetype = mMimeTypes.get(extension);
//
//        if(mimetype==null) {
//            mimetype = "*/*";
//        }
//
//        return mimetype;
//    }
//
//    public int getIcon(String mimetype){
//        Integer iconResId = mIcons.get(mimetype);
//        if(iconResId == null) {
//            return 0; // Invalid identifier
//        }
//        return iconResId;
//    }
//}