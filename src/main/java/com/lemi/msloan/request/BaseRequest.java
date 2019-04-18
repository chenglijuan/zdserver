package com.lemi.msloan.request;

/**
 * Created by WeiJinTechnology on 2017/7/10.
 */
public class BaseRequest {
    private Integer start;
    private Integer end;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public void setPager(Integer pageNum,Integer pageSize){
        if(pageNum!=null&&pageSize!=null){
            this.start=(pageNum-1)*pageSize;
            this.end=pageSize;
        }
    }
    private int curPage = 1; // 当前页
    private int pageSize = 10; // 每页多少行
    private int totalRow; // 共多少行
    private int startRow = 0;// 当前页起始行
    private int endRow;// 结束行
    private int totalPage; // 共多少页

    public int getCurPage() {
        return curPage;
    }
    public void setCurPage(int curPage) {
        if (curPage < 1) {
            curPage = 1;
        } else {
            startRow = pageSize * (curPage - 1);
        }
        endRow = startRow + pageSize > totalRow ? totalRow : startRow + pageSize;
        this.curPage = curPage;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        totalPage = (totalRow + pageSize - 1) / pageSize;
        this.totalRow = totalRow;
        if (totalPage < curPage) {
            curPage = totalPage;
            startRow = pageSize * (curPage - 1);
            endRow = totalRow;
        }
        endRow = startRow + pageSize > totalRow ? totalRow : startRow + pageSize;
    }

    public int getTotalPage() {

        return this.totalPage;
    }

}
