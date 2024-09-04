package com.wd;

import com.magape.MagApe;
import com.magape.model.domain.VerifyTokenResp;
import com.magape.model.req.VerifyTokenReq;
import com.magape.service.Game;
import org.junit.Test;


public class MapApeTest {

    @Test
    public void test1() throws Exception {
        MagApe.live = false;
        MagApe.accessKey = "MCowBQYDK2VwAyEAWZufMRg+VMScRDqexgAHPorNmz3GR4oTYIpgbxQW6cg=1";
        MagApe.secretKey = "MC4CAQAwBQYDK2VwBCIEIE0ZTrWnmSiiflzUM9oz8ottY4UhMNruc8lo1Gf+mUH2";

//        List<NFT> nft = Wallet.nft(String.valueOf(System.currentTimeMillis()), NFTListReq.builder());
//        System.out.println(nft);

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIweEEzNDM1NzQ4NjIyNDE1MWREZkRCMjkxRTEzMTk0OTk1YzIyRGY1MDUiLCJkYXRlIjoxNzI1MjUyODg5Mzk1fQ.Rnax0W-A4s8qsuHh5_LrSTo0IJmQjBWB5v0yQue6lrI";

        VerifyTokenResp verifyTokenResp = Game.verifyToken(String.valueOf(System.currentTimeMillis()), VerifyTokenReq.build());
        System.out.println(verifyTokenResp.isValid());

//
//        NFTOverallPerformance performance = Wallet.performance(String.valueOf(System.currentTimeMillis()), NFTOverallPerformanceReq.build().address("0x4D11dF920E0E48c7E132e5a9754C7e754Cd6EBFB").networkId(97));
//        System.out.println(performance);
//
//
//
////        NFTOverallPerformance performance = Wallet.performance(String.valueOf(System.currentTimeMillis()), NFTOverallPerformanceReq.build().address("0x4D11dF920E0E48c7E132e5a9754C7e754Cd6EBFB").networkId(97));
////        System.out.println(performance);

//        boolean b = Game.uploadOrUpdateProp("31312316", List.of(GameProp.builder().id("34234232452").cost(100d).name("test").type(GameProp.ARENA).supportCurrency(GameProp.CURRENCY_MAGAPE_ONLY).description("test").image("test.png")));
//        System.out.println(b);
    }

}
