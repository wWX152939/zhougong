package com.onekey.leftfragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import mythware.http.CloudUpdateVersionServer.CloudInterface;
import mythware.http.CloudUpdateVersionServer.CloudResponseStatus;
import mythware.liba.BaseFragment;
import android.widget.ListView;

import com.ab.util.AbToastUtil;
import com.ab.view.pullview.AbPullToRefreshView;
import com.ab.view.pullview.AbPullToRefreshView.OnFooterLoadListener;
import com.onekey.bean.PicBean;
import com.onekey.http.RequestShowAPI;
import com.onekey.leftfragment.FolderImageAdapter.AdapterInterface;
import com.onekey.zhougongjiemeng.R;

public class PicInnerBackFragment extends BaseFragment {

	private ListView mListView;
	private AbPullToRefreshView mAbPullToRefreshView;
	private int mCurrentPage = 1;
	private FolderImageAdapter mFolderImageAdapter;
	private Callback mCallback;
	
	@Override
	public void onServiceDisconnecting() {
		
	}

	@Override
	public void restoreUi() {
		
	}

	@Override
	public void setupStrings() {
		
	}
	
	public void setDataPosition(int position) {
		mCurrentPage = position;
	}
	
	public void setCallback(Callback Callback) {
		mCallback = Callback;
	}

	@Override
	public void setupUiEvents() {
		mAbPullToRefreshView.setOnFooterLoadListener(new OnFooterLoadListener() {
			
			@Override
			public void onFooterLoad(AbPullToRefreshView arg0) {
				loadData();
			}
		});
		loadData();
	}
	
	private void loadData() {
		Date curDate = new Date(System.currentTimeMillis());   
		String date = new SimpleDateFormat("yyyyMMddHHmmss").format(curDate);
		RequestShowAPI.getInstance().getInfo(new CloudInterface() {
			
			@Override
			public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
				if (arg0 == CloudResponseStatus.Succ){
					PicBean info = (PicBean) arg1;
					mCallback.callback(info.getShowapi_res_body().getNewslist().get(0).getTitle());
					int size = info.getShowapi_res_body().getNewslist().size();
					for (int i = 0; i < size; i ++) {
						mFolderImageAdapter.addData(info.getShowapi_res_body().getNewslist().get(i).getPicUrl());
					}
					mFolderImageAdapter.notifyDataSetChanged();
				} else {
					AbToastUtil.showToast(mActivity, "ÍøÂçÒì³£");
				}

				mAbPullToRefreshView.onFooterLoadFinish();
				
			}
		}, date, mCurrentPage++, 1);
	}

	@Override
	public void setupUiHandlers() {
		mListView = (ListView)mView.findViewById(R.id.lv);
		mAbPullToRefreshView = (AbPullToRefreshView)mView.findViewById(R.id.refreshview);
		mFolderImageAdapter = new FolderImageAdapter(mActivity);
		mFolderImageAdapter.setAdapterInterface(new AdapterInterface() {
			
			@Override
			public int getImageViewWidth() {
				return 0;
			}
			
			@Override
			public int getImageViewHeight() {
				return 0;
			}
		});
		mListView.setAdapter(mFolderImageAdapter);
	}

	@Override
	public void setupViewGroup() {
		mView = mFlater.inflate(R.layout.fragment_picinner, null);
	}
	
	public interface Callback {
		void callback(String title);
	}
	
}
