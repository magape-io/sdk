package com.wd;

import com.magape.MagApe;
import com.magape.model.domain.NFTOverallPerformance;
import com.magape.model.req.NFTOverallPerformanceReq;
import com.magape.service.Wallet;
import org.junit.Test;


public class MapApeTest {

    @Test
    public void test1() throws Exception {
        MagApe.live = false;
        MagApe.accessKey = "MCowBQYDK2VwAyEAjtZFUHxOcY7+qDoJDWXMZpI+jOi2uv+26k8I6wgnXh0=";
        MagApe.secretKey = "MC4CAQAwBQYDK2VwBCIEII+CN0TFDnzGAJiqex5WIL9bb23EPsjMp082Q/6b4A/+";


        NFTOverallPerformance performance = Wallet.performance(String.valueOf(System.currentTimeMillis()), NFTOverallPerformanceReq.build().address("0x4D11dF920E0E48c7E132e5a9754C7e754Cd6EBFB").networkId(97));
        System.out.println(performance);
    }

}
