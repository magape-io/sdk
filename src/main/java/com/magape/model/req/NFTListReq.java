package com.magape.model.req;


import java.util.List;
import java.util.Map;

/**
 * Prop List Request Parameters
 */
public class NFTListReq {

    /**
     * The queried user address
     */
    String address;

    /**
     * networkId
     */
    private Integer networkId;

    /**
     * nft name
     */
    String name;

    /**
     * nft level
     */
    private List<String> level;

    /**
     * nft category
     *
     */
    private List<String> category;

    /**
     * nft attrs
     */
    private Map<String,Object> attributes;

    /**
     * Is tokenId in forward or reverse order? If you don't want to sort using this field, you don't need to pass it
     */
    private Boolean tokenIdAsc;

    /**
     * Level positive or reverse order. If you don't want to sort using this field, you don't need to pass it
     */
    private Boolean levelAsc;

    /**
     * Is category in forward or reverse order? If you don't want to sort using this field, you don't need to pass it
     */
    private Boolean categoryAsc;

    public static NFTListReq builder() {
        return new NFTListReq();
    }

    public NFTListReq address(String address) {
        this.address = address;
        return this;
    }

    public NFTListReq networkId(Integer networkId) {
        this.networkId = networkId;
        return this;
    }

    public NFTListReq name(String name) {
        this.name = name;
        return this;
    }


    public NFTListReq level(List<String> level) {
        this.level = level;
        return this;
    }

    public NFTListReq category(List<String> category) {
        this.category = category;
        return this;
    }
    public NFTListReq attributes(Map<String,Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public NFTListReq tokenIdAsc(Boolean tokenIdAsc) {
        this.tokenIdAsc = tokenIdAsc;
        return this;
    }

    public NFTListReq levelAsc(Boolean levelAsc) {
        this.levelAsc = levelAsc;
        return this;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLevel() {
        return level;
    }

    public void setLevel(List<String> level) {
        this.level = level;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Boolean getTokenIdAsc() {
        return tokenIdAsc;
    }

    public void setTokenIdAsc(Boolean tokenIdAsc) {
        this.tokenIdAsc = tokenIdAsc;
    }

    public Boolean getLevelAsc() {
        return levelAsc;
    }

    public void setLevelAsc(Boolean levelAsc) {
        this.levelAsc = levelAsc;
    }

    public Boolean getCategoryAsc() {
        return categoryAsc;
    }

    public void setCategoryAsc(Boolean categoryAsc) {
        this.categoryAsc = categoryAsc;
    }
}
