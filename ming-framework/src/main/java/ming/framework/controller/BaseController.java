package ming.framework.controller;

import ming.framework.util.json.JsonUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Jrick on 2018/1/1.
 */
public abstract class BaseController {

    protected String handle(HttpServletRequest request) {
        List<MultipartFile> fileList = null;
        if (request instanceof MultipartHttpServletRequest) {
            fileList = ((MultipartHttpServletRequest) request).getFiles("fileList");
        }
        return this.handle(fileList, request);
    }

    private String handle(List<MultipartFile> file, HttpServletRequest request) {
        Map<String, Object> paramMap = null;
        String jsonRes = JsonUtil.toJson(null);
        return jsonRes;
    }
}
