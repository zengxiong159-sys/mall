package com.qdbank.mall.util;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class IpUtils {

  public static String getLocalHostAddress() {
    List<String> ipList = new ArrayList<>();
    String serverIp = "";

    try {
      InetAddress ia = null;
      Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
      out:
      while (true) {
        NetworkInterface current = null;
        do {
          do {
            do {
              if (!allNetInterfaces.hasMoreElements()) {
                break out;
              }
              current = allNetInterfaces.nextElement();
            } while (!current.isUp());
          } while (current.isLoopback());
        } while (current.isVirtual());

        Enumeration<InetAddress> addresses = current.getInetAddresses();

        while (addresses.hasMoreElements()) {
          ia = addresses.nextElement();
          if (!ia.isLoopbackAddress() && ia instanceof Inet4Address) {
            serverIp = ia.getHostAddress();
            ipList.add(serverIp);
          }
        }
      }
    } catch (Exception ignored) {
    }
    if (!ipList.isEmpty()) {
      ipList.sort(String::compareTo);
    }

    for (int i = 0; i < ipList.size(); ++i) {
      serverIp = ipList.get(i);
      if (!serverIp.equalsIgnoreCase("127.0.0.1")) {
        return serverIp;
      }
    }
    return serverIp;
  }


  /**
   * 获取IP地址
   *
   * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
   * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
   */
  public static String getIpAddr(HttpServletRequest request) {
    String ipAddress = null;
    try {
      ipAddress = request.getHeader("x-forwarded-for");
      if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getHeader("Proxy-Client-IP");
      }
      if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getHeader("WL-Proxy-Client-IP");
      }
      if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getRemoteAddr();
        if (ipAddress.equals("127.0.0.1")) {
          ipAddress = getHostAddress();
        }
      }
      // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
      if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length() == 15
        if (ipAddress.indexOf(",") > 0) {
          ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }
      }

      // 解决请求和响应的IP一致且通过浏览器请求时，request.getRemoteAddr()为"0:0:0:0:0:0:0:1"
      if("0:0:0:0:0:0:0:1".equals(ipAddress)){
        ipAddress = getHostAddress();
      }
    } catch (Exception e) {
      ipAddress = "";
    }

    return ipAddress;
  }

  private static String getHostAddress(){
    // 根据网卡取本机配置的IP
    InetAddress inet = null;
    try {
      inet = InetAddress.getLocalHost();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return inet.getHostAddress();
  }
}
