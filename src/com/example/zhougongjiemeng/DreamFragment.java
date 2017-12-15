package com.example.zhougongjiemeng;

import java.util.List;

import mythware.http.DataUtils;
import mythware.liba.BaseFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import bean.JsonDreamBean;

public class DreamFragment extends BaseFragment {
	
	private ListView mListView;
	private DreamBean mDreamBean;
	private int mPosition;
	private DataListAdapter mAdapter;
	
	public void setDataPosition(int position) {
		mPosition = position;
	}
	
	@Override
	public void onServiceDisconnecting() {
		
	}

	@Override
	public void restoreUi() {
		
	}

	@Override
	public void setupStrings() {
		
	}

	@Override
	public void setupUiEvents() {
		
	}

	@Override
	public void setupUiHandlers() {
		mListView = (ListView)mView.findViewById(R.id.lv);
		mDreamBean = new DreamBean();
		initData();
		initData();
		initData();
		mAdapter = new DataListAdapter();
		mListView.setAdapter(mAdapter);
		mListView.setEmptyView(mFlater.inflate(R.layout.empty_view, null));
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			}
		});
	}
	
	public void setDreamBean(DreamBean DreamBean) {
		mDreamBean = DreamBean;
		mAdapter.notifyDataSetChanged();
	}
	
	private void initData() {
		DreamBean bean = null;
		DreamBean bean2 = null;
		DreamBean bean3 = null;
		switch(mPosition) {
		case 0:
			bean = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "xingzou"));
			//������
			break;
		case 1:
			bean = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "mao"));
			bean2 = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "gou"));
			bean.getResult().addAll(bean2.getResult());
			//������
			break;
		case 2:
			//������
			bean = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "yinyue"));
			break;
		case 3:
			//�ڽ���
			bean = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "zongjiao"));
			break;
		case 4:
			//ɽˮ��
			bean = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "dahai"));
			break;
		case 5:
			//������
			bean = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "gaolou"));
			break;
		case 6:
			//�ֲ���
			bean = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "chiren"));
			break;
		case 7:
			//�鰮��
			bean = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "shuijiao"));
			break;
		case 8:
			//"ֲ����", 
			bean = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "hua"));
			break;
		case 9:
			//"���",
			bean = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "changge"));
			break;
		case 10:
			// "��Ʒ��", 
			bean = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "che"));
			break;
		case 11:
			//"������",
			bean = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "chaoshi"));
			break;
		case 12:
			// "������", 
			bean = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "shengbing"));
			break;
		case 13:
			//"��Ȼ��", 
			bean = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "chuntian"));
			break;
		case 14:
			//"������",
			bean = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "rufang"));
			break;
		case 15:
			// "�˶���", 
			bean = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "lanqiu"));
			break;
		case 16:
			//"ʳ����",
			bean = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "xiangjiao"));
			break;
		case 17:
			//"������"
			bean = (DreamBean) DataUtils.getObject(DreamBean.class, JsonDreamBean.readAssetsTxt(mActivity, "gui"));
			break;
		}
		mDreamBean = bean;
	}

	@Override
	public void setupViewGroup() {
		mView = mFlater.inflate(R.layout.fragment_home, null);
	}
	
	private class DataListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mDreamBean.getResult() == null ? 1 : mDreamBean.getResult().size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if (convertView == null) {
				convertView = mFlater.inflate(R.layout.list_item_dream, null);
			}
			
			TextView tvTitle = (TextView)convertView.findViewById(R.id.title);
			TextView tvContent = (TextView)convertView.findViewById(R.id.content);
			
			if (mDreamBean.getResult() == null) {
				tvTitle.setText("����Ϊ��");
				tvContent.setText("�ܱ�Ǹ��û�в�ѯ����Ҫ�Ľ����");
			} else {
				tvTitle.setText(mDreamBean.getResult().get(position).getTitle());
				List list = mDreamBean.getResult().get(position).getList();
				String content = "";
				for (int i = 0; i < list.size(); i++) {
					content += list.get(i) + "\n";
				}
				tvContent.setText(content);
			}
			
			
			return convertView;
		}
		
	}
	
}
