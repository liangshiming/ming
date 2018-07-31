package ming.framework.core.service.cache.base;


import ming.framework.core.service.cache.CacheProvider;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Jrick on 2018/7/30.
 */
public abstract class BaseCacheComposite {

	protected static Map<String, CacheProvider> providerMap = new TreeMap<>();

	protected abstract void init();

	protected abstract String refresh(String cacheType);

	protected void put(CacheProvider... cacheProvider) {
		for (CacheProvider provider : cacheProvider) {
			providerMap.put(provider.getType(), provider);
		}
	}

	public <T> T getProvider(Class<T> clazz) {
		for (CacheProvider cacheProvider : providerMap.values()) {
			if (clazz.isInstance(cacheProvider)) {
				return (T) cacheProvider;
			}
		}
		return null;
	}

	public synchronized void forceInit() {
		for (CacheProvider cacheProvider : providerMap.values()) {
			cacheProvider.forceInit();
		}
	}
}
