package ming.token;

import ming.constant.TokenItem;
import ming.framework.token.BaseTokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenService extends BaseTokenService {

	private static final String SERVER_NAME = "ADMIN";
	private static final long VALUE_TIME_OUT = 1800;

	public void putItem(String token, TokenItem itemKey, String value) {
		super.putItem(token, itemKey.toString(), value);
	}

	public String getItem(String token, TokenItem itemKey) {
		return super.getItem(token, itemKey.toString());
	}

	public void removeItem(String token, TokenItem itemKey) {
		super.removeItem(token, itemKey.toString());
	}

	@Override
	protected String getServerName() {
		return SERVER_NAME;
	}

	@Override
	protected long getSessionTimeOut() {
		return VALUE_TIME_OUT;
	}
}
