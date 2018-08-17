package ming.admin.controller;

import ming.framework.core.controller.BaseGraphCodeController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class GraphCodeController extends BaseGraphCodeController {

	@RequestMapping(value = "/getGraphCode")
	@Override
	public void getGraphCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super.getGraphCode(request, response);
	}

	@Override
	protected void setGraphCode(String graphCodeText) {

	}
}
