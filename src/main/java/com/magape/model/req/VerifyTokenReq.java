package com.magape.model.req;

/**
 * Verify whether the token is valid
 */
public class VerifyTokenReq {

    /**
     * login from magape
     */
    String token;

    public static VerifyTokenReq build() {
        return new VerifyTokenReq();
    }

    public VerifyTokenReq token(String token) {
        this.token = token;
        return this;
    }
}
