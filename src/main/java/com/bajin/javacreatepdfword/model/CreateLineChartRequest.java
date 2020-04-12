package com.bajin.javacreatepdfword.model;

import java.util.List;

/**
 * @author bajin
 * @title: CreateLineChartRequest
 * @projectName java-create-pdf-word
 * @description: TODO
 * @date 2020/4/11 11:36
 */
public class CreateLineChartRequest {

    /**
     * 折线图宽度
     */
    private int width;
    /**
     * 折线图高度
     */
    private int heigth;

    /**
     * 折线图保存地址
     */
    private String chartPath;

    /**
     * 折线图保存名称
     */
    private String chartName;

    /**
     * 折线图标题
     */
    private String title;

    /**
     * x轴名字
     */
    private String xName;

    /**
     * y轴名字
     */
    private String yName;

    /**
     * 绘制折线图所需数据
     */
    List<LineChartData> lineChartDataList;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public String getChartPath() {
        return chartPath;
    }

    public void setChartPath(String chartPath) {
        this.chartPath = chartPath;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getxName() {
        return xName;
    }

    public void setxName(String xName) {
        this.xName = xName;
    }

    public String getyName() {
        return yName;
    }

    public void setyName(String yName) {
        this.yName = yName;
    }

    public List<LineChartData> getLineChartDataList() {
        return lineChartDataList;
    }

    public void setLineChartDataList(List<LineChartData> lineChartDataList) {
        this.lineChartDataList = lineChartDataList;
    }
}
