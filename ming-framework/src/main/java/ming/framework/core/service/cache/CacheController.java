package ming.framework.core.service.cache;

import lombok.extern.slf4j.Slf4j;
import ming.framework.constant.CommonConst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Jrick on 2018/7/30.
 */
@Slf4j
@RestController
public class CacheController {

	@Resource
	private CacheService cacheservice;


	@RequestMapping("/cacheRefresh")
	public String cacheRefresh(@RequestParam(value = "cacheType", required = false) String cacheType) {
		try {
			String cacheTypeTemp = "";
			if (StringUtils.isNotBlank(cacheType)) {
				cacheTypeTemp = cacheType.toUpperCase();
			}
			return cacheservice.refresh(cacheTypeTemp);
		} catch (Exception e) {
			log.info("刷新缓存异常", e);
			return CommonConst.NO;
		}
	}
}
