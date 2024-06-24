package com.magape.model.domain;

/**
 * 游戏道具属性
 */

public class GameProp {

    // 道具类型 可以导出导出型,supportCurrency不传
    public static final Integer APE_LINK = 1;
    // 道具类型 只能用户ARENA格斗场，必须要传supportCurrency
    public static final Integer ARENA = 2;
    // 道具类型 只能用于P2P交易，supportCurrency不传
    public static final Integer P2P = 3;

    // 道具为arena时支持的货币类型，只支持格斗游戏的货币
    public static final Integer CURRENCY_ARENA_ONLY = 1;
    // 道具为arena支持的货币类型，只支持MAGAPE游戏的货币
    public static final Integer CURRENCY_MAGAPE_ONLY = 2;
    // 道具为arena支持的货币类型，支持格斗游戏和MAGAPE游戏的货币
    public static final Integer CURRENCY_BOTH_ARENA_MAGAPE = 3;

    // 道具在游戏中的id
    private String id;

    // 道具可以换多少个mac: 0.0001 - 1000
    private Double cost;

    private Integer supportCurrency;

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

    public GameProp supportCurrency(Integer supportCurrency) {
        this.supportCurrency = supportCurrency;
        return this;
    }

    public GameProp description(String description) {
        this.description = description;
        return this;
    }

    public Integer getSupportCurrency() {
        return supportCurrency;
    }

    public void setSupportCurrency(Integer supportCurrency) {
        this.supportCurrency = supportCurrency;
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
