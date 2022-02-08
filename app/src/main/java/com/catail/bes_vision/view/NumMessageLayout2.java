package com.catail.bes_vision.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.catail.bes_vision.R;


/**
 * 只有首页在用 这个图标.放大了.
 */
public class NumMessageLayout2 extends LinearLayout {
    private ImageView iconImage;// 图标
    private TextView numText;// 消息提醒的数目
    private TextView tvName;//标题名称

    public NumMessageLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    @SuppressLint("NewApi")
    public NumMessageLayout2(Context context, AttributeSet attrs,
                             int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    public NumMessageLayout2(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        final View view = inflate(context, R.layout.message_icon2, null);
        iconImage =  view.findViewById(R.id.icon);
        iconImage.setPadding(0, 0, 0, 0);
        numText =  view.findViewById(R.id.num_text);
        tvName = view.findViewById(R.id.tv_name);

        addView(view);
        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
//        params.setMargins(0, 0, 40, 0);
        this.setLayoutParams(params);

    }

    /* 设置需要消息提醒图片 */
    public void setIcon(int imgId) {
        if (iconImage != null)
            iconImage.setImageResource(imgId);
    }

    /* 设置消息提醒数量 */
    public void setMsgNum(String msgNum) {
        if (numText != null)
            if (Integer.parseInt(msgNum) > 99) {
                numText.setText("···");
            } else {
                numText.setText(msgNum);
            }

    }

    /*设置名称*/
    public void setName(String name) {
        if (tvName != null) {
            tvName.setText(name);
        }
    }

    /**
     * 隐藏右上角的红圈
     */
    public void InVisibleNum() {
        if (numText != null) {
            numText.setVisibility(View.GONE);
        }
    }

    /**
     * 显示右上角的红圈
     */
    public void VisibleNum() {
        if (numText != null) {
            numText.setVisibility(View.VISIBLE);
        }
    }
}
