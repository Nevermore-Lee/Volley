package notification.nevermore.io.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/21.
 */
public class MusicAdapter extends BaseAdapter<Music> {


    private final RequestQueue queue;
    private final ImageLoader imageLoader;

    public MusicAdapter(Context context, List<Music> data) {
        super(context, data);
        queue = Volley.newRequestQueue(context);
        imageLoader =new ImageLoader(queue, new LruImageCache());
    }
//    用SoftReference不可控，当图片数量太多时，有可能出现OOM，这是早期的处理方式，现在推荐使用LruImageCache
    class MyImageCache implements ImageLoader.ImageCache{
        private Map<String,SoftReference<Bitmap>> cache = new HashMap<>();
        @Override
        public Bitmap getBitmap(String url) {
            SoftReference<Bitmap>ref = cache.get(url);
            if(ref!=null){
                return ref.get();
            }else {
                return null;
            }
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            cache.put(url,new SoftReference<Bitmap>(bitmap));
        }
    }
//    LruImageCache

    class LruImageCache implements ImageLoader.ImageCache{
        private LruCache<String,Bitmap>cache;

        public LruImageCache(){
            int maxSize = 10*1024*1024;
            cache = new LruCache<String,Bitmap>(maxSize){
                protected int sizeOf(String key, Bitmap bitmap){
                    return  bitmap.getRowBytes()*bitmap.getHeight();
                }
            };

        }

        @Override
        public Bitmap getBitmap(String url) {
            return cache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            cache.put(url,bitmap);
        }
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Music music = getData().get(position);

        ViewHolder holder;

        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.item_lv_music, null);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvSinger = (TextView) convertView.findViewById(R.id.tvSinger);
            holder.ivPic = (ImageView) convertView.findViewById(R.id.ivPic);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(music.getTitle());
        holder.tvSinger.setText(music.getAuthor());
//        显示图片
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.ivPic,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        imageLoader.get(music.getPic_small(),listener);

        return convertView;
    }


    class ViewHolder {
        ImageView ivPic;
        TextView tvName;
        TextView tvSinger;
    }

}
