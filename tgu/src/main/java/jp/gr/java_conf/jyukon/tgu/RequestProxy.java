package jp.gr.java_conf.jyukon.tgu;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import jp.gr.java_conf.jyukon.tgu.model.ItemResponse;

public class RequestProxy {
    private RequestQueue mRequestQueue;

    RequestProxy(Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public void getItem(int itemId, Response.Listener<ItemResponse> listener) {
        String url = new Uri.Builder()
                .scheme("https")
                .authority("tab.do")
                .path(String.format("api/1/items/%d.json", itemId))
                .build().toString();
        mRequestQueue.add(new GsonRequest(url, ItemResponse.class, null, listener,
                new Response.ErrorListener() {
                    @Override public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                    }
                }));
    }

    public void getImage(String imageUrl, Response.Listener<Bitmap> listener) {
        mRequestQueue.add(new ImageRequest(imageUrl, listener, 0, 0, Bitmap.Config.ARGB_8888,
                new Response.ErrorListener() {
                    @Override public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                    }
                }));
    }
}