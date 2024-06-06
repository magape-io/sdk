package com.magape.model.domain;

import java.util.List;

public class NFTOverallPerformance {

    /**
     * enhanced attrs
     */
    List<NFTAttr> enhancedAttrs;

    public List<NFTAttr> getEnhancedAttrs() {
        return enhancedAttrs;
    }

    public void setEnhancedAttrs(List<NFTAttr> enhancedAttrs) {
        this.enhancedAttrs = enhancedAttrs;
    }

    static class NFTAttr {

        /**
         * attribute id
         */
        Integer id;

        /**
         * attribute name
         */
        String name;

        /**
         * enhanced percentage
         */
        String enhancedPercentage;

        /**
         * enhanced val
         */
        Double enhancedVal;

        /**
         * nft name
         */
        String nfName;

        /**
         * nft level
         */
        String level;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEnhancedPercentage() {
            return enhancedPercentage;
        }

        public void setEnhancedPercentage(String enhancedPercentage) {
            this.enhancedPercentage = enhancedPercentage;
        }

        public Double getEnhancedVal() {
            return enhancedVal;
        }

        public void setEnhancedVal(Double enhancedVal) {
            this.enhancedVal = enhancedVal;
        }

        public String getNfName() {
            return nfName;
        }

        public void setNfName(String nfName) {
            this.nfName = nfName;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }
}