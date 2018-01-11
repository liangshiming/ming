package ming.framework.core.controller;

import lombok.extern.slf4j.Slf4j;
import ming.framework.constant.RequestConst;
import ming.framework.core.po.ServiceApi;
import ming.framework.util.JsonUtil;
import ming.framework.util.ReflectUtil;
import org.apache.commons.collections.MapUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BaseController
 * Created by Jrick on 2018/1/1.
 */
@Slf4j
public abstract class BaseController {

    protected abstract String getApiType();

    protected String handle(HttpServletRequest request) {
        List<MultipartFile> fileList = null;
        if (request instanceof MultipartHttpServletRequest) {
            fileList = ((MultipartHttpServletRequest) request).getFiles("fileList");
        }
        return this.handle(fileList, request);
    }

    private String handle(List<MultipartFile> file, HttpServletRequest request) {
        Map<String, Object> paramMap = getParamMap(request);
        String jsonRes = JsonUtil.toJson(callService(file, paramMap));
        return jsonRes;
    }

    private Map<String, Object> getParamMap(HttpServletRequest request) {
        Map<String, Object> paramMap = initParamMap(request);
        paramMap.put(RequestConst.API_TYPE, getApiType());
        return paramMap;
    }

    private Map<String, Object> initParamMap(HttpServletRequest request) {
        Map<String, Object> paramMap = request.getParameterMap();
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            String value = (String) entry.getValue();
            if (value != null && value.length() > 0) {
                value = value.trim();
            }
            map.put(entry.getKey(), value);
        }
        return map;
    }

    private Object callService(List<MultipartFile> file, Map<String, Object> paramMap) {
        String apiName = MapUtils.getString(paramMap, RequestConst.API_NAME);
        String apiType = MapUtils.getString(paramMap, RequestConst.API_TYPE);
        String apiVersion = MapUtils.getString(paramMap, RequestConst.API_VERSION);
//        ServiceApi serviceApi = cacheService.getServiceApi(apiName, tradeWay, apiVersion);
        return null;
    }
}
