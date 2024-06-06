package com.magape.service;

import com.magape.exception.MapApeException;
import com.magape.model.Response;
import com.magape.model.domain.GameProp;
import com.magape.net.ApiRequest;
import com.magape.net.ContentType;
import com.magape.net.RequestMethod;
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

}
