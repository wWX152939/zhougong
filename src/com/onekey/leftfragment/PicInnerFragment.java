package com.onekey.leftfragment;

import mythware.liba.BaseFragment;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.onekey.common.PhotoView;
import com.onekey.zhougongjiemeng.R;

public class PicInnerFragment extends BaseFragment {

	private String mCurrentUrl;
	private Callback mCallback;
	private PhotoView mPhotoView;
	private ImageLoader mLoader;
	
	@Override
	public void onServiceDisconnecting() {
		
	}

	@Override
	public void restoreUi() {
		
	}

	@Override
	public void setupStrings() {
		
	}
	
	public void setDataPosition(String position) {
		mCurrentUrl = position;
	}
	
	public void setCallback(Callback Callback) {
		mCallback = Callback;
	}

	@Override
	public void setupUiEvents() {
		mPhotoView.enable();
		RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mPhotoView.getLayoutParams();
		DisplayMetrics s_Metric = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getRealMetrics(s_Metric);
		int mnLayoutwidth = s_Metric.widthPixels;
		int mnLayoutheigth = s_Metric.heightPixels;
		lp.width = mnLayoutwidth;
		lp.height = mnLayoutheigth;
//		mPhotoView.setScaleType(ScaleType.FIT_CENTER);
//		mPhotoView.setLayoutParams(lp);
		// œ‘ æÕº∆¨µƒ≈‰÷√
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.image_loading).cacheInMemory(true).cacheOnDisk(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(mActivity).build();
		// Initialize ImageLoader with configuration.
		mLoader = ImageLoader.getInstance();
		mLoader.init(configuration);
		mLoader.displayImage(mCurrentUrl, mPhotoView, options);
	}
	

	@Override
	public void setupUiHandlers() {
		mPhotoView = (PhotoView)mView.findViewById(R.id.imageView_image);
	}

	@Override
	public void setupViewGroup() {
		mView = mFlater.inflate(R.layout.show_file_image_item, null);
	}
	
	public interface Callback {
		void callback(String title);
	}
	
}
