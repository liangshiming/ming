package ming.framework.core.po;

import java.util.Date;

import lombok.Data;

/**
 * Created by Jrick on 2018/1/11.
 */
@Data
public class ServiceApi {
    private String apiId;

    private String apiName;

    private String description;

    private String service;

    private String function;

    private String tradeWay;

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
