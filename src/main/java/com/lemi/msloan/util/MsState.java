package com.lemi.msloan.util;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chenglijuan
 * @Data: 2019/3/11  上午11:02
 * @Decription:
 * @Modified:
 */
public class MsState {


    public static String excelTitle = "注意：请按照表格规则填写\n1.订单信息表导出的是所有提交资料中的订单\n2.“审核状态”列请填写";

    //资料审核中
    public static List<String> auditing = new ArrayList<String>();

    //审批通过
    public static List<String> auditSuccess = new ArrayList<String>();

    //审批不通过
    public static List<String> auditFault = new ArrayList<String>();


    static {
        auditing.add("贷款申请");
        auditing.add("录入");
        auditing.add("支行复核");
        auditing.add("支行行长");
        auditing.add("分行评审小组");
        auditing.add("分行评审小组组长");
        auditing.add("等待补件");
        auditing.add("继续跟踪");
        auditing.add("分行零售风险部总经理助理/副总经理");
        auditing.add("分行零售风险部总经理");
        auditing.add("分行副行长");
        auditing.add("分行行长");
        auditing.add("总行评审小组");
        auditing.add("总行零售风险部评审中心总经理");
        auditing.add("总行零售风险部副总经理");
        auditing.add("总行零售风险部总经理");
        auditing.add("资料审核中");

        auditSuccess.add("审批通过");
        auditSuccess.add("等待发放");
        auditSuccess.add("分行放款审查");
        auditSuccess.add("分行放款审批");
        auditSuccess.add("完成贷款发放");

        auditFault.add("审批未通过");
        auditFault.add("客户取消贷款");
        auditFault.add("拒绝申请");

        excelTitle =  excelTitle + auditing.toString() + auditSuccess.toString() + auditFault.toString();
        System.out.println(excelTitle);
    }


    public static String getOrderState(String strState){

        String orderState = "";

       if(auditing.contains(strState)){
           orderState = "资料审核中";
       }
        if(StringUtils.isBlank(orderState)){
            if(auditSuccess.contains(strState)){
                orderState = "审批通过";
            }
        }
        if(StringUtils.isBlank(orderState)){
            if(auditFault.contains(strState)){
                orderState = "审批未通过";
            }
        }
        return orderState;

    }

    public static void main(String[] args) {
        getOrderState("支行复核");
        System.out.println(getOrderState("支行复核"));
    }


}
