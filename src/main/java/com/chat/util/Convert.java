package com.chat.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.chat.datamodel.domain.User;
import com.chat.datamodel.domain.WhiteList;
import com.chat.datamodel.dto.UserDTO;
import com.chat.datamodel.dto.WhiteListDTO;

import javax.servlet.http.HttpServletRequest;

public class Convert {
    public static UserDTO convertUserFromDomain (User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUid(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassWord(user.getPassWord());
        userDTO.setCreateTime(user.getCreateTime());
        return userDTO;
    }

    public static WhiteListDTO convertWhiteListFromDomain (WhiteList wl) {
        WhiteListDTO w = new WhiteListDTO();
        w.setId(wl.getId());
        w.setIp(wl.getIp());
        w.setIsAuto(wl.getIsAuto());
        w.setsTime(wl.getsTime());
        w.seteTime(wl.geteTime());
        w.setValid(wl.getValid());
        return w;
    }

    public static boolean checkTimeFormat(String date) {
        if (date.length() != Constrain.TimeFormat.length())
            return false;
        for (int i = 0; i < date.length(); i++) {
            switch (i) {
                case 4:
                case 7:
                    if (date.toCharArray()[i] != '-')
                        return false;
                    break;
                case 10:
                    if (date.toCharArray()[i] != ' ')
                        return false;
                    break;
                case 13:
                case 16:
                    if (date.toCharArray()[i] != ':')
                        return false;
                    break;
                default:
                    if (!Character.isDigit(date.toCharArray()[i]))
                        return false;
                    break;
            }
        }
        return true;
    }

    public static boolean checkDateFormat(String date) {
        if (date.length() != Constrain.DateFormat.length())
            return false;
        for (int i = 0; i < date.length(); i++) {
            switch (i) {
                case 4:
                case 7:
                    if (date.toCharArray()[i] != '-')
                        return false;
                    break;
                default:
                    if (!Character.isDigit(date.toCharArray()[i]))
                        return false;
                    break;
            }
        }
        return true;
    }

    /**
     * 将16进制字符串转换成字节数组
     * @param hex
     * @return
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] hexChars = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (Constrain.HEX_NUMS_STR.indexOf(hexChars[pos]) << 4
                    | Constrain.HEX_NUMS_STR.indexOf(hexChars[pos + 1]));
        }
        return result;
    }

    /**
     * 将指定byte数组转换成16进制字符串
     * @param b
     * @return
     */
    public static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

    public static byte[] createRandomSalt() {
        Random random = new Random();
        byte[] salt = new byte[Constrain.SaltSize];
        random.nextBytes(salt);
        return salt;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}