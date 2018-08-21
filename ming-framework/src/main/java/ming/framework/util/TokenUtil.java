package ming.framework.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class TokenUtil {

	private static final char[] EVEN = {'0', '2', '4', '6', '8'};
	private static final char[] ODD = {'1', '3', '5', '7', '9'};


	public static String getUnLoginToken() {
		return createRandomOddNum() + getUUID();
	}

	public static String getLoginToken() {
		return createRandomEvenNum() + getUUID();
	}

	public static String getReqToken() {
		return createRandomLetter(1) + getUUID();
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	private static String createRandomEvenNum() {
		return RandomStringUtils.random(1, EVEN);
	}

	private static String createRandomOddNum() {
		return RandomStringUtils.random(1, ODD);
	}

	private static String createRandomLetter(int size) {
		return RandomStringUtils.randomAlphabetic(size);
	}
}
