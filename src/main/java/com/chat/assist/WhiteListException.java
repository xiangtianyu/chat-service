package com.chat.assist;

/**
 * Created by xiangtianyu on 2016/11/21.
 */
public class WhiteListException extends Exception {

    public WhiteListException() {
        super("白名单错误");
    }

    public WhiteListException(String message) {
        super(message);
    }
}
