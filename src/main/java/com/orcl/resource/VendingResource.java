package com.orcl.resource;

import com.orcl.dto.ChangeDTO;
import com.orcl.model.CoinName;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vending-api")
public class VendingResource {

    @Autowired
    private VendingService vendingService;

    private static final Logger logger = LoggerFactory.getLogger(VendingResource.class);

    @RequestMapping(value = "/deposit/{coinName}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> depositCoin(@PathVariable("coinName") String coinName){
        try{
            if(coinName != null && coinName.length() > 0) {
                vendingService.depositCoin(CoinName.valueOf(coinName));
                return new ResponseEntity<String>(new HttpHeaders(), HttpStatus.NO_CONTENT);

            } else {
                ErrorMessage errorMessage = new ErrorMessage(ErrorCode.ERROR_CODE_0002);
                return ResponseEntity.badRequest().body(errorMessage);
            }
        } catch(Exception e){
            logger.error("VendingResource : depositCoin : failed due to exception {}", e);
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/return/change/{sumValueInPence}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getChange(@PathVariable("sumValueInPence") Long sumValue){
        try{
            if(sumValue != null ) {
                List<ChangeDTO> changeDTOS = vendingService.getChange(sumValue);
                return new ResponseEntity<List<ChangeDTO>>(changeDTOS, HttpStatus.OK);

            } else {
                ErrorMessage errorMessage = new ErrorMessage(ErrorCode.ERROR_CODE_0003);
                return ResponseEntity.badRequest().body(errorMessage);
            }
        } catch(Exception e){
            if(e.getMessage().contains(ErrorCode.ERROR_CODE_0004.message)) {
                ErrorMessage errorMessage = new ErrorMessage(ErrorCode.ERROR_CODE_0004);
                return ResponseEntity.badRequest().body(errorMessage);
            } else {
                logger.error("VendingResource : getChange : failed due to exception {}", e);
                return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}
