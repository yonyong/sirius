package top.yonyong.sirius.system;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yonyong
 **/
public class DefaultSiriusSystemComputerInfo {

    private static final Map<String, String> MAP = System.getenv();

    public static String getUserName() {
        //获取用户名
        return MAP.get("USERNAME");
    }

    public static String getPCName() {
        //获取计算机名
        return MAP.get("COMPUTERNAME");
    }

    public static String getPCDomain() {
        //获取计算机域名
        return MAP.get("USERDOMAIN");
    }

    public static String getIP() {
        //本地ip地址
        InetAddress addr;
        try {
            addr = InetAddress.getLocalHost();
            return addr.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static List<String> getWinDisk() {
        //获取文件系统使用率
        // 操作系统
        List<String> list = new ArrayList<String>();
        for (char c = 'A'; c <= 'Z'; c++) {
            String dirName = c + ":/";
            File win = new File(dirName);
            if (win.exists()) {
                long total = (long) win.getTotalSpace();
                long free = (long) win.getFreeSpace();
                Double compare = (Double) (1 - free * 1.0 / total) * 100;
                String str = c + ":盘  已使用 " + compare.intValue() + "%";
                list.add(str);
            }
        }
        return list;
    }
}
