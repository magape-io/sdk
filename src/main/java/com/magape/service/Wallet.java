package com.magape.service;

import com.magape.exception.MapApeException;
import com.magape.model.Response;
import com.magape.model.domain.NFT;
import com.magape.model.domain.NFTOverallPerformance;
import com.magape.model.req.NFTOverallPerformanceReq;
import com.magape.model.req.NFTListReq;
import com.magape.net.ApiRequest;
import com.magape.net.ContentType;
import com.magape.net.RequestMethod;
import com.magape.util.JSONUtil;
import java.util.List;

public class Wallet extends BaseApi {

    /**
     * obtain all nft of this wallet account
     * @param requestId unique request id for trace
     * @param req request param
     * @return nft list
     * @throws Exception exception
     */
    public static List<NFT> nft(String requestId, NFTListReq req) throws Exception {
        String path = "/v1/nft/nftList";
        Response execute = execute(new ApiRequest(RequestMethod.POST, path, ContentType.APPLICATION_JSON,requestId, req), Response.class);
        if (execute.getCode() == 200) {
            return JSONUtil.fromJSONArray(JSONUtil.toJSON(execute.getData()),NFT.class);
        }
        throw new MapApeException(execute.getMessage());
    }

    /**
     * obtain the current user's NFT has an impact on the corresponding game
     * @param requestId unique request id for trace
     * @param req request param
     * @return obtain the current user's NFT has an impact on the corresponding game
     * @throws Exception exception
     */
    public static NFTOverallPerformance performance(String requestId,NFTOverallPerformanceReq req) throws Exception {
        String path = "/v1/nft/overallPerformance";
        Response execute = execute(new ApiRequest(RequestMethod.POST, path, ContentType.APPLICATION_JSON,requestId, req), Response.class);
        if (execute.getCode() == 200) {
            return JSONUtil.fromJSON(JSONUtil.toJSON(execute.getData()),NFTOverallPerformance.class);
        }
        throw new MapApeException(execute.getMessage());

    }

}
