package com.onekey.leftfragment;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.onekey.common.PhotoView;
import com.onekey.zhougongjiemeng.R;

public class FolderImageAdapter extends BaseAdapter {

	private ImageLoader mLoader;
	private Activity mActivity;
	private int mnLayoutwidth;
	private int mnLayoutheigth;
	private List<String> mUrlList = new ArrayList<String>();
	private AdapterInterface mAdapterInterface;
	
	public void addData(String d) {
		mUrlList.add(d);
	}

	public FolderImageAdapter(Activity Activity) {
		mActivity = Activity;
		DisplayMetrics s_Metric = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getRealMetrics(s_Metric);
		mnLayoutwidth = s_Metric.widthPixels;
		mnLayoutheigth = s_Metric.heightPixels;
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(mActivity).build();
		// Initialize ImageLoader with configuration.
		mLoader = ImageLoader.getInstance();
		mLoader.init(configuration);
	}
	
	public void setAdapterInterface(AdapterInterface AdapterInterface) {
		mAdapterInterface = AdapterInterface;
	}

	@Override
	public int getCount() {
		return mUrlList == null ? 0 : mUrlList.size();
	}

	@Override
	public Object getItem(int position) {
		return mUrlList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}


	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.image_loading).cacheInMemory(true).cacheOnDisk(true)
			.bitmapConfig(Bitmap.Config.RGB_565).build();
	
	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if (convertView == null) {
			convertView = LayoutInflater.from(mActivity).inflate(R.layout.show_file_image_item, null);
			vh = new ViewHolder();
			vh.imageView = (PhotoView) convertView.findViewById(R.id.imageView_image);
			RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) vh.imageView.getLayoutParams();

			if (mAdapterInterface != null) {
				if (mAdapterInterface.getImageViewWidth() == 0) {
					lp.width = mnLayoutwidth;
				} else {
					lp.width = mAdapterInterface.getImageViewWidth();
				}
				if (mAdapterInterface.getImageViewWidth() == 0) {
					lp.height = mnLayoutheigth;
				} else {
					lp.height = mAdapterInterface.getImageViewHeight();
				}
				vh.imageView.setScaleType(ScaleType.FIT_CENTER);
			} else {
				lp.width = (int) ((mnLayoutwidth - 3 * mActivity.getResources().getDimension(R.dimen.view_items_margin)) / 2);
				lp.height = lp.width * 9 / 16;
				vh.imageView.setScaleType(ScaleType.CENTER_CROP);
			}
			vh.imageView.setLayoutParams(lp);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		mLoader.displayImage(mUrlList.get(position), vh.imageView, options);

		return convertView;
	}

	class ViewHolder {
		PhotoView imageView;
	}
	
	public interface AdapterInterface {
		int getImageViewWidth();
		int getImageViewHeight();
	}
	
}
