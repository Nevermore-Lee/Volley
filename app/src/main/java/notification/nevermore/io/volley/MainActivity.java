package notification.nevermore.io.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
    private List<Music>musics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.init();
        queue = Volley.newRequestQueue(this);
//        加载热歌榜音乐
        loadHotMusciList();
    }

//    加载热歌榜
    private void loadHotMusciList() {
        String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.billboard.billList&format=json&type=2&offset=0&size=50";
        StringRequest req = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                把response解析成QueryRequest对象
                Gson gson = new Gson();
                QueryResult result = gson.fromJson(response,QueryResult.class);
                Logger.i("lee",result);
                Log.i("lee","解析成功"+result);
                musics = result.getSong_list();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.e("error");
            }
        });
        queue.add(req);
    }
}
