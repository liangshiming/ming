package ming.framework.core.po;

import lombok.Data;

import java.util.Date;

@Data
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

}