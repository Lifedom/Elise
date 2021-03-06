package site.zido.elise.utils;

/**
 * http状态码
 *
 * @author zido
 */
public class StatusCode {
    private StatusCode() {
    }

    public static final int CODE_200 = 200;
    public static final int CODE_300 = 300;

    public static boolean isSuccess(int code) {
        return code >= CODE_200 && code <= CODE_300;
    }
}
