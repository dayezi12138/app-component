package com.zh.xfz.bean.activity;

/**
 * author : dayezi
 * data :2019/12/10
 * description:
 */
public class FileResponseData {


    /**
     * Code : 0
     * fileName : imageTemp.jpg
     * originalUrl : http://47.103.75.23:8031/UpLoad/201912/607e671b-1309-451a-9cc7-950427c22965.jpg
     * createTime : 2019-12-10
     */

    private int Code;
    private String fileName;
    private String originalUrl;
    private String createTime;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
