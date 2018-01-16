package ming.framework.core.service.cache;

import ming.framework.core.po.ServiceApi;
import ming.framework.core.service.cache.provider.ServiceApiCacheProvider;
import ming.framework.util.StrUtil;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by Jrick on 2018/1/11.
 */
@Service
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

    public ServiceApi getServiceApi(String apiName, String apiVersion) {
        String key = StrUtil.join(apiName,apiVersion);
        ServiceApi config = getProvider(ServiceApiCacheProvider.class).get().get(key);
        return config;
    }
}
