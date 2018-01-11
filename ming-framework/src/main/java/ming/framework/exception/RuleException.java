package ming.framework.exception;

/**
 * Created by Jrick on 2018/1/11.
 */
public class RuleException extends RuntimeException {

    private final transient Object body;
    private final String[] parameterArr;

    public RuleException(Object body, String... parameterArr) {
        this.body = body;
        this.parameterArr = parameterArr;
    }
}
