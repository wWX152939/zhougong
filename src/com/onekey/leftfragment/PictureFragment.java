package com.onekey.leftfragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import mythware.http.CloudUpdateVersionServer.CloudInterface;
import mythware.http.CloudUpdateVersionServer.CloudResponseStatus;
import mythware.liba.BaseFragment;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.ab.util.AbToastUtil;
import com.ab.view.pullview.AbPullToRefreshView;
import com.ab.view.pullview.AbPullToRefreshView.OnFooterLoadListener;
import com.onekey.bean.PicBean;
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

	private FolderImageAdapter mFolderImageAdapter;
	private AbPullToRefreshView mAbPullToRefreshView;
	private int mCurrentPage = 1;
	@Override
	public void setupUiEvents() {
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), PictureShowActivity.class);
//				intent.putExtra("position", (mCurrentPage) * 10 + position + 1);
				intent.putExtra("obj", (String)mFolderImageAdapter.getItem(position));
				startActivity(intent);
			}
		});
		mAbPullToRefreshView.setOnFooterLoadListener(new OnFooterLoadListener() {
			
			@Override
			public void onFooterLoad(AbPullToRefreshView arg0) {
				loadData();
			}
		});
//		20171221232004
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
		}, date, mCurrentPage++, 10);
	}
	
	@Override
	public void setupUiHandlers() {
		mGridView = (GridView)mView.findViewById(R.id.gv);
		mAbPullToRefreshView = (AbPullToRefreshView)mView.findViewById(R.id.refreshview);
		mFolderImageAdapter = new FolderImageAdapter(mActivity);
		mGridView.setAdapter(mFolderImageAdapter);
	}

	@Override
	public void setupViewGroup() {
		mView = mFlater.inflate(R.layout.gridview, null);
	}
	
}
