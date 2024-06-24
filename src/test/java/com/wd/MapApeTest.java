package com.wd;

import com.magape.MagApe;
import com.magape.model.domain.GameProp;
import com.magape.model.domain.NFTOverallPerformance;
import com.magape.model.req.NFTOverallPerformanceReq;
import com.magape.service.Game;
import com.magape.service.Wallet;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static com.google.gson.internal.bind.TypeAdapters.UUID;
import static com.google.gson.internal.bind.TypeAdapters.UUID_FACTORY;


public class MapApeTest {

    @Test
    public void test1() throws Exception {
        MagApe.live = false;
        MagApe.accessKey = "MCowBQYDK2VwAyEAjtZFUHxOcY7+qDoJDWXMZpI+jOi2uv+26k8I6wgnXh0=";
        MagApe.secretKey = "MC4CAQAwBQYDK2VwBCIEII+CN0TFDnzGAJiqex5WIL9bb23EPsjMp082Q/6b4A/+";

//
//        NFTOverallPerformance performance = Wallet.performance(String.valueOf(System.currentTimeMillis()), NFTOverallPerformanceReq.build().address("0x4D11dF920E0E48c7E132e5a9754C7e754Cd6EBFB").networkId(97));
//        System.out.println(performance);
//
//
//
////        NFTOverallPerformance performance = Wallet.performance(String.valueOf(System.currentTimeMillis()), NFTOverallPerformanceReq.build().address("0x4D11dF920E0E48c7E132e5a9754C7e754Cd6EBFB").networkId(97));
////        System.out.println(performance);

        boolean b = Game.uploadOrUpdateProp("31312314", List.of(GameProp.builder().id("34234232452").cost(100d).name("test").type(GameProp.ARENA).description("test").image("test.png")));
        System.out.println(b);
    }

}
