package com.orcl.model;

import java.util.List;

public class CoinInventory {
    private List<CoinStock> coinInventory;

    public List<CoinStock> getCoinInventory() {
        return coinInventory;
    }

    public void setCoinInventory(List<CoinStock> coinInventory) {
        this.coinInventory = coinInventory;
    }
}
