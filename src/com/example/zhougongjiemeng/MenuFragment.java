package com.example.zhougongjiemeng;

import mythware.liba.BaseFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuFragment extends BaseFragment {

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
		String[] arrays = new String[]{"周公解梦", "美图", "搞笑gif", "关于"};
		mListView.setAdapter(new ArrayAdapter<String>(mActivity, R.layout.list_item_menu, arrays));
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void setupViewGroup() {
		mView = mFlater.inflate(R.layout.fragment_menu, null);
	}
	
}
