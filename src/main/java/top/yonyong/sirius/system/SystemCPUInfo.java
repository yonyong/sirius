package top.yonyong.sirius.system;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * @author yonyong
 **/
public class SystemCPUInfo {

    public static void main(String[] args) {
        System.out.println(SystemCPUInfo.getWinCPUsage());
    }

    public static String  getWinCPUsage() {
        try {
            String procCmd = System.getenv("windir")
                    + "\\system32\\wbem\\wmic.exe process get Caption,"
                    + "KernelModeTime,UserModeTime,WriteOperationCount";
            // 取进程信息
            long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
            Thread.sleep(10);
            long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
            if (c0 != null && c1 != null) {
                long idletime = c1[0] - c0[0];
                long busytime = c1[1] - c0[1];
                return (double) (100 * (busytime) / (busytime + idletime)) + "%";
            } else {
                return 0.0 + "%";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0.0+ "%";
        }
    }

    private static long[] readCpu(final Process proc) {
        long[] retn = new long[2];
        try {
            proc.getOutputStream().close();
            InputStreamReader ir = new InputStreamReader(proc.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line = input.readLine();
            if (line == null || line.length() < 10) {
                return null;
            }
            //以下为执行命令行后，各列出现的第一个字母的横坐标。具体样式可以在cmd 执行该指令
            int captionX= line.indexOf("Caption");
            int kernelModeTimeX = line.indexOf("KernelModeTime");
            int userModeTimeX = line.indexOf("UserModeTime");
            //标记最后一列
            int lastMark = line.indexOf("WriteOperationCount");
            long idletime = 0;
            long kneltime = 0;
            long usertime = 0;
            while ((line = input.readLine()) != null) {
                if (line.length() < lastMark) {
                    continue;
                }
                String caption = substring(line, captionX, kernelModeTimeX - 1)
                        .trim();

                if ("System Idle Process".equals(caption) || "System".equals(caption)) {
                    idletime += Long.valueOf(substring(line, kernelModeTimeX, userModeTimeX - 1).trim());
                    idletime += Long.valueOf(substring(line, kernelModeTimeX, userModeTimeX - 1).trim());
                    continue;
                }
                kneltime += Long.valueOf(substring(line, kernelModeTimeX, userModeTimeX - 1).trim());
                usertime += Long.valueOf(substring(line, userModeTimeX, lastMark - 1).trim());
            }
            retn[0] = idletime;
            retn[1] = kneltime + usertime;
            return retn;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                proc.getInputStream().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String substring(String src, int startIdx, int endIdx){
        byte[] b = src.getBytes();
        StringBuilder tgt = new StringBuilder();
        for(int i=startIdx; i<=endIdx; i++){
            tgt.append((char) b[i]);
        }
        return tgt.toString();
    }


}
