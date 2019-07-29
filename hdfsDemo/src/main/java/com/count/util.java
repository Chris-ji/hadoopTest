package com.count;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Chris on 2019/1/5.
 */
public class util {
    public static String getInfo(Object O ,String msg ){
        return  getHostName() + ":" + getPID() + ":" + getTID()+ ":" + getObjInfo(O)+ ":" + msg ;

    }
    /**
     * 得到主机名
     * @return
     */
    public static String getHostName(){
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 得到进程ID
     * @return
     */
    public  static int getPID(){
      String info =  ManagementFactory.getRuntimeMXBean().getName();
       return Integer.parseInt(info.substring(0,info.indexOf("@")));
    }
    /**
     * 得到线程ID
     */
    public static String getTID(){
       return  Thread.currentThread().getName();
    }

    public static  String getObjInfo(Object O){
        String sname = O.getClass().getSimpleName();

        return sname + "@" + O.hashCode();
    }
}
