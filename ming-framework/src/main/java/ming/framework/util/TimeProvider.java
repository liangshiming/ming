package ming.framework.util;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TimeProvider {

	public Date getCurrentTime() {
		return new Date();
	}

	public void setTime(Date time) {
	}

	public void restore() {
	}
}
