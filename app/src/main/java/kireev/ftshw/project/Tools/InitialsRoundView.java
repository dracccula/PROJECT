package kireev.ftshw.project.Tools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import kireev.ftshw.project.R;


public class InitialsRoundView extends FrameLayout {

    private int backgroundColor;
    private String text;

    private GradientDrawable gradientDrawable;
    private TextView tv;

    public InitialsRoundView(Context context, int backgroundColor, String text) {
        super(context);
        this.backgroundColor = backgroundColor;
        this.text = changeTextToInitials(text);
        addChildren(context);
    }

    public InitialsRoundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyAttrs(context, attrs);
        addChildren(context);
    }

    public void setBackgroundColor(int backgroundColor){
        this.backgroundColor = backgroundColor;
        gradientDrawable.setColor(this.backgroundColor);
        makeTextViewVisible();
    }

    public void setText(String text){
        this.text = changeTextToInitials(text);
        tv.setText(this.text);
        makeTextViewVisible();
    }

    private void makeTextViewVisible() {
        setBackgroundResource(0);
        setBackground(gradientDrawable);
        tv.setVisibility(View.VISIBLE);
    }

    private void applyAttrs(Context context, AttributeSet attrs){
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.InitialsRoundView, 0, 0);
        try {
            backgroundColor = a.getColor(R.styleable.InitialsRoundView_roundColor, Color.GRAY);
            text = a.getString(R.styleable.InitialsRoundView_initials);
            text = changeTextToInitials(text);
        } catch (Exception e){
            e.printStackTrace();

        }finally {
            a.recycle();
        }
    }

    private String changeTextToInitials(String text) {
        if(text != null && text.length() > 0){
            if(text.length() <=2){
                return text;
            }
            String[] split = text.split(" ");
            String result = String.valueOf(split[0].charAt(0));
            if(split.length >= 2){
                result += String.valueOf(split[1].charAt(0));
            }
            return result;
        } else{
            return null;
        }
    }

    private void addChildren(Context context){
        gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setColor(backgroundColor);
        setBackground(gradientDrawable);
        tv = new TextView(context);
        tv.setText(text);
        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        tv.setTextColor(Color.WHITE);
        tv.setGravity(Gravity.CENTER);
        int padding = getResources().getDimensionPixelOffset(R.dimen.text_margin);
        tv.setPadding(padding,padding,padding,padding);
        addView(tv);
        FrameLayout.LayoutParams params = (LayoutParams) tv.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        tv.setLayoutParams(params);
    }


}
