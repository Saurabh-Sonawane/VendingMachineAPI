package com.orcl.model;

public enum CoinName {

	ONE_PENNY(1),
	TWO_PENCE(2),
	FIVE_PENCE(5),
	TEN_PENCE(10),
	TWENTY_PENCE(20),
	FIFTY_PENCE(50),
	ONE_POUND(100),
	TWO_POUND(200);

	private Integer value;

	CoinName(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}