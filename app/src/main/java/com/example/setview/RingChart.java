package com.example.setview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class RingChart extends View {
    //屏幕宽高
    int screenW = 0;
    int screenH = 0;
    float density = 0;

    //绘制圆环的圆心坐标
    int centerX = 0;
    int centerY = 0;

    //扇形所在椭圆的四点坐标
    int left, top, right, bottom;
    int r= 250;

    //标签文本横线坐标
    float startX = 0; //偏移线段
    float startY = 0;
    float endX = 0;
    float endY = 0;
    float parX = 0; //水平线段
    float parY = 0;
    float textX = 0;
    float textY = 0;

    private List<ViewData> viewDataList = new ArrayList<>();
    private float sumValue = 0;//数据值的总和

    public float getSumValue() {
        return sumValue;
    }

    public void setSumValue(float sumValue) {
        this.sumValue = sumValue;
        invalidate();
    }

    //自定义动画
    private RingChartAnimation mAnimation;

    private Paint mPaint;

    public RingChart(Context context) {
        super(context);
        init();
    }

    public RingChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制扇形
        for (int i= 0; i < viewDataList.size(); i++){
            ViewData data = viewDataList.get(i);
            //设置画笔颜色
            mPaint.setColor(data.getColor());
            //绘制扇形
            canvas.drawArc(left,top,right,bottom,data.getCurrentStartAngle(),data.getAngle(),true,mPaint);

            float angle = data.getAngle()/2 + data.getCurrentStartAngle();
            float dx = (float) (Math.cos(2*angle * Math.PI /360) * r);
            float dy = (float) (Math.sin(2*angle * Math.PI /360) * r);
            //文字宽度
            float textWidth = mPaint.measureText((data.getText()+data.getPercentage()), 0, (data.getText()+data.getPercentage()).length());

            //设置画线方向
            directionLine(dx,dy,textWidth);
            // 绘制偏移线
            mPaint.setStrokeWidth(5);
            canvas.drawLine(startX, startY, endX, endY, mPaint);
            //绘制水平线（水平线段的开始坐标即为偏移线段的结束坐标
            canvas.drawLine(endX,endY,parX,parY,mPaint);

            //绘制标签文本
            mPaint.setTextSize(35);
            mPaint.setColor(Color.GRAY);
            canvas.drawText(data.getText()+data.getPercentage(),textX,textY,mPaint);
        }

        //绘制一个更小的圆将扇形遮挡中间的部分，让效果看起来变成一个圆环
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(centerX,centerY, (float) (r/1.4),mPaint);
        mPaint.setColor(Color.BLACK);
        float width = mPaint.measureText(sumValue+"元",0,(sumValue+"元").length());
        canvas.drawText(sumValue+"元",centerX - width/2,centerY+10,mPaint);

    }

    public void setData(List<ViewData> viewDataList) {
        this.viewDataList = viewDataList;
        initData(viewDataList);
        //启动动画
        startAnimation(mAnimation);
        invalidate();
    }

    public void directionLine(float dx, float dy, float textWidth){
        /**
         * 第一象限 x > 0 && y < 0
         * 第二象限 x < 0 && y < 0
         * 第三象限 x < 0 && y > 0
         * 第四象限 x > 0 && y > 0
         *
         * &&  并且
         * ||  或者
         */
        //偏差量
        float offsetX = 80f, offsetY = 60f, offset = 20f, offsetText = 10f;

        if (dx > 0 && dy < 0) {
            //右上
            startX = centerX + Math.abs(dx);
            startY = centerY - Math.abs(dy);
            endX = startX + offsetX;
            endY = startY - offsetY;

            parX = endX + offset;

            textX = parX + offsetText;
            textY = endY;
        } else if (dx < 0 && dy < 0) {
            //左上
            startX = centerX - Math.abs(dx);
            startY = centerY - Math.abs(dy);
            endX = startX - offsetX;
            endY = startY - offsetY;

            parX = endX - offset;

            textX = parX - offsetText - textWidth;
            textY = endY - offsetText;
        } else if (dx < 0 && dy > 0) {
            //左下
            startX = centerX - Math.abs(dx);
            startY = centerY + Math.abs(dy);
            endX = startX - offsetX;
            endY = startY + offsetY;

            parX = endX - offset;

            textX = parX - offsetText - textWidth;
            textY = endY;
        } else if (dx > 0 && dy > 0) {
            //右下
            startX = centerX + Math.abs(dx);
            startY = centerY + Math.abs(dy);
            endX = startX + offsetX;
            endY = startY + offsetY;

            parX = endX + offset;

            textX = parX + offsetText;
            textY = endY;
        }
        parY = endY; //水平线的y坐标只需要跟endY一样就可以了
    }

    //初始化数据
    private void initData(List<ViewData> mData){
        if (mData == null || mData.size() == 0) {
            return;
        }

        //根据数据条数确定颜色
        for (int i = 0; i < mData.size(); i++) {
            ViewData data = mData.get(i);
            sumValue += data.getValue();
            data.setColor(COLORS[i]);
        }

        //计算百分比和角度
        float currentStartAngle = 0f; //设置起始角度为0
        for (int i = 0; i < mData.size(); i++) {
            ViewData data = mData.get(i);
            //为每一段设置起始角度
            data.setCurrentStartAngle(currentStartAngle);
            //通过总和来计算百分比
            float percentage = data.getValue() / sumValue;
            //通过百分比来计算对应的角度
            float angle = percentage * 360;
            //设置数据
            data.setPercentage(percentage);
            data.setAngle(angle);
            currentStartAngle += angle;
        }
    }

    private void init(){
        getScreen();
        left = centerX - r;
        top = centerY - r;
        right = centerX + r;
        bottom = centerY + r;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setDither(true);//防止抖动
        mPaint.setStyle(Paint.Style.FILL);//画笔为填充
        //初始化动画
        mAnimation = new RingChartAnimation();
        mAnimation.setDuration(2000); //设置动画时间
    }

    //获取屏幕宽度和高度
    public void getScreen(){
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        density = metrics.density; //屏幕密度
        screenW = metrics.widthPixels; //单位为像素
        screenH = metrics.heightPixels;

        centerX = screenW/2;
        centerY = screenH/4;
    }

    public class RingChartAnimation extends Animation{
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 2.0f) {
                for (int i = 0; i < viewDataList.size(); i++) {
                    ViewData data = viewDataList.get(i);
                    //通过总和来计算百分比
                    float percentage = data.getValue() / sumValue;
                    //通过百分比来计算对应的角度
                    float angle = percentage * 360;
                    //根据插入时间来计算角度
                    angle = angle * interpolatedTime;
                    data.setAngle(angle);
                }
            } else {//默认显示效果
                for (int i = 0; i < viewDataList.size(); i++) {
                    //通过总和来计算百分比
                    ViewData data = viewDataList.get(i);
                    float percentage = data.getValue() / sumValue;
                    //通过百分比来计算对应的角度
                    float angle = percentage * 360;
                    data.setPercentage(percentage);
                    data.setAngle(angle);
                }
            }
            invalidate();
        }
    }

    //颜色
    public final static int[] COLORS = {
            Color.parseColor("#FFD778"), Color.parseColor("#FEA3C2"),
            Color.parseColor("#66DAD9"),Color.parseColor("#9092FF"),
            Color.parseColor("#A0E491"),Color.parseColor("#6BD9AC"),
            Color.parseColor("#75A2FF"),Color.parseColor("#6BD9AC")
    };
}
