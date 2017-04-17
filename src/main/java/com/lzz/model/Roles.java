package com.lzz.model;

/**
 * Created by lzz on 17/4/15.
 */
public class Roles {
    private int id;
    private String roleName;
    private String service;
    private String type;
    private String pingUrl;
    private long metric;
    private String clientId;
    private String members;
    private int addTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public long getMetric() {
        return metric;
    }

    public void setMetric(int metric) {
        this.metric = metric;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }
}