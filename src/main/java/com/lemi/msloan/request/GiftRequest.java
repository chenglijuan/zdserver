package com.lemi.msloan.request;


/**
 * Created by Administrator on 2018/10/30.
 */
public class GiftRequest extends BaseRequest {

    private Integer state;

    private Integer deleteState;


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Integer deleteState) {
        this.deleteState = deleteState;
    }
}
