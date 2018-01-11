package ming.framework.core.service.cache;

import ming.framework.core.po.ServiceApi;
import ming.framework.core.service.cache.provider.ServiceApiCacheProvider;
import ming.framework.util.StrUtil;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by Jrick on 2018/1/11.
 */
public class CacheService {
    @Resource
    CacheComposite cacheComposite;

    @PostConstruct
    public void init() {
        cacheComposite.init();
    }

    public void refresh(String cacheType) {
        cacheComposite.refresh(cacheType);
    }

    private <T> T getProvider(Class<T> clazz) {
        return cacheComposite.getProvider(clazz);
    }

    // --------------------------------

    public ServiceApi getServiceApi(String apiName, String tradeWay, String apiVersion) {
        String key = StrUtil.join(apiName, tradeWay, apiVersion);
        ServiceApi config = getProvider(ServiceApiCacheProvider.class).get().get(key);
        return config;
    }
}
