package ming.framework.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RedisService extends RedisPool<String, String> {


	public boolean hmsetExpire(String key, long timeout, Map<String, String> map) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {

				BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(key);
				boundHashOperations.putAll(map);

				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] keyByte = serializer.serialize(key);
				connection.expire(keyByte, timeout);
				return Boolean.TRUE;
			}
		});
		return result;
	}

	public Map<String, String> hmgetExpire(String key, long timeout) {
		Map<String, String> result = redisTemplate.execute(new RedisCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInRedis(RedisConnection connection) throws DataAccessException {

				BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(key);
				Map<String, String> data = boundHashOperations.entries();

				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] keyByte = serializer.serialize(key);
				connection.expire(keyByte, timeout);
				return data;
			}
		});
		return result;
	}

	public Boolean hsetExpire(String key, String itemKey, String value, long timeout) {
		Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				Map<String, String> map = new HashMap<>();
				map.put(itemKey, value);
				BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(key);
				boundHashOperations.putAll(map);

				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] keyByte = serializer.serialize(key);
				connection.expire(keyByte, timeout);
				return true;
			}
		});
		return result;
	}

	public boolean hdelExpire(String key, String itemKey, long timeout) {
		Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] keyByte = serializer.serialize(key);
				byte[] itemKeyByte = serializer.serialize(itemKey);

				connection.hDel(keyByte, itemKeyByte);
				connection.expire(keyByte, timeout);
				return true;
			}
		});
		return result;
	}

	public boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	public boolean set(String key, long timeout, String value) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] keyByte = serializer.serialize(key);
				byte[] valueByte = serializer.serialize(value);
				connection.setEx(keyByte, timeout, valueByte);
				return true;
			}
		});
		return result;
	}

	public String get(String key) {
		String result = redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] keyByte = serializer.serialize(key);
				return serializer.deserialize(connection.get(keyByte));
			}
		});
		return result;
	}

	public boolean incr(String key, long timeout) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] keyByte = serializer.serialize(key);
				connection.incr(keyByte);
				connection.expire(keyByte, timeout);
				return true;
			}
		});
		return result;
	}

	public void delete(String key) {
		redisTemplate.delete(key);
	}

}

