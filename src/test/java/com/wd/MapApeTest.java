package com.wd;

import com.magape.MagApe;
import com.magape.model.domain.GameProp;
import com.magape.model.domain.NFT;
import com.magape.model.domain.NFTOverallPerformance;
import com.magape.model.req.GameExitReq;
import com.magape.model.req.NFTListReq;
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
        MagApe.accessKey = "MCowBQYDK2VwAyEAWZufMRg+VMScRDqexgAHPorNmz3GR4oTYIpgbxQW6cg=";
        MagApe.secretKey = "MC4CAQAwBQYDK2VwBCIEIE0ZTrWnmSiiflzUM9oz8ottY4UhMNruc8lo1Gf+mUH2";

//        List<NFT> nft = Wallet.nft(String.valueOf(System.currentTimeMillis()), NFTListReq.builder());
//        System.out.println(nft);

        String exit = Game.exit(String.valueOf(System.currentTimeMillis()), GameExitReq.builder().token("123"));
        System.out.println(exit);

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
