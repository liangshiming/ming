package ming.framework.core.service.cache.provider;

import lombok.extern.slf4j.Slf4j;
import ming.framework.core.dao.ErrorCodeMapper;
import ming.framework.core.po.ErrorCode;
import ming.framework.core.po.ServiceApi;
import ming.framework.core.service.cache.CacheProvider;
import ming.framework.util.ReflectUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
@Service
public class ErrorCodeCacheProvider implements CacheProvider {
	private static final String TYPE = "ERROR_CODE";
	private Map<String, ErrorCode> cacheMap = new TreeMap<>();

	@Resource
	private ErrorCodeMapper errorCodeMapper;

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public void init() {
		if (cacheMap.isEmpty()) {
			synchronized (cacheMap) {
				if (cacheMap.isEmpty()) {
					forceInit();
				}
			}
		}
	}

	@Override
	public synchronized void forceInit() {
		log.debug("初始化缓存:{}", getType());
		List<ErrorCode> list = errorCodeMapper.selectAll();
		cacheMap = ReflectUtil.reflectToMap(list, "errCode");
	}

	@Override
	public Map<String, ErrorCode> get() {
		init();
		return cacheMap;
	}
}
