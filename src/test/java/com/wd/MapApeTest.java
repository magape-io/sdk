package com.wd;

import com.magape.model.domain.NFTOverallPerformance;
import com.magape.model.req.NFTOverallPerformanceReq;
import com.magape.service.Wallet;
import org.junit.Test;


public class MapApeTest {

    @Test
    public void test1() throws Exception {
//        MagApe.live = false;
//        MagApe.privateKey = "MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQg/m3+g0mAfHM8I5M7Ne3R5IlBHYTzMXq4AqcPOXblybSgCgYIKoZIzj0DAQehRANCAAQeBFUgSHgvvuGWg3xmlRtm6M8RPt4EU/9cUP4H2ISOzzF2GElLv0WuhFfI5YhwkPB7l9IKMlc6qKdsbB9J83hc";
//        MagApe.publicKey = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEHgRVIEh4L77hloN8ZpUbZujPET7eBFP/XFD+B9iEjs8xdhhJS79FroRXyOWIcJDwe5fSCjJXOqinbGwfSfN4XA==";

//        PropPageReq pageReq = PropPageReq.builder().address("0x4D11dF920E0E48c7E132e5a9754C7e754Cd6EBFB").networkId(97);
//        List<NFT> nft = Wallet.nft(String.valueOf(System.currentTimeMillis()),pageReq);
//        System.out.println(nft);

//        GameProp gameProp = GameProp.builder().id("123").name("test").image("https://media.istockphoto.com/id/182462356/photo/speedometer-and-tachometer.jpg?s=1024x1024&w=is&k=20&c=Kb9uDKKfcP1Wklnx08G_TIp5xum0rcPDK7GlnqBRdD0=").cost(10d).maxBuy(10).maxSell(10).type(GameProp.BLOCK_CHAIN);
//
//        boolean b = Game.uploadOrUpdateProp(String.valueOf(System.currentTimeMillis()), List.of(gameProp));
//        System.out.println(b);

        NFTOverallPerformanceReq req = NFTOverallPerformanceReq.build().address("0x4D11dF920E0E48c7E132e5a9754C7e754Cd6EBFB").networkId(141319);

        NFTOverallPerformance performance = null;
        try {
            performance = Wallet.performance(String.valueOf(System.currentTimeMillis()), req);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        System.out.println(performance);
    }

}
