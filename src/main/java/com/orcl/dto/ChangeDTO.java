package com.orcl.dto;

public class ChangeDTO {
    private String coinName;
    private Long noOfCoins;

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public Long getNoOfCoins() {
        return noOfCoins;
    }

    public void setNoOfCoins(Long noOfCoins) {
        this.noOfCoins = noOfCoins;
    }
}
