package com.lemi.msloan.response;

/**
 * @Author: chenglijuan
 * @Data: 2019/5/11  下午4:57
 * @Decription:
 * @Modified:
 */
public class RespectSummayResult {

    private String title;

    private String[] data;

    private String[] xAxisData;

    private String[] legend;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public String[] getxAxisData() {
        return xAxisData;
    }

    public void setxAxisData(String[] xAxisData) {
        this.xAxisData = xAxisData;
    }

    public String[] getLegend() {
        return legend;
    }

    public void setLegend(String[] legend) {
        this.legend = legend;
    }
}
