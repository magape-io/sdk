package com.magape.model.domain;

/**
 * 游戏道具属性
 */

public class GameProp {


    public static final Integer BLOCK_CHAIN = 1;
    public static final Integer FIGHTING_ARENA = 2;

    // 道具在游戏中的id
    private String id;

    // 最多可以换多少个道具变成mac。
    private Integer maxSell;

    // 最多用mac购买多少个道具。
    private Integer maxBuy;

    // 道具可以换多少个mac: 0.0001 - 1000
    private Double cost;

    // 类型
    private Integer type;

    // 图片url
    private String image;

    // 道具名称
    private String name;

    // 描述
    private String description;


    public GameProp id(String id) {
        this.id = id;
        return this;
    }

    public GameProp maxSell(Integer maxSell) {
        this.maxSell = maxSell;
        return this;
    }

    public GameProp maxBuy(Integer maxBuy) {
        this.maxBuy = maxBuy;
        return this;
    }

    public GameProp cost(Double cost) {
        this.cost = cost;
        return this;
    }

    public GameProp type(Integer type) {
        this.type = type;
        return this;
    }

    public GameProp image(String image) {
        this.image = image;
        return this;
    }

    public GameProp name(String name) {
        this.name = name;
        return this;
    }

    public GameProp description(String description) {
        this.description = description;
        return this;
    }

    public static GameProp builder() {
        return new GameProp();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxSell() {
        return maxSell;
    }

    public void setMaxSell(Integer maxSell) {
        this.maxSell = maxSell;
    }

    public Integer getMaxBuy() {
        return maxBuy;
    }

    public void setMaxBuy(Integer maxBuy) {
        this.maxBuy = maxBuy;
    }

    public static class Attr {
        // 属性名
        String traitType;

        // 属性值
        Object value;

        // 属性描述
        String description;

        public Attr traitType(String traitType) {
            this.traitType = traitType;
            return this;
        }

        public Attr traitType(Object value) {
            this.value = value;
            return this;
        }

        public Attr description(String description) {
            this.description = description;
            return this;
        }

        public Attr builder() {
            return new Attr();
        }

        public String getTraitType() {
            return traitType;
        }

        public void setTraitType(String traitType) {
            this.traitType = traitType;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
