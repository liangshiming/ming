package ming.framework.core.service.cache;

import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import ming.framework.core.service.cache.provider.ServiceApiCacheProvider;
import ming.framework.exception.RuleException;
import ming.framework.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Jrick on 2018/1/11.
 */
@Service
public class CacheComposite implements CacheProvider {

    private static Map<String, CacheProvider> providerMap = new TreeMap<>();

    @Resource
    private ServiceApiCacheProvider serviceApiCacheProvider;


    private synchronized void initMap() {
        put(providerMap, serviceApiCacheProvider);
    }

    @Override
    public synchronized void forceInit() {
        for (CacheProvider cacheProvider : providerMap.values()) {
            cacheProvider.forceInit();
        }
    }

    @Override
    public Map<String, CacheProvider> get() {
        return (Map<String, CacheProvider>) providerMap;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public synchronized void init() {
        initMap();
        for (CacheProvider cacheProvider : providerMap.values()) {
            cacheProvider.init();
        }
    }

    private void put(Map<String, CacheProvider> map, CacheProvider... cacheProvider) {
        for (CacheProvider provider : cacheProvider) {
            map.put(provider.getType(), provider);
        }
    }

    @SuppressWarnings("unchecked")
    <T> T getProvider(Class<T> clazz) {
        for (CacheProvider cacheProvider : providerMap.values()) {
            if (clazz.isInstance(cacheProvider)) {
                return (T) cacheProvider;
            }
        }
        return null;
    }

    synchronized void refresh(String cacheType) {
        if (StringUtils.isBlank(cacheType)) {
            forceInit();
        } else if (providerMap.containsKey(cacheType)) {
            providerMap.get(cacheType).forceInit();
        } else {
            throw new RuleException(providerMap, "��������");
        }
    }

}
