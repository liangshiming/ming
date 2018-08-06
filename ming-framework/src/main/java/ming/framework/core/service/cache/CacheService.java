package ming.framework.core.service.cache;

import ming.framework.core.po.ErrorCode;
import ming.framework.core.po.ServiceApi;
import ming.framework.core.service.cache.base.BaseCacheService;
import ming.framework.core.service.cache.provider.ErrorCodeCacheProvider;
import ming.framework.core.service.cache.provider.ServiceApiCacheProvider;
import ming.framework.util.StrUtil;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by Jrick on 2018/7/30.
 */
@Service
public class CacheService extends BaseCacheService {

	@Resource
	private CacheComposite cacheComposite;

	@PostConstruct
	@Override
	protected void init() {
		cacheComposite.init();
	}

	@Override
	protected <T> T getProvider(Class<T> clazz) {
		return cacheComposite.getProvider(clazz);
	}

	public String refresh(String cacheType) {
		return cacheComposite.refresh(cacheType);
	}

	// --------------------------------

	public ServiceApi getServiceApi(String apiName, String apiVersion) {
		String key = StrUtil.join(apiName, apiVersion);
		ServiceApi config = getProvider(ServiceApiCacheProvider.class).get().get(key);
		return config;
	}
	public ErrorCode getErrorCode(String code) {
		return getProvider(ErrorCodeCacheProvider.class).get().get(code);
	}
}
