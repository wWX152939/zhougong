package com.onekey.zhougongjiemeng;

import mythware.common.LogUtils;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;

import com.ab.activity.AbActivity;
import com.ab.view.slidingmenu.SlidingMenu;
import com.ab.view.titlebar.AbTitleBar;
import com.onekey.leftfragment.GifFragment;
import com.onekey.leftfragment.HomeFragment;
import com.onekey.leftfragment.PictureFragment;

public class MainActivity extends AbActivity {

	private AbTitleBar mAbTitleBar = null;
	private MenuFragment mMainMenuFragment;
	private HomeFragment mHomeFragment;
	private PictureFragment mPictureFragment;
	private GifFragment mGifFragment;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.activity_main);
        
		mAbTitleBar = getTitleBar();
		mAbTitleBar.setTitleText(R.string.app_name);
		mAbTitleBar.setLogo(R.drawable.button_selector_menu);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);
        
        final SlidingMenu menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

		mMainMenuFragment = new MenuFragment();
		mMainMenuFragment.setCallback(new MenuFragment.Callback() {
			
			@Override
			public void callback(AdapterView<?> parent, View view, int position, long id) {
				Fragment fragment = null;
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				if (mHomeFragment == null) {
					mHomeFragment = new HomeFragment();
				}
				if (mPictureFragment == null) {
					mPictureFragment = new PictureFragment();
				}
				if (mGifFragment == null) {
					mGifFragment = new GifFragment();
				}
				switch(position) {
				case 0:
					
					if (mPictureFragment.isAdded()) {
						ft.hide(mPictureFragment);
					}
					if (mGifFragment.isAdded()) {
						ft.hide(mGifFragment);
					}
					fragment = mHomeFragment;
					break;
				case 1:
					if (mGifFragment.isAdded()) {
						ft.hide(mGifFragment);
					}
					if (mHomeFragment.isAdded()) {
						ft.hide(mHomeFragment);
					}
					fragment = mPictureFragment;
					break;
				case 2:
					if (mPictureFragment.isAdded()) {
						ft.hide(mPictureFragment);
					}
					if (mHomeFragment.isAdded()) {
						ft.hide(mHomeFragment);
					}
					fragment = mGifFragment;
					break;
				case 3:
					return;
				}
				LogUtils.d("wzw postion:" + position +  " frag:" + fragment);
				if (fragment.isAdded()) {
					ft.show(fragment);
				} else {
					ft.add(R.id.container, fragment);
				}
                ft.commit();
			}
		});
		// menu ”ÕºµƒFragmentÃÌº”
		menu.setMenu(R.layout.sliding_menu_menu);
		getFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, mMainMenuFragment).commit();

        if (savedInstanceState == null) {
        	mHomeFragment = new HomeFragment();
            getFragmentManager().beginTransaction()
                    .add(R.id.container, mHomeFragment)
                    .commit();
        }
        
        mAbTitleBar.getLogoView().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (menu.isMenuShowing()) {
					menu.showContent();
				} else {
					menu.showMenu();
				}
			}
		});
    }

}
