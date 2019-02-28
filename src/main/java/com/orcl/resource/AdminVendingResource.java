package com.orcl.resource;

import com.orcl.dto.CoinDTO;
import com.orcl.model.CoinDeposit;
import com.orcl.model.CoinStock;
import com.orcl.model.ErrorCode;
import com.orcl.model.ErrorMessage;
import com.orcl.service.VendingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vending-api/admin")
public class AdminVendingResource {

    @Autowired
    private VendingService vendingService;

    private static final Logger logger = LoggerFactory.getLogger(AdminVendingResource.class);

    @RequestMapping(value = "/init-machine", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> initMachine(@RequestBody List<CoinDTO> coinDetails ){
        try{
            if(coinDetails != null && coinDetails.size() > 0) {
                vendingService.initMachine(coinDetails);
                return new ResponseEntity<String>(new HttpHeaders(), HttpStatus.NO_CONTENT);

            } else {
                ErrorMessage errorMessage = new ErrorMessage(ErrorCode.ERROR_CODE_0001);
                return ResponseEntity.badRequest().body(errorMessage);
            }
        } catch(Exception e){
            logger.error("VendingResource : initMachine : failed due to exception {}", e);
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/current-stock", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStock(){
        try{
            List<CoinStock> stock = vendingService.getInventory();
            return new ResponseEntity<List<CoinStock>>(stock, HttpStatus.OK);

        } catch(Exception e){
            logger.error("VendingResource : getChange : failed due to exception {}", e);
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/all-deposits", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllDeposit(){
        try{
            List<CoinDeposit> deposit = vendingService.getAllRegisteredDeposit();
            return new ResponseEntity<List<CoinDeposit>>(deposit, HttpStatus.OK);

        } catch(Exception e){
            logger.error("VendingResource : getAllDeposit : failed due to exception {}", e);
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
