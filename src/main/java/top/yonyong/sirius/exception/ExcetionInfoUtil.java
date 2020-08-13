package top.yonyong.sirius.exception;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author yonyong
 */
public class ExcetionInfoUtil {
    public static String getExceptionInformation(Exception exception) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        exception.printStackTrace(printStream);
        String exceptionInformation = new String(outputStream.toByteArray());
        printStream.close();
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exceptionInformation;
    }
}