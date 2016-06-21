package notification.nevermore.io.volley;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
	/**
	 * 上下文对象
	 */
	private Context context;
	/**
	 * 数据
	 */
	private List<T> data;
	/**
	 * LayoutInflater
	 */
	private LayoutInflater inflater;

	/**
	 * 构造方法
	 *
	 * @param context
	 *            Context对象，不允许为null
	 * @param data
	 *            数据
	 */
	public BaseAdapter(Context context, List<T> data) {
		super();
		setContext(context);
		setData(data);
		setLayoutInflater();
	}

	/**
	 * 设置Context对象
	 *
	 * @param context
	 *            Context对象，不允许为null
	 */
	public final void setContext(Context context) {
		if (context == null) {
			throw new IllegalArgumentException("参数Context不允许为null！");
		}
		this.context = context;
	}

	/**
	 * 设置数据
	 *
	 * @param data
	 *            数据
	 */
	public final void setData(List<T> data) {
		if (data == null) {
			data = new ArrayList<T>();
			Log.w("lee", "Adapter使用的数据为null，已创建为新的ArrayList，推荐检查参数！");
		}
		this.data = data;
	}

	/**
	 * 获取Context对象
	 *
	 * @return Context对象
	 */
	public final Context getContext() {
		return context;
	}

	/**
	 * 获取数据的List集合
	 *
	 * @return 数据的List集合
	 */
	public final List<T> getData() {
		return data;
	}

	/**
	 * 设置LayoutInflater的值
	 */
	private void setLayoutInflater() {
		inflater = LayoutInflater.from(context);
	}

	/**
	 * 获取LayoutInflater对象
	 *
	 * @return LayoutInflater对象
	 */
	public final LayoutInflater getLayoutInflater() {
		return inflater;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// （无视）
		return null;
	}

	@Override
	public long getItemId(int position) {
		// （无视）
		return 0;
	}

}
