package com.orcl.service;

import com.orcl.dto.ChangeDTO;
import com.orcl.dto.CoinDTO;
import com.orcl.model.*;
import com.orcl.resource.VendingResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.orcl.repository.VendingRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VendingService {

    @Autowired
    private VendingRepository vendingRepository;

    private static final Logger logger = LoggerFactory.getLogger(VendingResource.class);

    public void initMachine(List<CoinDTO> coinDTOList) {

        List<CoinStock> coinStocks = new ArrayList<>();

        for (CoinDTO coinDTO : coinDTOList) {
            CoinStock coinStock = new CoinStock();
            coinStock.setCoin(coinDTO.getCoinName());
            coinStock.setStock(coinDTO.getInitialCount());
            coinStocks.add(coinStock);
        }

        vendingRepository.saveInvetory(coinStocks);
    }

    public void depositCoin(CoinName coinName) {

        CoinInventory coinInventory = vendingRepository.getCoinInventory();
        coinInventory.getCoinInventory().forEach(coinStock -> {
            if(coinStock.getCoin().equals(coinName)) {
                coinStock.setStock(coinStock.getStock() + 1);
            }
        });

        CoinDeposit coinDeposit = new CoinDeposit();
        coinDeposit.setCoinName(coinName);
        coinDeposit.setDepositedDateTime(new Date());
        vendingRepository.depositCoin(coinDeposit);
    }

    public List<ChangeDTO> getChange(Long sumValue)  throws Exception {

        CoinName[] coinNames = getSortedCoinNamesBasedOnValueAsc();
        List<ChangeDTO> changeDTOS = new ArrayList<>();
        Long value = getChanges(sumValue, coinNames, changeDTOS);
        if(value == 0) {
            updateStock(changeDTOS);
        } else {
            throw new Exception(ErrorCode.ERROR_CODE_0004.message);
        }
        return changeDTOS;
    }

    private Long getChanges(Long sumValue, CoinName[] coinNames, List<ChangeDTO> changeDTOS) {
        Long value = sumValue;
        for (int i = 0; i < coinNames.length && value > 0; i++) {
            Long val = value;
            value  = value / coinNames[i].getValue();
            if(isCoinAvailableInStock(coinNames[i], value) && value > 0) {
                ChangeDTO changeDTO = new ChangeDTO();
                changeDTO.setCoinName(coinNames[i].name());
                changeDTO.setNoOfCoins(value);
                changeDTOS.add(changeDTO);
            }
            value  = val % coinNames[i].getValue();
        }
        return value;
    }

    private CoinName[] getSortedCoinNamesBasedOnValueAsc() {
        CoinName[] coinNames = CoinName.values();
        Arrays.sort(coinNames, new Comparator<CoinName>() {
            @Override
            public int compare(CoinName coinName1, CoinName coinName2) {
                return coinName2.getValue().compareTo(coinName1.getValue());
            }
        });
        return coinNames;
    }

    private boolean isCoinAvailableInStock(CoinName coinName, Long count) {
        List<CoinStock> list = vendingRepository.getCoinInventory().getCoinInventory();
        boolean result = false;
        for (CoinStock coinStock : list) {
            if(coinStock.getCoin().name().equals(coinName.name()) && coinStock.getStock() >= count) {
                result = true;
                break;
            }
        }
        return result;
    }

    private void updateStock(List<ChangeDTO> changeDTOS) {
        for(ChangeDTO changeDTO : changeDTOS) {
            vendingRepository.getCoinInventory().getCoinInventory().forEach(coinStock -> {
                if(coinStock.getCoin().name().equals(changeDTO.getCoinName())) {
                    coinStock.setStock(coinStock.getStock() - changeDTO.getNoOfCoins());
                }
            });
        }
    }

    public List<CoinStock> getInventory() {
        return vendingRepository.getCoinInventory().getCoinInventory();
    }

    public List<CoinDeposit> getAllRegisteredDeposit() {
        return vendingRepository.getCoinDeposits();
    }

}
