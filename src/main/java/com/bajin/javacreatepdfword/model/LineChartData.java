package com.bajin.javacreatepdfword.model;

/**
 * @author bajin
 * @title: LineChartData
 * @projectName java-create-pdf-word
 * @description: TODO 创建折线图数据
 * @date 2020/4/11 10:59
 */
public class LineChartData {

    private String xAxis;

    private double yAxis;

    private String group;

    public String getxAxis() {
        return xAxis;
    }

    public void setxAxis(String xAxis) {
        this.xAxis = xAxis;
    }

    public double getyAxis() {
        return yAxis;
    }

    public void setyAxis(double yAxis) {
        this.yAxis = yAxis;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
