package com.chat.util;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "localconfig")
public class Constrain {
    public static final String TimeFormat       = "yyyy-MM-dd HH:mm:ss";

    public static final String DateFormat       = "yyyy-MM-dd";
}
