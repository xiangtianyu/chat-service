package com.chat.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "localconfig")
public class Constrain {
    public static final String TimeFormat       = "yyyy-MM-dd HH:mm:ss";

    public static final String DateFormat       = "yyyy-MM-dd";

    public static final String Salt             = "xiangtianyu";

    public static final String HEX_NUMS_STR     = "0123456789ABCDEF";

    public static final int SaltSize            = 40;
}
