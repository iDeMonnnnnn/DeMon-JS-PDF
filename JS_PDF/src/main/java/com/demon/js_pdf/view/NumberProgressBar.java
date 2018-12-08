package com.demon.js_pdf.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.demon.js_pdf.R;

/**
 * @author DeMon
 * @date 2018/1/11
 * @description
 */
public class NumberProgressBar extends ProgressBar {
    /**
     * 画笔对象的引用
     */
    private Paint paint;
    /**
     * 当前进度
     */
    private int progress;
    /**
     * 最大进度
     */
    private int max;
    /**
     * 中间进度百分比的字符串的颜色
     */
    private int textColor;
    /**
     * 中间圆的颜色
     */
    private int circleColor;
    /**
     * 中间进度百分比的字符串的字体
     */
    private float textSize;

    public NumberProgressBar(Context context) {
        this(context, null);
    }

    public NumberProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberProgressBar);
        textColor = mTypedArray.getColor(R.styleable.NumberProgressBar_nTextColor, Color.BLUE);
        textSize = mTypedArray.getDimension(R.styleable.NumberProgressBar_nTextSize, 15);
        max = mTypedArray.getInteger(R.styleable.NumberProgressBar_nMax, 100);
        circleColor = mTypedArray.getColor(R.styleable.NumberProgressBar_nCircleColor, Color.GRAY);
        mTypedArray.recycle();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centre = getWidth() / 2; //获取圆心的x坐标
        float mPadding = (centre / 131) * 35;//外圈进度条的环的宽度
        paint.setStrokeWidth(0);
        paint.setColor(circleColor);
        canvas.drawCircle(centre, centre, centre - mPadding, paint);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT_BOLD); //设置字体
        /*
         * 画进度百分比
         */
        int percent = (int) (((float) progress / (float) max) * 100);  //中间的进度百分比，先转换成float在进行除法运算，不然都为0
        float textWidth = paint.measureText(percent + "%");   //测量字体宽度，我们需要根据字体的宽度设置在圆环中间
        canvas.drawText(percent + "%", centre - textWidth / 2, centre + textSize / 2, paint); //画出进度百分比
    }


    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步
     * 刷新界面调用postInvalidate()能在非UI线程刷新
     *
     * @param progress
     */
    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
        }

    }

}
