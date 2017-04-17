package com.lzz.model;

/**
 * Created by lzz on 17/4/16.
 */
public class QueryParam {
    private String clientId;
    private int metric;
    private int metricValue;
    private String roleName;
    private String service;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
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
                ", roleName='" + roleName + '\'' +
                ", service='" + service + '\'' +
                ", members='" + members + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
