package ming.admin.controller;

import ming.constant.TokenItem;
import ming.framework.core.controller.BaseGraphCodeController;
import ming.token.TokenService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class GraphCodeController extends BaseGraphCodeController {

	@Resource
	private TokenService tokenService;

	@RequestMapping(value = "/getGraphCode")
	@Override
	public void getGraphCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super.getGraphCode(request, response);
	}

	@Override
	protected void setGraphCode(HttpServletRequest request, String graphCodeText) {
		String token = request.getParameter("token");
		tokenService.putItem(token, TokenItem.GRAPH_CODE, graphCodeText);
	}
}
