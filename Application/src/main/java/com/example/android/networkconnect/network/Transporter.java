//package com.example.android.networkconnect.network;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class Transporter {
//    private static String TAG = Transporter.class.getSimpleName();
//
//    public static JSONObject fetchRickAndMorty (String apiUrl) throws IOException, JSONException {
//        OkHttpClient client = new OkHttpClient();
//        Request getRequest = new Request.Builder()
//                .url(apiUrl)
//                .header("content-type", "application/json; charset = UTF-8")
//                .build();
//        Response response = client.newCall(getRequest).execute();
//        JSONObject object = new JSONObject(response.body().string());
//        return object;
//    }
//}
