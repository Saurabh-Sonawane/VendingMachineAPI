package com.orcl.repository;

import com.orcl.model.CoinDeposit;
import com.orcl.model.CoinInventory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class VendingRepositoryTest {
    @InjectMocks
    private VendingRepository vendingRepository;

    @Test
    public void verifySaveInvetory() throws Exception {
        //given
        CoinInventory coinInventory = populateInventory();
        //when
        vendingRepository.saveInvetory(coinInventory.getCoinInventory());

        //then
        Assert.assertEquals(coinInventory.getCoinInventory(), vendingRepository.getCoinInventory().getCoinInventory());
    }

    @Test
    public void verifyDepositCoin() throws Exception {
        //given
        CoinDeposit coinDeposit = populateCoinDeposit();

        //when
        vendingRepository.depositCoin(coinDeposit);

        //then
        List<CoinDeposit> coinDeposit1 = vendingRepository.getCoinDeposits();
        Assert.assertEquals(1, coinDeposit1.size());
    }

    @Test
    public void verifyGetCoinInventory() throws Exception {
        //given
        CoinInventory coinInventory = populateInventory();
        vendingRepository.saveInvetory(coinInventory.getCoinInventory());

        //when
        CoinInventory coinInventory1 = vendingRepository.getCoinInventory();

        //then
        Assert.assertEquals(coinInventory.getCoinInventory(), coinInventory1.getCoinInventory());
    }

    @Test
    public void verifyGetCoinDeposits() throws Exception {
        //given
        CoinDeposit coinDeposit = populateCoinDeposit();
        vendingRepository.depositCoin(coinDeposit);
        //when
        List<CoinDeposit> coinDeposit1 = vendingRepository.getCoinDeposits();

        //then

        Assert.assertEquals(1, coinDeposit1.size());
    }

    private CoinInventory populateInventory() {
        PodamFactory factory = new PodamFactoryImpl();
        CoinInventory coinInventory = factory.manufacturePojo(CoinInventory.class);
        return coinInventory;
    }


    private CoinDeposit populateCoinDeposit() {
        PodamFactory factory = new PodamFactoryImpl();
        CoinDeposit coinDeposit = factory.manufacturePojo(CoinDeposit.class);
        return coinDeposit;
    }
}