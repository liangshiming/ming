package ming.framework.core.controller;

import com.google.code.kaptcha.Producer;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class BaseGraphCodeController {

	@Resource
	private Producer captchaProducer;

	protected void getGraphCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setDateHeader("Expires", 0L);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
		String graphCodeText = this.captchaProducer.createText();
		setGraphCode(request,graphCodeText);
		BufferedImage bufferedImage = this.captchaProducer.createImage(graphCodeText);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bufferedImage, "jpg", out);

		try {
			out.flush();
		} finally {
			out.close();
		}

	}

	protected abstract void setGraphCode(HttpServletRequest request, String graphCodeText);
}
