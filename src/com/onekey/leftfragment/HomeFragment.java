package com.onekey.leftfragment;

import mythware.liba.BaseFragment;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.onekey.zhougongjiemeng.R;
import com.onekey.zhougongjiemeng.ZhouGongActivity;

public class HomeFragment extends BaseFragment {
	
	public static final String DrawClass[] = new String[] {"������", "������", "������", "�ڽ���", "ɽˮ��", "������", "�ֲ���", "�鰮��"
		, "ֲ����", "���", "��Ʒ��", "������", "������", "��Ȼ��", "������", "�˶���", "ʳ����", "������"};

	private ListView mListView;
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
		String[] arrays = DrawClass;
		mListView.setAdapter(new ArrayAdapter<String>(mActivity, R.layout.list_item, arrays));
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.putExtra("position", position);
				intent.setClass(getActivity(), ZhouGongActivity.class);
				startActivity(intent);
				
			}
		});
	}

	@Override
	public void setupViewGroup() {
		mView = mFlater.inflate(R.layout.fragment_home, null);
	}
	
}
