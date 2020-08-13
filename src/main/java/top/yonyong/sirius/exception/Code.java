package top.yonyong.sirius.exception;

/**
 * @author yonyong
 */
public final class Code {
    /**
     * 禁止实例化
     */
    private Code(){}

    public final static Integer SC_OK_200 = 200;

    public final static Integer SC_INTERNAL_SERVER_ERROR_500 = 500;

    public final static Integer SC_NO_AUTHZ_403 = 403;

    //common error
    public final static Integer ACE_COMMON_FAIL = 4000;

    // exception error
    public final static Integer ACE_EXCEPTION = 4002;
}