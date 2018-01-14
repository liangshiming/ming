package ming.framework.core.po;

import java.util.Date;

public class ServiceApi {
    private String apiId;

    private String apiName;

    private String description;

    private String service;

    private String function;

    private String apiVersion;

    private String status;

    private String remark;

    private String author;

    private String needLogin;

    private String needToken;

    private String needReqToken;

    private String needPermit;

    private Date createTime;

    private Date lastUpdateTime;

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNeedLogin() {
        return needLogin;
    }

    public void setNeedLogin(String needLogin) {
        this.needLogin = needLogin;
    }

    public String getNeedToken() {
        return needToken;
    }

    public void setNeedToken(String needToken) {
        this.needToken = needToken;
    }

    public String getNeedReqToken() {
        return needReqToken;
    }

    public void setNeedReqToken(String needReqToken) {
        this.needReqToken = needReqToken;
    }

    public String getNeedPermit() {
        return needPermit;
    }

    public void setNeedPermit(String needPermit) {
        this.needPermit = needPermit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}