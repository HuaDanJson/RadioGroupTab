package com.cool.radiogrouptab;

import android.cool.radiogrouptab.R;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rbt_msg_video_chat_activity) RadioButton mMsgRadioButton;
    @BindView(R.id.rbt_home_video_chat_activity) RadioButton mHomeRadioButton;
    @BindView(R.id.rbt_moment_video_chat_activity) RadioButton mMomentRadioButton;
    @BindView(R.id.rbt_me_video_chat_activity) RadioButton mMeRadioButton;
    @BindView(R.id.radio_group_video_chat_activity) RadioGroup mRadioGroup;
    @BindView(R.id.fl_show_fragment_video_chat_activity) FrameLayout mShowFragmentFrameLayout;
    @BindView(R.id.tv_line_video_chat_activity) TextView mTabLine;

    private MessageFragment mMessageFragment;
    private HomeFragment mHomeFragment;
    private MomentFragment mMomentFragment;
    private MeFragment mMeFragment;
    private static final String HOME_CURRENT_TAB_POSITION = "HOME_CURRENT_TAB_POSITION";
    private static final int MESSAGE_ID = R.id.rbt_msg_video_chat_activity;
    private static final int HOME_ID = R.id.rbt_home_video_chat_activity;
    private static final int MOMENT_ID = R.id.rbt_moment_video_chat_activity;
    private static final int ME_ID = R.id.rbt_me_video_chat_activity;
    private static final String MESSAGE_FRAGMENT = "MESSAGE_FRAGMENT";
    private static final String HOME_FRAGMENT = "HOME_FRAGMENT";
    private static final String MOMENT_FRAGMENT = "MOMENT_FRAGMENT";
    private static final String ME_FRAGMENT = "ME_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment(savedInstanceState);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SwitchTo(checkedId);
            }
        });
    }

    public void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = HOME_ID;
        if (savedInstanceState != null) {
            mMessageFragment = (MessageFragment) getSupportFragmentManager().findFragmentByTag(MESSAGE_FRAGMENT);
            mHomeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT);
            mMomentFragment = (MomentFragment) getSupportFragmentManager().findFragmentByTag(MOMENT_FRAGMENT);
            mMeFragment = (MeFragment) getSupportFragmentManager().findFragmentByTag(ME_FRAGMENT);
            currentTabPosition = savedInstanceState.getInt(HOME_CURRENT_TAB_POSITION);
        } else {
            mMessageFragment = new MessageFragment();
            mHomeFragment = new HomeFragment();
            mMomentFragment = new MomentFragment();
            mMeFragment = new MeFragment();
            //transaction里添加fragment
            transaction.add(R.id.fl_show_fragment_video_chat_activity, mMessageFragment, MESSAGE_FRAGMENT);
            transaction.add(R.id.fl_show_fragment_video_chat_activity, mHomeFragment, HOME_FRAGMENT);
            transaction.add(R.id.fl_show_fragment_video_chat_activity, mMomentFragment, MOMENT_FRAGMENT);
            transaction.add(R.id.fl_show_fragment_video_chat_activity, mMeFragment, ME_FRAGMENT);
        }
        transaction.commit();
        SwitchTo(currentTabPosition);
    }

    private void SwitchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case MESSAGE_ID:
                transaction.show(mMessageFragment);
                transaction.hide(mHomeFragment);
                transaction.hide(mMomentFragment);
                transaction.hide(mMeFragment);
                transaction.commitAllowingStateLoss();
                break;
            case HOME_ID:
                transaction.show(mHomeFragment);
                transaction.hide(mMessageFragment);
                transaction.hide(mMomentFragment);
                transaction.hide(mMeFragment);
                transaction.commitAllowingStateLoss();
                break;
            case MOMENT_ID:
                transaction.show(mMomentFragment);
                transaction.hide(mHomeFragment);
                transaction.hide(mMessageFragment);
                transaction.hide(mMeFragment);
                transaction.commitAllowingStateLoss();
                break;
            case ME_ID:
                transaction.show(mMeFragment);
                transaction.hide(mHomeFragment);
                transaction.hide(mMomentFragment);
                transaction.hide(mMessageFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }

    }

}
