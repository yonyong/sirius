package top.yonyong.sirius.cmd;

import top.yonyong.sirius.exception.MyException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author yonyong
 **/
public class ExcuteCommandUtil {

    public static String excute(String command){
        System.out.println("执行命令[ " + command + "]");
        String result = null;
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        if (os != null && os.toLowerCase().contains("linux")) {
            try {
                String[] cmd = new String[]{"/bin/sh", "-c",command};
                Process ps = Runtime.getRuntime().exec(cmd);

                BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    //执行结果加上回车
                    sb.append(line).append("\n");
                }
                result = sb.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }else if (os != null && os.toLowerCase().startsWith("win")){
            try{
                Process process = Runtime.getRuntime().exec("cmd.exe /c " + command);
                int status = process.waitFor();

                InputStream in = process.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                StringBuffer sb = new StringBuffer();
                String line;
                while((line = br.readLine()) != null) {
                    //执行结果加上回车
                    sb.append(line).append("\n");
                }
                result = sb.toString();
            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }
        throw new MyException("os unknown");
    }
}
