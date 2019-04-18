package com.lemi.msloan.request;

/**
 * Created by Administrator on 2019/2/22.
 */
public class ChannelRequest extends BaseRequest {

    private String name;
    private String phone;
    private String chargePerson;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getChargePerson() {
        return chargePerson;
    }

    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
    }
}
