package com.magape.model.domain;

/**
 * Verify whether the token is valid
 */

public class VerifyTokenResp {

    /**
     * Is it valid to return token?
     */
    boolean valid;

    /**
     * The wallet address corresponding to token
     */
    String address;


    public boolean isValid() {
        return valid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
