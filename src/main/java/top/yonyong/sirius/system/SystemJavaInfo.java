package top.yonyong.sirius.system;

/**
 * @author yonyong
 **/
public class SystemJavaInfo {

    public static String getJavaVersion() {
        //java版本号
        return System.getProperty("java.version");
    }

    public static String getJavaVendor() {
        //java提供商名称
        return System.getProperty("java.vendor");
    }

    public static String getJavaVendorUrl() {
        //java提供商网站
        return System.getProperty("java.vendor.url");
    }

    public static String getJavaHome() {
        //jre目录
        return System.getProperty("java.home");
    }

    public static String getJVMTotalMemory() {
        //JVM可以使用的总内存
        Runtime r = Runtime.getRuntime();
        return r.totalMemory() * 1.0 / 1024 / 1024 + "M";
    }

    public static String getJVMFreeMemory() {
        //JVM可以使用的剩余内存
        Runtime r = Runtime.getRuntime();
        return r.freeMemory() * 1.0 / 1024 / 1024 + "M";
    }

    public static long getJVMAvailableCpu() {
        //JVM可以使用的处理器个数
        Runtime r = Runtime.getRuntime();
        return r.availableProcessors();
    }

    public static String getJvmRuleVersion() {
        //Java虚拟机规范版本号
        return System.getProperty("java.vm.specification.version");
    }

    public static String getJvmRuleVendor() {
        //Java虚拟机规范提供商
        return System.getProperty("java.vm.specification.vendor");
    }

    public static String getJvmRuleName() {
        //Java虚拟机规范名称
        return System.getProperty("java.vm.specification.name");
    }

    public static String getJvmVersion() {
        //Java虚拟机版本号
        return System.getProperty("java.vm.version");
    }

    public static String getJvmVendor() {
        //Java虚拟机提供商
        return System.getProperty("java.vm.vendor");
    }

    public static String getJvmName() {
        //Java虚拟机名称
        return System.getProperty("java.vm.name");
    }

    public static String getJavaRuleVersion() {
        //Java规范版本号
        return System.getProperty("java.specification.version");
    }

    public static String getJavaRuleVendor() {
        //Java规范提供商
        return System.getProperty("java.specification.vendor");
    }

    public static String getJavaRuleName() {
        //Java规范名称
        return System.getProperty("java.specification.name");
    }

    public static String getJavaClassVersion() {
        //Java类版本号
        return System.getProperty("java.class.version");
    }

    public static String getJavaClassPath() {
        //Java类路径
        return System.getProperty("java.class.path");
    }

    public static String getJavaLibPath() {
        //Java lib路径
        return System.getProperty("java.library.path");
    }

    public static String getJavaTmpIOPath() {
        //Java输入输出临时路径
        return System.getProperty("java.io.tmpdir");
    }

    public static String getJavaCompile() {
        //Java编译器
        return System.getProperty("java.compiler");
    }

    public static String getJavaExcutePath() {
        //Java执行路径
        return System.getProperty("java.ext.dirs");
    }
}
