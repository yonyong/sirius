package top.yonyong.sirius.system;

import java.applet.Applet;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * @author yonyong
 **/
public class DefaultSiriusSystemHardwareInfo extends Applet {

    public static void main(String[] args) throws Exception {
        //CPU
        System.out.println(getCPUSerial());
        //主板
        System.out.println(getMotherboardSN());
        //c盘
        System.out.println(getHardDiskSN("c"));
        //MAC
        System.out.println(getMac());
        String msg = getCPUSerial() + getMotherboardSN().replace(".", "") + getHardDiskSN("c") + getMac().replace("-", "");
        System.out.println("原始数据:" + msg);
    }
    @Override
    public void paint(Graphics paint) {
        super.paint(paint);
        paint.drawString("获取硬件信息", 10, 10);
        paint.drawString("CPU  SN:" + DefaultSiriusSystemHardwareInfo.getCPUSerial(), 10, 30);
        paint.drawString("主板  SN:" + DefaultSiriusSystemHardwareInfo.getMotherboardSN(), 10, 50);
        paint.drawString("C盘   SN:" + DefaultSiriusSystemHardwareInfo.getHardDiskSN("c"), 10, 70);
        paint.drawString("MAC  SN:" + DefaultSiriusSystemHardwareInfo.getMac(), 10, 90);
    }

    public static String getMac() {
        //获取MAC地址
        try {
            byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                String s = Integer.toHexString(mac[i] & 0xFF);
                sb.append(s.length() == 1 ? 0 + s : s);
            }
            return sb.toString().toUpperCase();
        } catch (Exception e) {
            return "";
        }
    }

    public static String getCPUSerial() {
        //获取CPU序列号
        StringBuilder result = new StringBuilder();
        try {
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_Processor\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.ProcessorId \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p
                    .getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result.append(line);
            }
            input.close();
            file.delete();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        if (result.toString().trim().length() < 1) {
            result = new StringBuilder("无CPU_ID被读取");
        }
        return result.toString().trim();
    }

    public static String getHardDiskSN(String drive) {
        //获取硬盘序列号
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    + "Set colDrives = objFSO.Drives\n"
                    + "Set objDrive = colDrives.item(\""
                    + drive
                    + "\")\n"
                    + "Wscript.Echo objDrive.SerialNumber"; // see note
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p
                    .getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }

    public static String getMotherboardSN() {
        //获取主板序列号
        StringBuilder result = new StringBuilder();
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_BaseBoard\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.SerialNumber \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";

            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result.append(line);
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString().trim();
    }
}
