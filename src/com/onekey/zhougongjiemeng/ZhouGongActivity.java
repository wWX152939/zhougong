package com.onekey.zhougongjiemeng;

import mythware.common.LogUtils;
import mythware.http.CloudUpdateVersionServer.CloudInterface;
import mythware.http.CloudUpdateVersionServer.CloudResponseStatus;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.ab.activity.AbActivity;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.titlebar.AbTitleBar;
import com.onekey.bean.DreamBean;
import com.onekey.http.RequestDream;
import com.onekey.leftfragment.HomeFragment;

public class ZhouGongActivity extends AbActivity {

	private AbTitleBar mAbTitleBar = null;
	MenuFragment mMainMenuFragment;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.activity_main);
        
		mAbTitleBar = getTitleBar();
		int position = getIntent().getIntExtra("position", 0);
		initContent(position);
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);
        
        mAbTitleBar.getLogoView().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
        
		initTitleRightLayout();
		
		if (savedInstanceState == null) {
			mDreamFragment = new DreamFragment();
			mDreamFragment.setDataPosition(position);
            getFragmentManager().beginTransaction()
                    .add(R.id.container, mDreamFragment)
                    .commit();
        }
    }
    
    DreamFragment mDreamFragment;
    
    private void initContent(int position) {
    	switch(position) {
		case 0:
			//人物类
			break;
		case 1:
			//动物类
			break;
		case 2:
			//娱乐类
			break;
		case 3:
			//动物类
			break;
		case 4:
			//宗教类
			break;
		case 5:
			//山水类
			break;
		case 6:
			//建筑类
			break;
		case 7:
			//恐怖类
			break;
		case 8:
			//情爱类
			break;
		case 9:
			//"植物类", 
			break;
		case 10:
			//"活动类",
			break;
		case 11:
			// "物品类", 
			break;
		case 12:
			//"生活类",
			break;
		case 13:
			// "疾病类", 
			break;
		case 14:
			//"自然类", 
			break;
		case 15:
			//"身体类",
			break;
		case 16:
			// "运动类", 
			break;
		case 17:
			//"食物类", "鬼神类"
			break;
		case 18:
			//"鬼神类"
			break;
		}

		mAbTitleBar.setTitleText(HomeFragment.DrawClass[position]);
    }
    
    private void initTitleRightLayout() {
		mAbTitleBar.clearRightView();
		View rightViewApp = mInflater.inflate(R.layout.app_btn, null);
		mAbTitleBar.addRightView(rightViewApp);
		Button appBtn = (Button) rightViewApp.findViewById(R.id.appBtn);

		appBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				View view = mInflater.inflate(R.layout.edittext, null);
				Button left = (Button)view.findViewById(R.id.left_btn);
				left.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						AbDialogUtil.removeDialog(ZhouGongActivity.this);
					}
				});
				Button right = (Button)view.findViewById(R.id.right_btn);
				final EditText EditText = (EditText)view.findViewById(R.id.et);
				right.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if (EditText.getText().toString().isEmpty()) {
							AbToastUtil.showToast(ZhouGongActivity.this, "请输入查询关键字");
							return;
						}
						RequestDream.getInstance().getInfo(new CloudInterface() {
							
							@Override
							public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
								LogUtils.i("wzw arg1:" + arg1);
								
								if (arg0 == CloudResponseStatus.Succ) {
									if (arg1 != null) {
										DreamBean DreamBean = (com.onekey.bean.DreamBean) arg1;
										
										mDreamFragment.setDreamBean(DreamBean);
									}
								} else if (arg0 == CloudResponseStatus.ErrorNetwork) {
									AbToastUtil.showToast(ZhouGongActivity.this, "网络异常");
									mDreamFragment.setDreamBean(new DreamBean());
								} else {
									AbToastUtil.showToast(ZhouGongActivity.this, "操作异常");
									mDreamFragment.setDreamBean(new DreamBean());
								}
								
							}
						}, EditText.getText().toString());
						AbDialogUtil.removeDialog(ZhouGongActivity.this);
					}
				});
				AbDialogUtil.showAlertDialog(view);
			}
		});

	}

}
