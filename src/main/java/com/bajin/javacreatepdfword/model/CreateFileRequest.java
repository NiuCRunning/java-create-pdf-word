package com.bajin.javacreatepdfword.model;

/**
 * @author bajin
 * @title: CreateFileRequest
 * @projectName java-create-pdf-word
 * @description: TODO 创建生成fpd或word的请求对象
 * @date 2020/4/11 9:57
 */
public class CreateFileRequest {

    /**
     * 生成pdf & word模板路径
     */
    private String templateFilePath;
    /**
     * 生成pdf & word模板名称
     */
    private String templateFileName;
    /**
     * pdf & word保存路径
     */
    private String saveFilePath;
    /**
     * pdf & word保存文件名称
     */
    private String saveFileName;
    /**
     * 生成pdf & word文件所需参数数据
     */
    private Object data;

    public String getTemplateFilePath() {
        return templateFilePath;
    }

    public void setTemplateFilePath(String templateFilePath) {
        this.templateFilePath = templateFilePath;
    }

    public String getTemplateFileName() {
        return templateFileName;
    }

    public void setTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
    }

    public String getSaveFilePath() {
        return saveFilePath;
    }

    public void setSaveFilePath(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    public String getSaveFileName() {
        return saveFileName;
    }

    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
