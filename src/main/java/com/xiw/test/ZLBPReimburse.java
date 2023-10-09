package com.xiw.test;

import com.alibaba.fastjson.JSONObject;

/**
 * 报销记录
 */
public class ZLBPReimburse {
    /**
     * 报销单位统一社会信用代码(18位)
     */
    private String corpId;

    /**
     * 报销单位名称
     */
    private String corpName;

    /**
     * 报销金额
     * <p>单位：元，精确到分；100.00</p>
     */
    private String reimburseAmount;

    /**
     * 财务系统的记账凭证号
     * <p>[1,50]</p>
     */
    private String accNumber;

    /**
     * 入账（受理）日期，格式：yyyyMMdd
     */
    private String accDate;

    /**
     * 报销状态
     * <p>0 已报销 1 已撤销</p>
     */
    private String reimburseStatus;

    public ZLBPReimburse() {
    }

    public ZLBPReimburse(JSONObject o) {
        this.corpId = o.getString("corpId");
        this.corpName = o.getString("corpName");
        this.reimburseAmount = o.getString("reimburseAmount");
        this.accNumber = o.getString("accNumber");
        this.accDate = o.getString("accDate");
        this.reimburseStatus = o.getString("reimburseStatus");
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getReimburseAmount() {
        return reimburseAmount;
    }

    public void setReimburseAmount(String reimburseAmount) {
        this.reimburseAmount = reimburseAmount;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public String getAccDate() {
        return accDate;
    }

    public void setAccDate(String accDate) {
        this.accDate = accDate;
    }

    public String getReimburseStatus() {
        return reimburseStatus;
    }

    public void setReimburseStatus(String reimburseStatus) {
        this.reimburseStatus = reimburseStatus;
    }
}
