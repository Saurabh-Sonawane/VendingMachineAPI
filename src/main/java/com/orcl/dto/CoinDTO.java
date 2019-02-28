package com.orcl.dto;


import com.orcl.model.CoinName;

public class CoinDTO {

    private CoinName coinName;
    private Long initialCount;

    public CoinName getCoinName() {
        return coinName;
    }

    public void setCoinName(CoinName coinName) {
        this.coinName = coinName;
    }

    public Long getInitialCount() {
        return initialCount;
    }

    public void setInitialCount(Long initialCount) {
        this.initialCount = initialCount;
    }
}
