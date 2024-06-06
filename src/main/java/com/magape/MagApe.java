package com.magape;

public class MagApe {

    /**
     * Is the usage environment live
     */
    public static volatile boolean live;

    /**
     * publicKey Apply from the MagApe platform
     */
    public static volatile String publicKey;

    /**
     * privateKey Apply from the MagApe platform
     */
    public static volatile String privateKey;

    /**
     * mainNet URL
     */
    public static final String LIVE_API_BASE = "https://api.magape.io/api";

    /**
     * testnet URL
     */
    public static final String TESTNET_API_BASE = "https://testnet-api.magape.io/api";

    /**
     * default connect timeout 30s
     */
    public static final int DEFAULT_CONNECT_TIMEOUT = 30 * 1000;
    /**
     * default read timeout 80s
     */
    public static final int DEFAULT_READ_TIMEOUT = 80 * 1000;
    /**
     * default write timeout 10s
     */
    public static final int DEFAULT_WRITE_TIMEOUT = 10 * 1000;

    /**
     * connect timeout -1 mean never timeout
     */
    private static volatile int connectTimeout = -1;
    /**
     * read timeout -1 mean never timeout
     */
    private static volatile int readTimeout = -1;
    /**
     * write timeout -1 mean never timeout
     */
    private static volatile int writeTimeout = -1;

    public static String getApiBase() {
        return live ? LIVE_API_BASE : TESTNET_API_BASE;
    }

    public static int getConnectTimeout() {
        if (connectTimeout == -1) {
            return DEFAULT_CONNECT_TIMEOUT;
        }
        return connectTimeout;
    }


    public static int getReadTimeout() {
        if (readTimeout == -1) {
            return DEFAULT_READ_TIMEOUT;
        }
        return readTimeout;
    }

    public static int getWriteTimeout() {
        if (writeTimeout == -1) {
            return DEFAULT_WRITE_TIMEOUT;
        }
        return writeTimeout;
    }


}
