package com.bajin.javacreatepdfword.util;

import com.bajin.javacreatepdfword.model.CreateFileRequest;
import com.bajin.javacreatepdfword.model.CreateLineChartRequest;
import com.bajin.javacreatepdfword.model.DataModel;
import com.bajin.javacreatepdfword.model.LineChartData;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bajin
 * @title: WordUtil
 * @projectName java-create-pdf-word
 * @description: TODO
 * @date 2020/4/11 10:41
 */
public class WordUtil {
    private final static Logger log = LoggerFactory.getLogger(WordUtil.class.getName());
    private final static String encode = "utf-8";
    /**
     * 创建Configuration配置实例
     */
    private static Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);


    public static void main(String[] args) {
        CreateFileRequest createFileRequest = new CreateFileRequest();
        String classPath = PDFUtil.class.getClassLoader().getResource("").getPath();
        String templatePath = classPath + "templates";
        createFileRequest.setTemplateFilePath(templatePath);
        createFileRequest.setTemplateFileName("pdfTemplate.ftl");
        createFileRequest.setSaveFilePath("f:/pdf");
        createFileRequest.setSaveFileName("word.doc");
        CreateLineChartRequest lineChartRequest = new CreateLineChartRequest();
        lineChartRequest.setChartName("lineChart.jpg");
        lineChartRequest.setChartPath("f:/pdf/");
        lineChartRequest.setHeigth(300);
        lineChartRequest.setWidth(500);
        lineChartRequest.setTitle("折线图");
        lineChartRequest.setxName("x轴");
        lineChartRequest.setyName("y轴");
        List<LineChartData> lineChartDataList = new ArrayList<>();
        LineChartData lineChartData1 = new LineChartData();
        lineChartData1.setxAxis("2020-03-08");
        lineChartData1.setyAxis(Double.parseDouble("22"));
        lineChartData1.setGroup("组1");
        lineChartDataList.add(lineChartData1);
        LineChartData lineChartData2 = new LineChartData();
        lineChartData2.setxAxis("2020-03-18");
        lineChartData2.setyAxis(Double.parseDouble("40"));
        lineChartData2.setGroup("组1");
        lineChartDataList.add(lineChartData2);
        LineChartData lineChartData3 = new LineChartData();
        lineChartData3.setxAxis("2020-03-28");
        lineChartData3.setyAxis(Double.parseDouble("25"));
        lineChartData3.setGroup("组1");
        lineChartDataList.add(lineChartData3);
        LineChartData lineChartData4 = new LineChartData();
        lineChartData4.setxAxis("2020-03-08");
        lineChartData4.setyAxis(Double.parseDouble("28"));
        lineChartData4.setGroup("组2");
        lineChartDataList.add(lineChartData4);
        LineChartData lineChartData5 = new LineChartData();
        lineChartData5.setxAxis("2020-03-18");
        lineChartData5.setyAxis(Double.parseDouble("45"));
        lineChartData5.setGroup("组2");
        lineChartDataList.add(lineChartData5);
        LineChartData lineChartData6 = new LineChartData();
        lineChartData6.setxAxis("2020-03-28");
        lineChartData6.setyAxis(Double.parseDouble("18"));
        lineChartData6.setGroup("组2");
        lineChartDataList.add(lineChartData6);
        lineChartRequest.setLineChartDataList(lineChartDataList);
        String chartUrl = LineChart.drawLineChar(lineChartRequest);
        DataModel dataModel = setDate();
        dataModel.setLineChartUrl(chartUrl);
        createFileRequest.setData(dataModel);

        createWord(createFileRequest);
    }

    public static DataModel setDate() {
        DataModel dataModel = new DataModel();
        dataModel.setStatisticalA("A");
        dataModel.setStatisticalB("B");
        dataModel.setStatisticalC("C");
        dataModel.setStatisticalD("D");
        dataModel.setStatisticalE("E");
        dataModel.setStatisticalF("F");
        dataModel.setStatisticalG("G");
        dataModel.setStatisticalMonth("2020-03");
        dataModel.setStatisticalStratTime("2020-03-01");
        dataModel.setStatisticalEndTime("2020-03-31");

        CreateLineChartRequest request = new CreateLineChartRequest();
        request.setChartName("lineCart.jpg");
        request.setChartPath("f:/pdf");
        request.setHeigth(300);
        request.setWidth(500);
        request.setTitle("折线图");
        request.setxName("X轴");
        request.setyName("Y轴");

        List<LineChartData> lineChartDataList = new ArrayList<>();
        LineChartData lineChartDataA = new LineChartData();
        LineChartData lineChartDataB = new LineChartData();
        LineChartData lineChartDataC = new LineChartData();
        lineChartDataA.setxAxis("2020-03-03");
        lineChartDataA.setyAxis(Double.parseDouble("20"));
        lineChartDataA.setGroup("组一");
        lineChartDataB.setxAxis("2020-03-15");
        lineChartDataB.setyAxis(Double.parseDouble("48"));
        lineChartDataB.setGroup("组一");
        lineChartDataC.setxAxis("2020-03-23");
        lineChartDataC.setyAxis(Double.parseDouble("18"));
        lineChartDataC.setGroup("组一");
        lineChartDataList.add(lineChartDataA);
        lineChartDataList.add(lineChartDataB);
        lineChartDataList.add(lineChartDataC);
        request.setLineChartDataList(lineChartDataList);
        LineChart.drawLineChar(request);
        return dataModel;
    }

    public static void createWord(CreateFileRequest createPDFRequest) {
        Template t;
        Writer writer = null;
        String saveFilePath = createPDFRequest.getSaveFilePath() + File.separator + createPDFRequest.getSaveFileName();
        try {
            OutputStream outputStream = new FileOutputStream(new File(saveFilePath));
            writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            Configuration configuration = getConfiguration(createPDFRequest.getTemplateFilePath());
            t = configuration.getTemplate(createPDFRequest.getTemplateFileName(), "utf-8");
            t.process(createPDFRequest.getData(), writer);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    public static Configuration getConfiguration(String templateFilePath) {
        FileTemplateLoader fileTemplateLoader = null;
        try {
            fileTemplateLoader = new FileTemplateLoader(new File(templateFilePath));
        } catch (IOException ex) {
            log.error("生成FileTemplateLoader失败，失败原因：{}", ex.getMessage());
        }
        configuration.setTemplateLoader(fileTemplateLoader);
        configuration.setDefaultEncoding(encode);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        return configuration;
    }

}
