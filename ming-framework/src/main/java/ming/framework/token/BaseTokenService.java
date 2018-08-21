package ming.framework.token;

import ming.framework.redis.RedisService;
import ming.framework.util.CollectionUtil;
import ming.framework.util.DateUtil;
import ming.framework.util.TokenUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseTokenService {

	public static final String TOKEN = "TOKEN";
	public static final String SPLITER = "|";

	@Resource
	private RedisService redisService;

	protected abstract String getServerName();

	protected abstract long getSessionTimeOut();

	public String createUnLoginToken() {
		String token = TokenUtil.getUnLoginToken();
		String fullKey = getTokenFullKey(token);
		createMap(fullKey);
		return token;
	}

	public String createLoginToken() {
		String token = TokenUtil.getLoginToken();
		String fullKey = getTokenFullKey(token);
		createMap(fullKey);
		return token;
	}

	public void setToken(String token, Map<String, String> valueMap) {
		redisService.hmsetExpire(getTokenFullKey(token), getSessionTimeOut(), valueMap);
	}

	private String getTokenFullKey(String token) {
		return getServerName() + SPLITER + TOKEN + SPLITER + token;
	}

	private void createMap(String fullKey) {
		Map<String, String> valueMap = new HashMap<>();
		valueMap.put("TIMESTAMP", DateUtil.getCurrentDateTimeStr());
		redisService.hmsetExpire(fullKey, getSessionTimeOut(), valueMap);
	}


	public void putItem(String token, String itemKey, String value) {
		redisService.hsetExpire(getTokenFullKey(token), itemKey, value, getSessionTimeOut());
	}

	public String getItem(String token, String itemKey) {
		Map<String, String> valueMap = getTokenMap(getTokenFullKey(token));
		return CollectionUtil.getStringValue(valueMap, itemKey);
	}

	private Map<String, String> getTokenMap(String key) {
		Map<String, String> valueMap = redisService.hmgetExpire(key, getSessionTimeOut());
		return valueMap;
	}

	public void removeItem(String token, String itemKey) {
		redisService.hdelExpire(getTokenFullKey(token), itemKey, getSessionTimeOut());
	}
}
