package top.yonyong.sirius.system;

import java.util.TimeZone;

/**
 * @author yonyong
 **/
public class DefaultSiriusSystemOsInfo {

    public static String getOsName(){
        //操作系统名称
        return System.getProperty("os.name");
    }

    public static String getOsArch(){
        //操作系统的架构
        return System.getProperty("os.arch");
    }

    public static String getOsVersion(){
        //操作系统版本号
        return System.getProperty("os.version");
    }

    public static String getOsUser(){
        //操作系统用户名
        return System.getProperty("user.name");
    }

    public static String getOsUserHome(){
        //操作系统用户的主目录
        return System.getProperty("user.home");
    }

    public static String getOsFileSeparator(){
        //操作系统文件分隔符
        return System.getProperty("file.separator");
    }

    public static String getOsPathSeparator(){
        //操作系统路径分隔符
        return System.getProperty("path.separator");
    }

    public static String getOsLineSeparator(){
        //操作系统直线分隔符
        return System.getProperty("line.separator");
    }

    public static String getProgramePath(){
        //当前程序所在目录
        return System.getProperty("user.dir");
    }

    public static TimeZone getOsTimeZone(){
        //获取系统时区
        return TimeZone.getDefault();
    }
}
