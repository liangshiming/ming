package ming.framework.core.service.cache.base;

/**
 * Created by Jrick on 2018/7/30.
 */
public abstract class BaseCacheService {

	protected abstract void init();

	protected abstract <T> T getProvider(Class<T> clazz);

}
