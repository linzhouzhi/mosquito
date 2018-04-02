package com.lzz.model;

/**
 * Created by lzz on 17/4/16.
 */
public class QueryParam {
    private String clientId;
    private int metric;
    private int metricValue;
    private Integer roleId;
    private String members;
    private String errorMessage;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getMetric() {
        return metric;
    }

    public void setMetric(int metric) {
        this.metric = metric;
    }

    public int getMetricValue() {
        return metricValue;
    }

    public void setMetricValue(int metricValue) {
        this.metricValue = metricValue;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "QueryParam{" +
                "clientId='" + clientId + '\'' +
                ", metric=" + metric +
                ", metricValue=" + metricValue +
                ", members='" + members + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
