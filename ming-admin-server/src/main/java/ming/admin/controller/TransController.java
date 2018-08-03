package ming.admin.controller;

import ming.framework.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TransController extends BaseController {


	@RequestMapping(value = "/api", produces = "application/json; charset=UTF-8")
	@ResponseBody
	@Override
	public String request(HttpServletRequest request) {
		return super.request(request);
	}
}
