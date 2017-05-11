package com.shuaige.ggq.mytopbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by 18271 on 11/05/2017.
 */
public class CommonTopbar extends RelativeLayout {
    private OnTitleButtonClickListener onTitleButtonClickListener;

    public void setOnTitleButtonClickListener(OnTitleButtonClickListener onTitleButtonClickListener) {
        this.onTitleButtonClickListener = onTitleButtonClickListener;
    }

    private ImageView leftButton, rightButton;
    private TextView tvTitle;

    private String titleText;
    private float titleTextSize;
    private int titleTextColor;

    private Drawable leftBackGraund;
    private Drawable rightBackGraund;
    private float leftButtonmagin;
    private float rightButtonmagin;
    private LayoutParams leftLayoutParams, titleLayoutParams, rightLayoutParams;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public CommonTopbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Topbar);
        titleText = typedArray.getString(R.styleable.Topbar_topbarTitleText);
        titleTextSize = typedArray.getDimension(R.styleable.Topbar_topbarTitleTextSize, 0);
        titleTextColor = typedArray.getColor(R.styleable.Topbar_topbarTitleTextColor, 0);

        leftBackGraund = typedArray.getDrawable(R.styleable.Topbar_leftButtonDrawable);
        rightBackGraund = typedArray.getDrawable(R.styleable.Topbar_rightButtonDrawable);
        leftButtonmagin = typedArray.getDimension(R.styleable.Topbar_leftButtonmagin, 0);
        rightButtonmagin = typedArray.getDimension(R.styleable.Topbar_rightButtonmagin, 0);
        //使用完回收
        typedArray.recycle();
        //实例化控件
        leftButton = new ImageView(context);
        leftButton.setImageDrawable(leftBackGraund);
        leftButton.setOnClickListener(onLeftClickListener);
        rightButton = new ImageView(context);
        rightButton.setImageDrawable(rightBackGraund);
        rightButton.setOnClickListener(onRightClickListener);
        tvTitle = new TextView(context);
        tvTitle.setText(titleText);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setTextColor(titleTextColor);

        //将左边控件添加进布局
        leftLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        leftLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        leftLayoutParams.setMargins((int) leftButtonmagin, 0, 0, 0);
        addView(leftButton, leftLayoutParams);
        //将右边控件添加进布局
        rightLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        rightLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        rightLayoutParams.setMargins(0, 0, (int) rightButtonmagin, 0);
        addView(rightButton, rightLayoutParams);
        //将中间标题加入到布局
        titleLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(tvTitle, titleLayoutParams);
    }
    public void setTitleTextNature(String text) {
        tvTitle.setText(text);
        invalidate();
    }
    public void setTitleTextNature(String text, float newtitleTextSize) {
        tvTitle.setText(text);
        tvTitle.setTextSize(newtitleTextSize == 0 ? titleTextSize : newtitleTextSize);
        invalidate();
    }
    public void setTitleTextNature(String text, float newtitleTextSize,int newtitleTextColor) {
        tvTitle.setText(text);
        tvTitle.setTextSize(newtitleTextSize == 0 ? titleTextSize : newtitleTextSize);
        tvTitle.setTextColor(newtitleTextColor == 0 ? titleTextColor : newtitleTextColor);
        invalidate();
    }

    public void setLeftButtonNature(Drawable leftButtonDrawable,int visibisity) {
        leftButton.setImageDrawable(leftButtonDrawable);
        leftButton.setVisibility(visibisity == 0 ? VISIBLE : visibisity);
        invalidate();
    }
    public void setrightButtonNature(Drawable rightButtonDrawable,int visibisity) {
        rightButton.setImageDrawable(rightButtonDrawable);
        rightButton.setVisibility(visibisity==0?VISIBLE:visibisity);
        invalidate();
    }
    public void setLeftButtonMaginLeft(int maginLeft){
        removeView(leftButton);;
        leftLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        leftLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        leftLayoutParams.setMargins(maginLeft, 0, 0, 0);
        addView(leftButton, leftLayoutParams);
        invalidate();
    }
    public void setRightButtonMaginLeft(int maginRight){
        removeView(rightButton);;
        rightLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        rightLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        rightLayoutParams.setMargins(0, 0, maginRight, 0);
        addView(rightButton, rightLayoutParams);
        invalidate();
    }
    OnClickListener onLeftClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onTitleButtonClickListener != null) {
                onTitleButtonClickListener.onLeftClickListener();
            }
        }
    };
    OnClickListener onRightClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onTitleButtonClickListener != null) {
                onTitleButtonClickListener.onRightClickListener();
            }
        }
    };

    public interface OnTitleButtonClickListener {
        void onRightClickListener();

        void onLeftClickListener();
    }
}
