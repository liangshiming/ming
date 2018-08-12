package ming.framework.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Base64;
import java.util.Random;

public class EncryptUtil {
	private static final int DEC_ASCII_LOW_A = (int) Character.valueOf('a');
	private static final int NUM_ALL_LETTER = 26;
	private static final Random random = new Random();

	public static String md5(String str) {
		return DigestUtils.md5Hex(str).toUpperCase();
	}

	public static String sha256(String str) {
		return DigestUtils.sha256Hex(str).toUpperCase();
	}

	public static String sha512(String str) {
		return DigestUtils.sha512Hex(str).toUpperCase();
	}

	public static String base64Encode(String str) {
		return Base64.getEncoder().encodeToString(str.getBytes());
	}

	public static String base64Decode(String str) {
		return new String(Base64.getDecoder().decode(str.getBytes()));
	}

	public static String bcryptEncode(String str) {
		final int startIndex = 7;
		int i = random.nextInt(NUM_ALL_LETTER);
		int round = getRound(i);
		String fullEncode = BCrypt.hashpw(sha512(str), BCrypt.gensalt(round));
		return fullEncode.substring(startIndex, fullEncode.length()) + intToLetter(i);
	}

	public static boolean bcryptCheck(String str, String encode) {
		if (StringUtils.isBlank(encode)) {
			return false;
		}
		int i = letterToInt(encode.charAt(encode.length() - 1));
		int round = getRound(i);
		String fullEncode = "$2a$" + round + "$" + encode.substring(0, encode.length() - 1);
		return BCrypt.checkpw(sha512(str), fullEncode);
	}

	private static int letterToInt(char c) {
		return (int) c - DEC_ASCII_LOW_A;
	}

	private static String intToLetter(int i) {
		return Character.toString((char) (i + DEC_ASCII_LOW_A));
	}

	private static int getRound(int num) {
		final int roundBase = 10;
		final int modParam = 3;
		return roundBase + num % modParam;
	}
}
