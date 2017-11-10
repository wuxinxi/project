package com.wxx.imooc.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.IdRes;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.wxx.imooc.R;
import com.wxx.imooc.base.BaseActivity;
import com.wxx.imooc.fragement.home.HomeFragment;

import butterknife.BindView;

/**
 * 作者: Tangren on 2017-11-05
 * 包名：com.wxx.imooc.activity.home
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class HomeActivity extends BaseActivity implements OnTabSelectListener {

    @BindView(R.id.frame_content)
    FrameLayout frameLayout;
    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;

    private FragmentManager fm;
    private HomeFragment homeFragment;

    @Override
    protected void initView() {
        bottomBar.setOnTabSelectListener(this);
        fm = getFragmentManager();
        homeFragment = HomeFragment.newInstance();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.frame_content, homeFragment);
        transaction.commit();
    }

    @Override
    protected int layoutID() {
        return R.layout.activity_home_layout;
    }

    @Override
    public void onTabSelected(@IdRes int tabId) {
        switch (tabId) {
            case R.id.home:

                break;
            case R.id.message:

                break;
            case R.id.mine:

                break;
            default:

                break;
        }
    }
}
