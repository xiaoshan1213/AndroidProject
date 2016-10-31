package com.example.sam.game2048;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by sam on 2016/2/20.
 */
public class Card extends FrameLayout {

    private TextView label;
    private int num=0;

    public Card(Context context) {
        super(context);

        label=new TextView(getContext());
        label.setTextSize(32);
        label.setGravity(Gravity.CENTER);
        label.setBackgroundColor(0x33ffffff);
        LayoutParams layoutParams=new LayoutParams(-1,-1);
        layoutParams.setMargins(10,10,0,0);
        addView(label,layoutParams);
        setNum(0);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;

        if(num<=0){
            label.setText("");
        }else {
            label.setText(num+"");//只能识别字符串
        }

    }

    public boolean equals(Card card){
        return getNum()==card.getNum();
    }

}
