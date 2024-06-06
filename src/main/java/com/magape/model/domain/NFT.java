package com.magape.model.domain;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class NFT {

    private Long id;

    /**
     * jungle、city、ocean、sky
     */
    private String category;

    /**
     * Common、Uncommon、Rare、Epic、Legendary
     */
    private String level;

    private String toAddress;

    private String fromAddress;

    /**
     * 图片cid
     */
    private String imgCid;

    /**
     * 图片cid
     */
    private String imageUrl;

    /**
     * cid
     */
    private String nftCid;

    /**
     * cid对应在ipfs上的url
     */
    private String nftCidUrl;

    /**
     * 交易hash
     */
    private String transactionHash;

    /**
     * token id
     */
    private Integer tokenId;

    /**
     * 接入方法
     */
    private String method;

    /**
     * 所属道具链地址
     */
    private String erc721ContractAddress;

    /**
     * 所属市场地址
     */
    private String marketContractAddress;

    /**
     * 所属网络id
     */
    private Integer networkId;

    /**
     * 属性
     */
    private String attrs;

    /**
     * 属性列表
     */
    private List<Attributes> attrList;

    /**
     * 道具名称
     */
    private String name;

    /**
     * 道具市场价格
     */
    private BigDecimal price;

    /**
     * 金币名称
     */
    private String coinName;

    /**
     * 金币所属链地址
     */
    private String tokenContractAddress;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getImgCid() {
        return imgCid;
    }

    public void setImgCid(String imgCid) {
        this.imgCid = imgCid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNftCid() {
        return nftCid;
    }

    public void setNftCid(String nftCid) {
        this.nftCid = nftCid;
    }

    public String getNftCidUrl() {
        return nftCidUrl;
    }

    public void setNftCidUrl(String nftCidUrl) {
        this.nftCidUrl = nftCidUrl;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getErc721ContractAddress() {
        return erc721ContractAddress;
    }

    public void setErc721ContractAddress(String erc721ContractAddress) {
        this.erc721ContractAddress = erc721ContractAddress;
    }

    public String getMarketContractAddress() {
        return marketContractAddress;
    }

    public void setMarketContractAddress(String marketContractAddress) {
        this.marketContractAddress = marketContractAddress;
    }

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public String getAttrs() {
        return attrs;
    }

    public void setAttrs(String attrs) {
        this.attrs = attrs;
    }

    public List<Attributes> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<Attributes> attrList) {
        this.attrList = attrList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getTokenContractAddress() {
        return tokenContractAddress;
    }

    public void setTokenContractAddress(String tokenContractAddress) {
        this.tokenContractAddress = tokenContractAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
