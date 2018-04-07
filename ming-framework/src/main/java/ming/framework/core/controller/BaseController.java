package ming.framework.core.controller;

import lombok.extern.slf4j.Slf4j;
import ming.framework.constant.RequestConst;
import ming.framework.core.po.ServiceApi;
import ming.framework.util.BeanFactory;
import ming.framework.util.CollectionUtil;
import ming.framework.util.JsonUtil;
import ming.framework.util.ReflectUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BaseController
 * Created by Jrick on 2018/1/1.
 */
@Slf4j
public abstract class BaseController {


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
        return paramMap;
    }

    private Map<String, Object> initParamMap(HttpServletRequest request) {
        Map<String, Object> paramMap = request.getParameterMap();
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            String[] valueArr = (String[]) entry.getValue();
            String value = "";
            if (valueArr != null && valueArr.length > 0 && valueArr[0] != null) {
                value = valueArr[0].trim();
            }
            map.put(entry.getKey(), value);
        }
        return map;
    }

    private Object callService(List<MultipartFile> file, Map<String, Object> paramMap) {
        String apiName = MapUtils.getString(paramMap, RequestConst.API_NAME);
        String apiVersion = MapUtils.getString(paramMap, RequestConst.API_VERSION);
        ServiceApi serviceApi = null;
        String paramClass = getParamClass(serviceApi);
        Object req = JsonUtil.toObj(JsonUtil.toJson(paramMap), paramClass);
        log.info("==========>调用接口 : {} , \n请求参数 : {}", JsonUtil.toJson(serviceApi), JsonUtil.toJson(req));
        fillFileList(req, file);

        Object service = getBean(serviceApi.getService());
        Object result = ReflectUtil.invoke(service, serviceApi.getFunction(), req);
        return result;
    }
    private String getParamClass(ServiceApi serviceApi) {
        Object service = getBean(serviceApi.getService());
        Method method = ReflectUtil.getMethod(service.getClass(), serviceApi.getFunction());
        return method.getParameterTypes()[0].getName();
    }
    private Object getBean(String serviceName) {
        String formatService = formatService(serviceName);
        Object service = BeanFactory.getBean(formatService);
        return service;
    }

    private String formatService(String service) {
        return StringUtils.uncapitalize(service);
    }
    private void fillFileList(Object req, List<MultipartFile> fileList) {
        if (CollectionUtil.isEmpty(fileList)) {
            return;
        }
        ReflectUtil.setFiledValue(req, "fileList", fileList);
    }
}
