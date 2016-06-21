package notification.nevermore.io.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
    private List<Music>musics = new ArrayList<Music>();
    private ListView lv;
    private MusicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
//        加载热歌榜音乐
        loadHotMusicList();
//        显示
        setViews();
    }

    private void setAdapter() {
        adapter = new MusicAdapter(this,musics);
        lv.setAdapter(adapter);
    }

    private void setViews() {
        lv = (ListView) findViewById(R.id.lv_musics);
    }

    //    加载热歌榜
    private void loadHotMusicList() {
        String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.billboard.billList&format=json&type=2&offset=0&size=50";
        StringRequest req = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                把response解析成QueryRequest对象
                Gson gson = new Gson();
                QueryResult result = gson.fromJson(response,QueryResult.class);
                Log.i("lee","解析成功"+result);
                musics = result.getSong_list();
                setAdapter();
                Log.i("lee",musics.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("lee","error"+error.getMessage());
            }
        });
        queue.add(req);
    }
}
