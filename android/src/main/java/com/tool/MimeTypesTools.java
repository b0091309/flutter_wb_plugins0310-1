//
//package com.tool;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.content.res.XmlResourceParser;
//import android.text.TextUtils;
//import com.geely.mars.sqldemo.R;
//import com.flutter_webview_plugin.R;
//
//import org.xmlpull.v1.XmlPullParserException;
//import java.io.IOException;
//public class MimeTypesTools {
//
//    private static boolean hasLoadMimeType = false;
//
//    /**
//     *
//     * @param context
//     * @param fileName
//     * @return 获取文件mime类型
//     */
//    public static String getMimeType(Context context, String fileName) {
//        if (!TextUtils.isEmpty(fileName)) {
//            fileName = fileName.toLowerCase();
//
//            MimeTypes.java mimeTypes = getMimeTypes(context);
//            String extension = getSuffix(fileName);
//            return mimeTypes.getMimeType(extension);
//        }
//
//        return null;
//    }
//
//    /**
//     * 获取文件格式的后缀名  如 .jpg
//     * @param uri 指定文件
//     * @return 如 .jpg
//     */
//    public static String getSuffix(String uri) {
//        if (uri == null) {
//            return null;
//        }
//
//        int dot = uri.lastIndexOf(".");
//        if (dot >= 0) {
//            return uri.substring(dot);
//        } else {
//            // No extension.
//            return "";
//        }
//    }
//
//    private static MimeTypes.java getMimeTypes(Context context) {
//        return loadMimeTypes(context);
//    }
//
//    /**
//     * 解析xml 文件
//     * @param context
//     * @return
//     */
//    private static MimeTypes.java loadMimeTypes(Context context) {
//        MimeTypeParser parser = null;
//        XmlResourceParser xmlResourceParser = null;
//        if (!hasLoadMimeType) {
//            try {
//                parser = new MimeTypeParser(context, context.getPackageName());
//                xmlResourceParser = context.getResources().getXml(R.xml.mimetypes);
//
//                return parser.fromXmlResource(xmlResourceParser);
//            } catch (XmlPullParserException e) {
//                e.printStackTrace();
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            hasLoadMimeType = true;
//        }
//
//        return null;
//    }
//}
