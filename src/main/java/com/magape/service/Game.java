package com.magape.service;

import com.magape.exception.MapApeException;
import com.magape.model.Response;
import com.magape.model.domain.GameProp;
import com.magape.model.domain.VerifyTokenResp;
import com.magape.model.req.GameExitReq;
import com.magape.model.req.VerifyTokenReq;
import com.magape.net.ApiRequest;
import com.magape.net.ContentType;
import com.magape.net.RequestMethod;
import com.magape.util.JSONUtil;

import java.util.List;

public class Game extends BaseApi {

    /**
     * Upload or game item metadata
     * @return result
     */
    public static boolean uploadOrUpdateProp(String requestId,List<GameProp> gameProps) throws Exception {
        String path = "/v1/game/uploadOrUpdateProp";
        Response execute = execute(new ApiRequest(RequestMethod.POST, path, ContentType.APPLICATION_JSON,requestId, gameProps), Response.class);
        if (execute.getCode() == 200) {
            return true;
        }
        throw new MapApeException(execute.getMessage());
    }

    /**
     * obtain the current user's NFT has an impact on the corresponding game
     * @param requestId unique request id for trace
     * @param req request param
     * @return Verify whether the login token to magape is valid
     * @throws Exception exception
     */
    public static VerifyTokenResp verifyToken(String requestId, VerifyTokenReq req) throws Exception {
        String path = "/v1/game/verifyToken";
        Response execute = execute(new ApiRequest(RequestMethod.POST, path, ContentType.APPLICATION_JSON,requestId, req), Response.class);
        if (execute.getCode() == 200) {
            return JSONUtil.fromJSON(JSONUtil.toJSON(execute.getData()), VerifyTokenResp.class);
        }
        throw new MapApeException(execute.getMessage());
    }

    public static String exit(String requestId, GameExitReq gameExitReq) throws Exception {
        String path = "/v1/game/exit";
        Response execute = execute(new ApiRequest(RequestMethod.POST, path, ContentType.APPLICATION_JSON,requestId,gameExitReq), Response.class);
        if (execute.getCode() == 200) {
            return execute.getMessage();
        }
        throw new MapApeException(execute.getMessage());
    }

}
