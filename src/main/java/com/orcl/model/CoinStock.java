package com.orcl.model;


public class CoinStock {

    private CoinName coin;
    private Long stock;

    public CoinName getCoin() {
        return coin;
    }

    public void setCoin(CoinName coin) {
        this.coin = coin;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoinStock coinStock = (CoinStock) o;

        if (coin != coinStock.coin) return false;
        return stock != null ? stock.equals(coinStock.stock) : coinStock.stock == null;
    }

    @Override
    public int hashCode() {
        int result = coin != null ? coin.hashCode() : 0;
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CoinStock{" +
                "coin=" + coin +
                ", stock=" + stock +
                '}';
    }
}
