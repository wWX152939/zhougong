package com.onekey.leftfragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mythware.common.LogUtils;
import mythware.http.CloudUpdateVersionServer.CloudInterface;
import mythware.http.CloudUpdateVersionServer.CloudResponseStatus;
import mythware.liba.BaseFragment;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import com.ab.util.AbToastUtil;
import com.ab.view.pullview.AbPullToRefreshView;
import com.ab.view.pullview.AbPullToRefreshView.OnFooterLoadListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.onekey.bean.PicBean;
import com.onekey.common.PhotoView;
import com.onekey.http.RequestShowAPI;
import com.onekey.zhougongjiemeng.R;

public class PictureFragment extends BaseFragment {
	
	private GridView mGridView;
	
	@Override
	public void onServiceDisconnecting() {
		
	}

	@Override
	public void restoreUi() {
		
	}

	@Override
	public void setupStrings() {
		
	}

	private ImageLoader mLoader;
	private List<String> mUrlList = new ArrayList<String>();
	private FolderImageAdapter mFolderImageAdapter;
	private int mnLayoutwidth;
	private AbPullToRefreshView mAbPullToRefreshView;
	private int mCurrentPage = 1;
	@Override
	public void setupUiEvents() {
		DisplayMetrics s_Metric = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getRealMetrics(s_Metric);
		mnLayoutwidth = s_Metric.widthPixels;
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(mActivity).build();
		// Initialize ImageLoader with configuration.
		mLoader = ImageLoader.getInstance();
		mLoader.init(configuration);
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}
		});
		mAbPullToRefreshView.setOnFooterLoadListener(new OnFooterLoadListener() {
			
			@Override
			public void onFooterLoad(AbPullToRefreshView arg0) {
				Date curDate = new Date(System.currentTimeMillis());   
				String date = new SimpleDateFormat("yyyyMMddHHmmss").format(curDate);
				RequestShowAPI.getInstance().getInfo(new CloudInterface() {
					
					@Override
					public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
						if (arg0 == CloudResponseStatus.Succ){
							PicBean info = (PicBean) arg1;
							int size = info.getShowapi_res_body().getNewslist().size();
							for (int i = 0; i < size; i ++) {
								mUrlList.add(info.getShowapi_res_body().getNewslist().get(i).getPicUrl());
							}
							LogUtils.i("wzw mUrlList:" + mUrlList);
							mFolderImageAdapter.notifyDataSetChanged();
						} else {
							AbToastUtil.showToast(mActivity, "ÍøÂçÒì³£");
						}

						mAbPullToRefreshView.onFooterLoadFinish();
						
					}
				}, date, mCurrentPage++);
			}
		});
		Date curDate = new Date(System.currentTimeMillis());   
		String date = new SimpleDateFormat("yyyyMMddHHmmss").format(curDate);
//		20171221232004
		RequestShowAPI.getInstance().getInfo(new CloudInterface() {
			
			@Override
			public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
				if (arg0 == CloudResponseStatus.Succ){
					PicBean info = (PicBean) arg1;
					int size = info.getShowapi_res_body().getNewslist().size();
					for (int i = 0; i < size; i ++) {
						mUrlList.add(info.getShowapi_res_body().getNewslist().get(i).getPicUrl());
					}
					LogUtils.i("wzw mUrlList:" + mUrlList);
					mFolderImageAdapter.notifyDataSetChanged();
				} else {
					AbToastUtil.showToast(mActivity, "ÍøÂçÒì³£");
				}
				
			}
		}, date, mCurrentPage);
	}

	@Override
	public void setupUiHandlers() {
		mGridView = (GridView)mView.findViewById(R.id.gv);
		mAbPullToRefreshView = (AbPullToRefreshView)mView.findViewById(R.id.refreshview);
		mFolderImageAdapter = new FolderImageAdapter();
		mGridView.setAdapter(mFolderImageAdapter);
	}

	@Override
	public void setupViewGroup() {
		mView = mFlater.inflate(R.layout.gridview, null);
	}
	
	class FolderImageAdapter extends BaseAdapter {

		private Bitmap mBitmaps;

		public FolderImageAdapter() {
			super();
			mBitmaps = BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.image_loading);
		}

		@Override
		public int getCount() {
			return mUrlList == null ? 0 : mUrlList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@SuppressLint("NewApi")
		@SuppressWarnings("deprecation")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder vh;
			if (convertView == null) {
				convertView = LayoutInflater.from(mActivity).inflate(R.layout.show_file_image_item, null);
				vh = new ViewHolder();
				vh.imageView = (PhotoView) convertView.findViewById(R.id.imageView_image);
				RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) vh.imageView.getLayoutParams();

				lp.width = (int) ((mnLayoutwidth - 3 * mActivity.getResources().getDimension(R.dimen.view_items_margin)) / 2);
				lp.height = lp.width * 9 / 16;
				vh.imageView.setLayoutParams(lp);
				convertView.setTag(vh);
			} else {
				vh = (ViewHolder) convertView.getTag();
			}


			final int index = position;// - off;


			// ÏÔÊ¾Í¼Æ¬µÄÅäÖÃ
			DisplayImageOptions options = new DisplayImageOptions.Builder()
					.showImageOnLoading(R.drawable.image_loading).cacheInMemory(true)
					.bitmapConfig(Bitmap.Config.RGB_565).build();

			vh.imageView.setScaleType(ScaleType.CENTER_CROP);
			mLoader.displayImage(mUrlList.get(position), vh.imageView, options);

			return convertView;
		}

		class ViewHolder {
			PhotoView imageView;
		}
	}
	
}
