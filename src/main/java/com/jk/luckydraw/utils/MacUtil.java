package com.jk.luckydraw.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class MacUtil {

    public static String getMac(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String macAdd = "";
        // String arpCMD = "arp -a ";//window环境
        String arpCMD = "arp -n "; //linux环境
        try {
            //Runtime.getRuntime().exec(pingCMD + ip);
            String string;
            Process process = Runtime.getRuntime().exec(arpCMD + ip);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
            LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
            for (int i = 0; i < 100; i++) {
                string = lineNumberReader.readLine();
                //System.out.println(string);
                if (string != null) {
                    if (string.indexOf(ip) > 1) {
                        macAdd = string.substring(string.indexOf("at") + 3, string.indexOf("at") + 20);
                        return macAdd;
                    }
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
