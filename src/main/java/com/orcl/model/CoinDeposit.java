package com.orcl.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CoinDeposit {

    private CoinName coinName;

    @JsonFormat(pattern= "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date depositedDateTime;

    public CoinName getCoinName() {
        return coinName;
    }

    public void setCoinName(CoinName coinName) {
        this.coinName = coinName;
    }

    public Date getDepositedDateTime() {
        return depositedDateTime;
    }

    public void setDepositedDateTime(Date depositedDateTime) {
        this.depositedDateTime = depositedDateTime;
    }
}
