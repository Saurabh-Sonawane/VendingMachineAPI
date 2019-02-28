package com.orcl.repository;

import com.orcl.model.CoinDeposit;
import com.orcl.model.CoinInventory;
import com.orcl.model.CoinStock;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VendingRepository {

    private CoinInventory coinInventory = new CoinInventory();
    private List<CoinDeposit> coinDeposits = new ArrayList<>();

    public void saveInvetory(List<CoinStock> coinStocks) {
        coinInventory.setCoinInventory(coinStocks);
    }

    public void depositCoin(CoinDeposit coinDeposit) {
        coinDeposits.add(coinDeposit);
    }

    public CoinInventory getCoinInventory() {
        return coinInventory;
    }

    public List<CoinDeposit> getCoinDeposits() {
        return coinDeposits;
    }
}
