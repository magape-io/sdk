package com.magape.model.req;

public class NFTOverallPerformanceReq {

    Integer networkId;

    String address;

    public static NFTOverallPerformanceReq build() {
        return new NFTOverallPerformanceReq();
    }

    public NFTOverallPerformanceReq networkId(Integer networkId) {
        this.networkId = networkId;
        return this;
    }

    public NFTOverallPerformanceReq address(String address) {
        this.address = address;
        return this;
    }


    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
