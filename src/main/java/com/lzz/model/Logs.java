package com.lzz.model;

/**
 * Created by lzz on 17/4/16.
 */
public class Logs {
    private int roleid;
    private String service;
    private String type;
    private String pingUrl;
    private int metricValue;
    private int metric;
    private String errorMessage;
    private int errorCode;
    private String members;
    private String clientId;
    private int addTime;
    private int day;
    private int hour;
    private int minute;

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPingUrl() {
        return pingUrl;
    }

    public void setPingUrl(String pingUrl) {
        this.pingUrl = pingUrl;
    }

    public int getMetricValue() {
        return metricValue;
    }

    public void setMetricValue(int metricValue) {
        this.metricValue = metricValue;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getMetric() {
        return metric;
    }

    public void setMetric(int metric) {
        this.metric = metric;
    }

    @Override
    public String toString() {
        return "Logs{" +
                "roleid=" + roleid +
                ", service='" + service + '\'' +
                ", type='" + type + '\'' +
                ", pingUrl='" + pingUrl + '\'' +
                ", metricValue=" + metricValue +
                ", metric=" + metric +
                ", errorMessage='" + errorMessage + '\'' +
                ", errorCode=" + errorCode +
                ", members='" + members + '\'' +
                ", addTime=" + addTime +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                '}';
    }
}
