package ming.framework.core.service.cache.provider;

import ming.framework.core.dao.ServiceApiMapper;
import ming.framework.core.po.ServiceApi;
import ming.framework.core.service.cache.CacheProvider;
import ming.framework.util.ReflectUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ServiceApiCacheProvider implements CacheProvider {
	private static final String TYPE = "SERVICE_API";
	private Map<String, ServiceApi> cacheMap = new TreeMap<>();

	@Resource
	private ServiceApiMapper serviceApiMapper;

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
		List<ServiceApi> list = serviceApiMapper.selectAll();
		cacheMap = ReflectUtil.reflectToMap(list, "apiName", "apiVersion");
	}

	@Override
	public Map<String, ServiceApi> get() {
		init();
		return cacheMap;
	}
}
