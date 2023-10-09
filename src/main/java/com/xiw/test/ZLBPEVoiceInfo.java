package com.xiw.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 浙里办票票据详细信息DTO
 */
public class ZLBPEVoiceInfo {
    /**
     * 票据标识
     */
    private String eInvoiceId;

    /**
     * 票据号码
     */
    private String eInvoicdNumber;

    /**
     * 票据代码
     */
    private String eInvoicdCode;

    /**
     * 校验码
     */
    private String randomNumber;

    /**
     * 票据种类
     */
    private String eInvoicdType;

    /**
     * 总金额
     * <p>单位：元，精确到分；100.00</p>
     */
    private String totalAmount;

    /**
     * 开票日期
     * <p>yyyymmdd格式</p>
     */
    private String issueDate;

    /**
     * 开票单位名称
     */
    private String invoicingPartyName;

    /**
     * 交款人类型
     * <p>1:个人 2:单位</p>
     */
    private String payerPartyType;

    /**
     * 交款人名称
     */
    private String payerPartyName;

    /**
     * 票据是否锁定
     * <p>N未锁定，Y锁定</p>
     */
    private String isLocked;

    /**
     * 锁定原因
     */
    private String lockedReason;

    /**
     * 锁定日期
     * <p>yyyymmdd格式</p>
     */
    private String lockedDate;

    /**
     * 锁定机构名称
     */
    private String lockCorpName;

    /**
     * 票据XML详情(具体xml规范，后续提供)
     */
    private String eInvoiceXML;

    /**
     * 票据PDF地址
     */
    private String eInvoicePDF;

    /**
     * 医疗收费明细清单图片url
     * <p>如果医疗票据无收费明细清单，则此值为空</p>
     */
    private String detailPDF;

    /**
     * 票据换开状态
     * <p>0 未换开 1 已换开</p>
     */
    private String switchStatus;

    /**
     * 报销记录
     */
    private List<ZLBPReimburse> reimburseList;

    public ZLBPEVoiceInfo(JSONObject o){
        this.eInvoiceId = o.getString("eInvoiceId");
        this.eInvoicdNumber = o.getString("eInvoicdNumber");
        this.eInvoicdCode = o.getString("eInvoicdCode");
        this.randomNumber = o.getString("randomNumber");
        this.eInvoicdType = o.getString("eInvoicdType");
        this.totalAmount = o.getString("totalAmount");
        this.issueDate = o.getString("issueDate");
        this.invoicingPartyName = o.getString("invoicingPartyName");
        this.payerPartyType = o.getString("payerPartyType");
        this.payerPartyName = o.getString("payerPartyName");
        this.isLocked = o.getString("isLocked");
        this.lockedReason = o.getString("lockedReason");
        this.lockedDate = o.getString("lockedDate");
        this.lockCorpName = o.getString("lockCorpName");
        this.eInvoiceXML = o.getString("eInvoiceXML");
        this.eInvoicePDF = o.getString("eInvoicePDF");
        this.detailPDF = o.getString("detailPDF");
        this.switchStatus = o.getString("switchStatus");
        JSONArray reimburseList = o.getJSONArray("reimburseList");
        this.reimburseList = reimburseList.stream().map(e->new ZLBPReimburse((JSONObject)e)).collect(Collectors.toList());
    }

    public ZLBPEVoiceInfo(Object o){

    }

    public String geteInvoiceId() {
        return eInvoiceId;
    }

    public void seteInvoiceId(String eInvoiceId) {
        this.eInvoiceId = eInvoiceId;
    }

    public String geteInvoicdNumber() {
        return eInvoicdNumber;
    }

    public void seteInvoicdNumber(String eInvoicdNumber) {
        this.eInvoicdNumber = eInvoicdNumber;
    }

    public String geteInvoicdCode() {
        return eInvoicdCode;
    }

    public void seteInvoicdCode(String eInvoicdCode) {
        this.eInvoicdCode = eInvoicdCode;
    }

    public String getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    }

    public String geteInvoicdType() {
        return eInvoicdType;
    }

    public void seteInvoicdType(String eInvoicdType) {
        this.eInvoicdType = eInvoicdType;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getInvoicingPartyName() {
        return invoicingPartyName;
    }

    public void setInvoicingPartyName(String invoicingPartyName) {
        this.invoicingPartyName = invoicingPartyName;
    }

    public String getPayerPartyType() {
        return payerPartyType;
    }

    public void setPayerPartyType(String payerPartyType) {
        this.payerPartyType = payerPartyType;
    }

    public String getPayerPartyName() {
        return payerPartyName;
    }

    public void setPayerPartyName(String payerPartyName) {
        this.payerPartyName = payerPartyName;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    public String getLockedReason() {
        return lockedReason;
    }

    public void setLockedReason(String lockedReason) {
        this.lockedReason = lockedReason;
    }

    public String getLockedDate() {
        return lockedDate;
    }

    public void setLockedDate(String lockedDate) {
        this.lockedDate = lockedDate;
    }

    public String getLockCorpName() {
        return lockCorpName;
    }

    public void setLockCorpName(String lockCorpName) {
        this.lockCorpName = lockCorpName;
    }

    public String geteInvoiceXML() {
        return eInvoiceXML;
    }

    public void seteInvoiceXML(String eInvoiceXML) {
        this.eInvoiceXML = eInvoiceXML;
    }

    public String geteInvoicePDF() {
        return eInvoicePDF;
    }

    public void seteInvoicePDF(String eInvoicePDF) {
        this.eInvoicePDF = eInvoicePDF;
    }

    public String getDetailPDF() {
        return detailPDF;
    }

    public void setDetailPDF(String detailPDF) {
        this.detailPDF = detailPDF;
    }

    public String getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(String switchStatus) {
        this.switchStatus = switchStatus;
    }

    public List<ZLBPReimburse> getReimburseList() {
        return reimburseList;
    }

    public void setReimburseList(List<ZLBPReimburse> reimburseList) {
        this.reimburseList = reimburseList;
    }
}
