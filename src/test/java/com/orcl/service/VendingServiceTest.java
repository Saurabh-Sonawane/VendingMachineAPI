package com.orcl.service;

import com.orcl.dto.ChangeDTO;
import com.orcl.dto.CoinDTO;
import com.orcl.model.CoinDeposit;
import com.orcl.model.CoinInventory;
import com.orcl.model.CoinName;
import com.orcl.model.CoinStock;
import com.orcl.repository.VendingRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class VendingServiceTest {
    @Mock
    private VendingRepository vendingRepository;

    @InjectMocks
    private VendingService vendingService;

    @Test
    public void verifyInitMachine() throws Exception {
        //given
        List<CoinDTO> coinDTOS = new ArrayList<>();
        coinDTOS.add(populateCoinDTO());
        coinDTOS.add(populateCoinDTO());

        //when
        vendingService.initMachine(coinDTOS);
        //then

        verify(vendingRepository, times(1)).saveInvetory(Matchers.anyListOf(CoinStock.class));
    }

    @Test
    public void verifyDepositCoin() throws Exception {
        //given
        CoinInventory coinInventory = populateInventory();
        when(vendingRepository.getCoinInventory()).thenReturn(coinInventory);

        //when
        vendingService.depositCoin(CoinName.FIFTY_PENCE);

        //then
        verify(vendingRepository, times(1)).depositCoin(Matchers.any(CoinDeposit.class));
        verify(vendingRepository, times(1)).getCoinInventory();
    }


    @Test
    public void verifyGetChange() throws Exception {
        //given
        List<CoinStock> coinStocks = new ArrayList<>();
        CoinStock coinStock = new CoinStock();
        coinStock.setCoin(CoinName.ONE_POUND);
        coinStock.setStock(5L);
        coinStocks.add(coinStock);

        coinStock = new CoinStock();
        coinStock.setCoin(CoinName.ONE_PENNY);
        coinStock.setStock(10L);
        coinStocks.add(coinStock);

        coinStock = new CoinStock();
        coinStock.setCoin(CoinName.TWO_PENCE);
        coinStock.setStock(20L);
        coinStocks.add(coinStock);

        CoinInventory coinInventory = new CoinInventory();
        coinInventory.setCoinInventory(coinStocks);
        when(vendingRepository.getCoinInventory()).thenReturn(coinInventory);
        //when
        List<ChangeDTO> changeDTOS = vendingService.getChange(101L);
        //then
        Assert.assertEquals(CoinName.ONE_POUND.name(), changeDTOS.get(0).getCoinName());
        Assert.assertEquals(new Long(1), changeDTOS.get(0).getNoOfCoins());
        Assert.assertEquals(CoinName.ONE_PENNY.name(), changeDTOS.get(1).getCoinName());
        Assert.assertEquals(new Long(1), changeDTOS.get(1).getNoOfCoins());
    }

    private CoinInventory populateInventory() {
        PodamFactory factory = new PodamFactoryImpl();
        CoinInventory coinInventory = factory.manufacturePojo(CoinInventory.class);
        return coinInventory;
    }

    private CoinDTO populateCoinDTO() {
        PodamFactory factory = new PodamFactoryImpl();
        return factory.manufacturePojo(CoinDTO.class);
    }

}