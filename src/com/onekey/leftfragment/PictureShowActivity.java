package com.onekey.leftfragment;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;
import com.onekey.leftfragment.PicInnerFragment.Callback;
import com.onekey.zhougongjiemeng.MenuFragment;
import com.onekey.zhougongjiemeng.R;

public class PictureShowActivity extends AbActivity {

	private AbTitleBar mAbTitleBar = null;
	MenuFragment mMainMenuFragment;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.activity_main);
        
		mAbTitleBar = getTitleBar();
		String url = getIntent().getStringExtra("obj");
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
			mPicInnerFragment = new PicInnerFragment();
			mPicInnerFragment.setDataPosition(url);
			mPicInnerFragment.setCallback(new Callback() {
				
				@Override
				public void callback(String title) {
					mAbTitleBar.setTitleText(title);
				}
			});
            getFragmentManager().beginTransaction()
                    .add(R.id.container, mPicInnerFragment)
                    .commit();
        }
    }
    
    PicInnerFragment mPicInnerFragment;
    
    private void initTitleRightLayout() {
		mAbTitleBar.clearRightView();
	}

}
