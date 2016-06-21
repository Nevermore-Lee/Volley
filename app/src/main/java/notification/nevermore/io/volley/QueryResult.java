package notification.nevermore.io.volley;

import java.util.List;

/**
 * Created by Administrator on 2016/6/21.
 */
public class QueryResult {

    private List<Music> song_list;
    private int error_code;
    private Billboard billboard;

    public QueryResult() {
        // TODO Auto-generated constructor stub
    }

    public QueryResult(List<Music> song_list, int error_code,
                       Billboard billboard) {
        super();
        this.song_list = song_list;
        this.error_code = error_code;
        this.billboard = billboard;
    }

    public List<Music> getSong_list() {
        return song_list;
    }

    public void setSong_list(List<Music> song_list) {
        this.song_list = song_list;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public Billboard getBillboard() {
        return billboard;
    }

    public void setBillboard(Billboard billboard) {
        this.billboard = billboard;
    }


}
