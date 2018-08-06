package ming.framework.core.service.cache;

import ming.framework.constant.CommonConst;
import ming.framework.core.service.cache.base.BaseCacheComposite;
import ming.framework.core.service.cache.provider.ErrorCodeCacheProvider;
import ming.framework.core.service.cache.provider.ServiceApiCacheProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Jrick on 2018/7/30.
 */
@Service
public class CacheComposite extends BaseCacheComposite {

	@Resource
	private ServiceApiCacheProvider serviceApiCacheProvider;

	@Resource
	private ErrorCodeCacheProvider errorCodeCacheProvider;

	private synchronized void initMap() {
		put(serviceApiCacheProvider,errorCodeCacheProvider);
	}

	@Override
	public synchronized void init() {
		initMap();
		for (CacheProvider cacheProvider : providerMap.values()) {
			cacheProvider.init();
		}
	}

	@Override
	protected String refresh(String cacheType) {
		String refreshStatus = CommonConst.YES;
		if (StringUtils.isBlank(cacheType)) {
			forceInit();
		} else if (providerMap.containsKey(cacheType)) {
			providerMap.get(cacheType).forceInit();
		} else {
			refreshStatus = CommonConst.NO;
		}
		return refreshStatus;
	}
}
