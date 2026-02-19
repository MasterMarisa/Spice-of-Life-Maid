package com.mastermarisa.solmaid.utils;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class Debug {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final String MARK = "[solmaid-DEBUG]";

    public static void Log(String msg) {
        Log(msg, new Object[]{});
    }

    public static void Log(String msg, Object[] args) { LOGGER.debug(MARK + msg, args); }
}
