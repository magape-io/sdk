package com.magape.model.req;

public class GameExitReq {

    /**
     * need exit's user token,not null
     */
    private String token;

    public GameExitReq token(String token) {
        this.token = token;
        return this;
    }


    public static GameExitReq builder() {
        return new GameExitReq();
    }
}
