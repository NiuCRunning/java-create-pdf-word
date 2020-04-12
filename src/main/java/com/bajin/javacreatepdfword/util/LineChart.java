package com.bajin.javacreatepdfword.util;

import com.bajin.javacreatepdfword.model.CreateLineChartRequest;
import com.bajin.javacreatepdfword.model.LineChartData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;

/**
 * @author bajin
 * @title: LineChart
 * @projectName java-create-pdf-word
 * @description: TODO
 * @date 2020/4/11 10:53
 */
public class LineChart {
    private final static Logger log = LoggerFactory.getLogger(LineChart.class.getName());

    /**
     * 绘制折线图
     * @param request
     * @return
     */
    public static String drawLineChar(CreateLineChartRequest request) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (LineChartData lineChartData : request.getLineChartDataList()) {
            dataset.addValue(lineChartData.getyAxis(), lineChartData.getGroup(), lineChartData.getxAxis());
        }
        JFreeChart jFreeChart = ChartFactory.createLineChart(request.getTitle(), request.getxName(), request.getyName(), dataset, PlotOrientation.VERTICAL, true, false, false);
        File lineChartFile = new File(request.getChartPath() + File.separator + request.getChartName());
        setLineChartStyle(jFreeChart);

        try{
            ChartUtilities.saveChartAsJPEG(lineChartFile, jFreeChart, request.getWidth(), request.getHeigth());
        } catch (Exception ex) {
            log.error("生成折线图失败，失败原因：{}", ex.getMessage());
        }
        return lineChartFile.getAbsolutePath();
    }

    private static void setLineChartStyle(JFreeChart jFreeChart) {
        //设置背景颜色
        jFreeChart.setBackgroundPaint(Color.white);
        CategoryPlot categoryPlot = jFreeChart.getCategoryPlot();
        /**
         * 设置x轴样式
         */
        CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
        categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
        /**
         * 设置y轴样式
         */
        NumberAxis numberAxis = (NumberAxis) categoryPlot.getRangeAxis();
        //最小值
        numberAxis.setLowerBound(0);
        //最大值
        numberAxis.setUpperBound(50);
        //自动设置数据范围
        numberAxis.setAutoRange(true);
        //坐标轴数值间隔
        numberAxis.setTickUnit(new NumberTickUnit(10));
        //坐标轴颜色
        numberAxis.setTickMarkPaint(Color.black);
        //坐标标记大小
        numberAxis.setTickMarkStroke(new BasicStroke(0.5f));

        LineAndShapeRenderer lineAndShapeRenderer = new LineAndShapeRenderer();

        lineAndShapeRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        lineAndShapeRenderer.setBaseItemLabelsVisible(true);
        lineAndShapeRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
        categoryPlot.setRenderer(lineAndShapeRenderer);
    }

}
