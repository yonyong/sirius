package top.yonyong.sirius.system;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

/**
 * @author yonyong
 **/
public class SystemMemoryInfo {

    public static String getTotalMemory() {
        //获取总的物理内存
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long totalVirtualMemory = osmxb.getTotalSwapSpaceSize();
        return totalVirtualMemory* 1.0 / 1024 / 1024 + "M";
    }

    public static String getFreeMemory() {
        //获取可用物理内存
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
        return freePhysicalMemorySize* 1.0 / 1024 / 1024 + "M";
    }

    public static String getMemeoyUsage(){
        //获取内存使用率
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long totalvirtualMemory = osmxb.getTotalSwapSpaceSize();
        System.out.println(totalvirtualMemory* 1.0 / 1024 / 1024 + "M");

        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
        System.out.println(freePhysicalMemorySize* 1.0 / 1024 / 1024 + "M");

        Double compare=(Double)(1-freePhysicalMemorySize*1.0/totalvirtualMemory)*100;
        return compare.intValue()+"%";
    }

    public static void main(String[] args) {
        System.out.println(SystemMemoryInfo.getTotalMemory());
    }
}
