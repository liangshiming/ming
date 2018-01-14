package ming.framework.core.service.cache.provider;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import ming.framework.core.dao.ServiceApiMapper;
import ming.framework.core.po.ServiceApi;
import ming.framework.core.service.cache.CacheProvider;
import ming.framework.core.service.cache.CacheTypeConst;
import ming.framework.util.ReflectUtil;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jrick on 2018/1/11.
 */
@Service
@Slf4j
public class ServiceApiCacheProvider implements CacheProvider {

    private static final String TYPE = CacheTypeConst.SERVICE_API;
    private static Map<String, ServiceApi> cacheMap = new TreeMap<>();

    @Resource
    private ServiceApiMapper mapper;

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void init() {
        if (cacheMap.size() == 0) {
            synchronized (cacheMap) {
                if (cacheMap.size() == 0) {
                    forceInit();
                }
            }
        }
    }

    @Override
    public synchronized void forceInit() {
        log.info("", getType());
        List<ServiceApi> list = mapper.selectAll();
        cacheMap = ReflectUtil.reflectToMap(list, "apiName", "tradeWay", "apiVersion");
    }

    @Override
    public Map<String, ServiceApi> get() {
        init();
        return cacheMap;
    }

}
