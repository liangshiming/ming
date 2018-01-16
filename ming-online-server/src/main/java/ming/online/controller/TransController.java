package ming.online.controller;

import ming.framework.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jrick on 2018/1/7.
 */
@Controller
public class TransController extends BaseController {

    @RequestMapping(value = "/api", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @Override
    public String handle(HttpServletRequest request) {
        return super.handle(request);
    }
}
