package com.example.zhougongjiemeng;

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
			//������
			break;
		case 1:
			//������
			break;
		case 2:
			//������
			break;
		case 3:
			//������
			break;
		case 4:
			//�ڽ���
			break;
		case 5:
			//ɽˮ��
			break;
		case 6:
			//������
			break;
		case 7:
			//�ֲ���
			break;
		case 8:
			//�鰮��
			break;
		case 9:
			//"ֲ����", 
			break;
		case 10:
			//"���",
			break;
		case 11:
			// "��Ʒ��", 
			break;
		case 12:
			//"������",
			break;
		case 13:
			// "������", 
			break;
		case 14:
			//"��Ȼ��", 
			break;
		case 15:
			//"������",
			break;
		case 16:
			// "�˶���", 
			break;
		case 17:
			//"ʳ����", "������"
			break;
		case 18:
			//"������"
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
							AbToastUtil.showToast(ZhouGongActivity.this, "�������ѯ�ؼ���");
							return;
						}
						RequestDream.getInstance().getInfo(new CloudInterface() {
							
							@Override
							public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
								LogUtils.i("wzw arg1:" + arg1);
								
								if (arg0 == CloudResponseStatus.Succ) {
									if (arg1 != null) {
										DreamBean DreamBean = (com.example.zhougongjiemeng.DreamBean) arg1;
										
										mDreamFragment.setDreamBean(DreamBean);
									}
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
