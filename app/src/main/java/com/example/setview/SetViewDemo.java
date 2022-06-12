package com.example.setview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
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

    int color = 0xff00ff00;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setAntiAlias(true); //抗锯齿设置抗锯齿开关
//        paint.setStyle(Paint.Style.STROKE); //设置绘制模式：空心
        paint.setColor(color); //设置颜色：蓝色
//        paint.setStrokeWidth(30); //设置线条宽度：30

        //得到屏幕的宽和高，让圆从屏幕中央画起
        getScreen();
        int centerX = screenW/2;
        int centerY = screenH/2;
//        Log.d(TAG, "onDraw: "+screenW+" "+screenH);
//        Log.d(TAG, "onDraw: "+centerX+" "+centerY);

        /**
         * 画圆（x坐标，y坐标，半径，paint）
         *（x，y）是这个圆的圆心坐标
         */
        canvas.drawCircle(centerX,centerY,300,paint);
//        //drawColor,再绘制区域统一涂上指定的颜色
//        canvas.drawColor(Color.parseColor("#88880000")); //半透明红色
        /**
         * 在mainActivity设置动画颜色渐变效果
         * ObjectAnimator animator = ObjectAnimator.ofInt(setViewDemo,"color",0xffff0000,0xff00ff00);
         * animator.setEvaluator(new ArgbEvaluator()); //主动设置为ArgbEvaluator
         * animator.setDuration(5000);
         * animator.start();
         */

        /**
         * 画矩形 （矩形四条边的坐标
         * drawRoundRect 圆角矩形(四个点的坐标，圆角的横向半径和纵向半径。
         */
//        paint.setColor(Color.WHITE);//白色的矩形
//        canvas.drawRect(centerX - 350,centerY - 350,centerX + 350,centerY + 350,paint);
//        canvas.drawRoundRect(100, 100, 500, 300, 50, 50, paint);

        /**
         * 画点 (点坐标
         * setStrokeCap可以设置线条端点形状，端点有圆头 (ROUND)、平头 (BUTT) 和方头 (SQUARE) 三种
         * drawPoints可以画多个点，pts数组是点的坐标，每两个成一对，offset表示跳过数组的前几个数再开始记坐标
         */
//        paint.setColor(Color.YELLOW);//黄色的中心点
//        // 这里让它绘制一个圆点
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawPoint(centerX,centerY,paint);
//        //绘制多个点
//        paint.setColor(Color.BLACK); //黑色的多个点（放在白色矩形的四个点上
//        float[] pts = {0,0,centerX - 350,centerY - 350,centerX - 350,centerY + 350,centerX + 350,centerY - 350,centerX + 350,centerY + 350};
//        canvas.drawPoints(pts,2,8,paint);//跳过两个数（0，0），绘制8个数（4个点）

        /**
         * 画椭圆 (左、上、右、下四个边界点的坐标。
         */
//        canvas.drawOval(centerX - 250,centerY - 100,centerX + 250, centerY + 100,paint);

        /**
         * 画线 (线的起点和终点坐标。
         */
//        paint.setStrokeWidth(20);
//        canvas.drawLine(100,100,800,100,paint);
//        //多条线
//        paint.setColor(Color.BLACK);
//        float[] pts = {0,0,100,200,800,200,100,300,800,300};
//        canvas.drawLines(pts,2,8,paint);

        /**
         * 画弧形或者扇形
         * drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint)
         * drawArc() 是使用一个椭圆来描述弧形的。
         * left, top, right, bottom 描述的是这个弧形所在的椭圆；
         * startAngle 是弧形的起始角度（x 轴的正向，即正右的方向，是 0 度的位置；顺时针为正角度，逆时针为负角度），
         * sweepAngle 是弧形划过的角度；
         * useCenter 表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形。
         */
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

        /**
         * 使用path绘制
         */
//        Path path = new Path(); //初始化Path对象
//
//        //使用path对图形进行描述（这里描述了一个心型）
//        path.addArc(200, 200, 400, 400, -225, 225);
//        path.arcTo(400, 200, 600, 400, -180, 225, false);
//        path.lineTo(500,542);
//
//        canvas.drawPath(path,paint);

        /**
         * 绘制bitmap
         * canvas.drawBitmap(bitmap,0,0,paint);
         * (0,0)是bitmap的绘制起点
         */
//        //在drawable包下放一张图片，用bitmap得到这张图片并绘制
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.tu);
//        canvas.drawBitmap(bitmap,0,0,paint);
        /**
         * 范围裁切(范围裁切需要通过save和restore即使恢复绘制范围
         * canvas可以设置范围裁切，需要通过save和restore即使恢复绘制范围
         * canvas.clipRect(left, top, right, bottom);
         * canvas.clipPath()，其实和 clipRect() 用法完全一样，只是把参数换成了 Path ，所以能裁切的形状更多一些：
         */
//        canvas.save();
//        canvas.clipRect(110,110,500,500);
//        canvas.drawBitmap(bitmap,100,100,paint);
//        canvas.restore();

//        canvas.save();
//        canvas.clipPath(path); //使用上面绘制path时的path
//        canvas.drawBitmap(bitmap,100,100,paint);
//        canvas.restore();

        /**
         * 几何变换
         * 1.使用 Canvas 来做常见的二维变换；
         * 2.使用 Matrix 来做常见和不常见的二维变换；
         * 3.使用 Camera 来做三维变换。
         */

        /**
         * 1.使用 Canvas 来做常见的二维变换
         * 注意:canvas变换是移动坐标系,所以要先旋转后平移的操作时,平移的操作要写在前面
         * 平移
         * Canvas.translate(float dx, float dy)
         * 参数里的 dx 和 dy 表示横向和纵向的位移。
         *
         * 旋转
         *  Canvas.rotate(float degrees, float px, float py)
         * 参数里的 degrees 是旋转角度，方向是顺时针为正向； px 和 py 是轴心的位置。
         *
         * 放缩
         * Canvas.scale(float sx, float sy, float px, float py)
         * 参数里的 sx sy 是横向和纵向的放缩倍数； px py 是放缩的轴心。
         *
         * 错切
         * skew(float sx, float sy)
         * 参数里的 sx 和 sy 是 x 方向和 y 方向的错切系数。
         */
//        canvas.save(); //保存状态
//        canvas.translate(0,200);  //平移
//        canvas.rotate(45,centerX,centerY);  //旋转
//        canvas.scale(0.5f,0.5f,centerX,centerY);  //放缩
//        canvas.skew(0.2f,0); //错切
//        canvas.drawBitmap(bitmap,centerX - bitmap.getWidth()/2,centerY - bitmap.getHeight()/2,paint); //绘制bitmap
//        canvas.restore(); //恢复状态

        /**
         * 2.使用 Matrix 来做常见和不常见的二维变换；
         *      1.创建 Matrix 对象；
         *      2.调用 Matrix 的 (pre/post)Translate/Rotate/Scale/Skew() 方法来设置几何变换；
         *          pre表示在之前变换,就像上面canvas中的方法一样,post则是按顺序变换,即用post会更符合我们的思维
         *      3.使用 Canvas.setMatrix(matrix) 或 Canvas.concat(matrix) 来把几何变换应用到 Canvas。
         *
         * 使用 Matrix 来做自定义变换
         * Matrix.setPolyToPoly(float[] src, int srcIndex, float[] dst, int dstIndex, int pointCount) 用点对点映射的方式设置变换
         * 例如：(0, 0) -> (100, 100) 表示把 (0, 0) 位置的像素移动到 (100, 100) 的位置
         * 这个是单点的映射，单点映射可以实现平移。而多点的映射，就可以让绘制内容任意地扭曲。
         * 参数里，src 和 dst 是源点集合目标点集；srcIndex 和 dstIndex 是第一个点的偏移；
         * pointCount 是采集的点的个数（个数不能大于 4，因为大于 4 个点就无法计算变换了）。
         */
//        Matrix matrix = new Matrix();
//        //float pointsSrc = {left, top, right, top, left, bottom, right, bottom};
//        //float pointsDst = {left - 10, top + 50, right + 120, top - 90, left + 20, bottom + 30, right + 20, bottom + 60};
//        matrix.reset();
//        //matrix.setPolyToPoly(pointsSrc, 0, pointsDst, 0, 4);
//        matrix.postTranslate(0,200);
//        matrix.postRotate(45,centerX,centerY);
//
//        canvas.save();
//        canvas.concat(matrix);
//        canvas.drawBitmap(bitmap,centerX - bitmap.getWidth()/2,centerY - bitmap.getHeight()/2,paint);
//        canvas.restore();

        /**
         * 3.使用 Camera 来做三维变换
         * 注意:Camera 和 Canvas 一样也需要保存和恢复状态才能正常绘制
         * 三维旋转
         * Camera.rotate*() 一共有四个方法： rotateX(deg),rotateY(deg),rotateZ(deg),rotate(x, y, z)
         * 直接旋转图形会不对称,所以如果需要图形左右对称,
         * 需要配合上 Canvas.translate()，在三维旋转之前把绘制内容的中心点移动到原点，即旋转的轴心，然后在三维旋转后再把投影移动回来
         * Canvas 的几何变换顺序是反的，所以要把移动到中心的代码写在下面，把从中心移动回来的代码写在上面。
         *
         * 移动(很少使用
         * Camera.translate(float x, float y, float z) 移动
         *
         * 设置虚拟相机的位置
         * Camera.setLocation(x, y, z)
         * 它的参数的单位不是像素，而是 inch英寸。在 Camera 中，相机的默认位置是 (0, 0, -8)（英寸）。8 x 72 = 576，
         * 所以它的默认位置是 (0, 0, -576)（像素）。
         */
//        Camera camera = new Camera();
//        canvas.save();
//        camera.save(); //保存状态
//        camera.setLocation(0,0,200); //设置虚拟相机的位置
//        camera.rotateX(30); //旋转 Camera的三维空间
//        canvas.translate(centerX - bitmap.getWidth()/2,centerY - bitmap.getHeight()/2); //旋转之后把投影移动回来
//        camera.applyToCanvas(canvas); //把旋转投影到Canvas
//        canvas.translate(-(centerX - bitmap.getWidth()/2),-(centerY - bitmap.getHeight()/2));// 旋转之前把绘制内容移动到轴心（原点）
//        camera.restore(); //恢复状态
//        canvas.drawBitmap(bitmap,centerX - bitmap.getWidth()/2,centerY - bitmap.getHeight()/2,paint);
//        canvas.restore();


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
