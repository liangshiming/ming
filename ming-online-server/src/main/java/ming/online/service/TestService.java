package ming.online.service;

import ming.framework.core.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * Created by Jrick on 2018/1/7.
 */
@Service
public class TestService extends BaseService {
    public String test(String agrs) {
        return "hello world";
    }
}
