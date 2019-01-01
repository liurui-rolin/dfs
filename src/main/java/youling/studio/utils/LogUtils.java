package youling.studio.utils;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * @author liurui
 * @date 2018/12/31 下午4:29
 */
public class LogUtils {
    /**
     * 获取异常信息
     * @return
     */
    public static String getExceptionOut(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }
}
