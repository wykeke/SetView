package com.example.setview;

import android.util.Log;

import java.text.DecimalFormat;

public class ViewData {
    private String text; //标签文本
    private float value; //各标签数值
    private float Angle; //需要绘制的圆弧角度
    private float CurrentStartAngle; //当前起始角度
    private float Percentage; //百分比
    private int color; //各部分颜色

    public ViewData(String text, float value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getCurrentStartAngle() {
        return CurrentStartAngle;
    }

    public void setCurrentStartAngle(float currentStartAngle) {
        CurrentStartAngle = currentStartAngle;
    }

    public float getAngle() {
        return Angle;
    }

    public void setAngle(float angle) {
        Angle = angle;
    }

    public String getPercentage() {
        DecimalFormat decimalFormat = new DecimalFormat(".0");
        return decimalFormat.format(Percentage*100) + "%";
    }

    public void setPercentage(float percentage) {
        Percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
