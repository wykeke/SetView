package com.example.setview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * 自定义View基础学习
 */
public class SetViewDemo extends View {
    //屏幕宽高
    int screenW = 0;
    int screenH = 0;

    //设置画笔
    Paint paint = new Paint();

    public SetViewDemo(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setAntiAlias(true); //抗锯齿设置抗锯齿开关
//        paint.setStyle(Paint.Style.STROKE); //设置绘制模式：空心
        paint.setColor(Color.BLUE); //设置颜色：蓝色
//        paint.setStrokeWidth(30); //设置线条宽度：30

        //得到屏幕的宽和高，让圆从屏幕中央画起
        getScreen();
        int centerX = screenW/2;
        int centerY = screenH/2;
        Log.d(TAG, "onDraw: "+screenW+" "+screenH);
        Log.d(TAG, "onDraw: "+centerX+" "+centerY);

        //画圆（x坐标，y坐标，半径，paint）
        //（x，y）是这个圆的圆心坐标
        canvas.drawCircle(centerX,centerY,300,paint);
        //drawColor,再绘制区域统一涂上指定的颜色
        canvas.drawColor(Color.parseColor("#88880000")); //半透明红色
//
//        //画矩形 （矩形四条边的坐标
//        paint.setColor(Color.WHITE);//白色的矩形
//        canvas.drawRect(centerX - 350,centerY - 350,centerX + 350,centerY + 350,paint);
//        /**
//         * //drawRoundRect 圆角矩形(四个点的坐标，圆角的横向半径和纵向半径。
//         * canvas.drawRoundRect(100, 100, 500, 300, 50, 50, paint);
//         */
//
//        //画点 (点坐标
//        paint.setColor(Color.YELLOW);//黄色的中心点
//        //setStrokeCap可以设置线条端点形状，端点有圆头 (ROUND)、平头 (BUTT) 和方头 (SQUARE) 三种
//        // 这里让它绘制一个圆点
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawPoint(centerX,centerY,paint);
//
//        paint.setColor(Color.BLACK); //黑色的多个点（放在白色矩形的四个点上
//        //drawPoints可以画多个点，pts数组是点的坐标，每两个成一对，offset表示跳过数组的前几个数再开始记坐标
//        float[] pts = {0,0,centerX - 350,centerY - 350,centerX - 350,centerY + 350,centerX + 350,centerY - 350,centerX + 350,centerY + 350};
//        canvas.drawPoints(pts,2,8,paint);//跳过两个数（0，0），绘制8个数（4个点）
//
//        //画椭圆 (左、上、右、下四个边界点的坐标。
//        canvas.drawOval(centerX - 250,centerY - 100,centerX + 250, centerY + 100,paint);

//        //画线 (线的起点和终点坐标。
//        paint.setStrokeWidth(20);
//        canvas.drawLine(100,100,800,100,paint);
//
//        //多条线
//        paint.setColor(Color.BLACK);
//        float[] pts = {0,0,100,200,800,200,100,300,800,300};
//        canvas.drawLines(pts,2,8,paint);

//        //画弧形或者扇形
//        /**
//         * drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint)
//         * drawArc() 是使用一个椭圆来描述弧形的。
//         * left, top, right, bottom 描述的是这个弧形所在的椭圆；
//         * startAngle 是弧形的起始角度（x 轴的正向，即正右的方向，是 0 度的位置；顺时针为正角度，逆时针为负角度），
//         * sweepAngle 是弧形划过的角度；
//         * useCenter 表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形。
//         */
//        paint.setStyle(Paint.Style.FILL); //填充模式
//        //绘制扇形
//        canvas.drawArc(300,300,900,900,0,120,true,paint);
//        //绘制弧形
//        paint.setColor(Color.RED);
//        canvas.drawArc(300,300,900,900,180,90,false,paint);
//        //绘制弧线
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(5);
//        canvas.drawArc(300,300,900,900,-90,90,false,paint);

//        Path path = new Path(); //初始化Path对象
//
//        //使用path对图形进行描述（这里描述了一个心型）
//        path.addArc(200, 200, 400, 400, -225, 225);
//        path.arcTo(400, 200, 600, 400, -180, 225, false);
//        path.lineTo(500,542);
//
//        canvas.drawPath(path,paint);

//        //绘制bitmap
//        //在drawable包下放一张图片，用bitmap得到这张图片并绘制
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.tu);
//        canvas.drawBitmap(bitmap,100,100,paint);

//        //绘制文字
//        String text = "Hello World!";
//        paint.setTextSize(18); //设置文字大小
//        canvas.drawText(text, 100, 25, paint);
//        paint.setTextSize(36);
//        canvas.drawText(text, 100, 70, paint);
//        paint.setTextSize(60);
//        canvas.drawText(text, 100, 145, paint);
//        paint.setTextSize(84);
//        canvas.drawText(text, 100, 240, paint);

//        //线性渐变
//        //配色网站https://www.toptal.com/designers/colourcode
//        /**
//         * x0 y0 x1 y1：渐变的两个端点的位置
//         * color0 color1 是端点的颜色
//         * tile：端点范围之外的着色规则，类型是 TileMode。TileMode 一共有 3 个值可选：CLAMP, MIRROR 和 REPEAT。
//         * CLAMP 会在端点之外延续端点处的颜色；MIRROR 是镜像模式；REPEAT 是重复模式。
//         */
//        Shader shader = new LinearGradient(100,100,300,300
//                ,Color.parseColor("#6984C9")
//                ,Color.parseColor("#D072BB")
//                ,Shader.TileMode.REPEAT);
//        paint.setShader(shader);
//        canvas.drawRect(100,100,500,500,paint);
//        //画一条分界线显示一下
//        Paint linePaint = new Paint();
//        linePaint.setColor(Color.WHITE);
//        linePaint.setStrokeWidth(5);
//        //paint.setColor(Color.WHITE); //设置shader之后setColor失效
//        canvas.drawLine(100,500,500,100,linePaint);

//        //辐射渐变
//        /**
//         * centerX centerY：辐射中心的坐标
//         * radius：辐射半径
//         * centerColor：辐射中心的颜色
//         * edgeColor：辐射边缘的颜色
//         * tileMode：辐射范围之外的着色模式。
//         */
//        Shader shader = new RadialGradient(300,300,200
//                ,Color.parseColor("#6984C9")
//                ,Color.parseColor("#D072BB")
//                ,Shader.TileMode.CLAMP);
//        paint.setShader(shader);
//        canvas.drawCircle(300,300,200,paint);



    }

    //获取屏幕宽度和高度
    public void getScreen(){
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenW = metrics.widthPixels;
        screenH = metrics.heightPixels;
    }

}
