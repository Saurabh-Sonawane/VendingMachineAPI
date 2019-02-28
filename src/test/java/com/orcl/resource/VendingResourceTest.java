package com.orcl.resource;

import com.orcl.dto.ChangeDTO;
import com.orcl.model.CoinName;
import com.orcl.service.VendingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VendingResource.class)
public class VendingResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendingService vendingService;

    @Test
    public void verifyDepositCoinAPI() throws Exception {

        mockMvc.perform(post("/vending-api/deposit/ONE_PENNY"))
                .andExpect(status().is(204));
    }

    @Test
    public void verifyGetChangeAPI() throws Exception {
        //given
        List<ChangeDTO> changeDTOS = new ArrayList<>();
        ChangeDTO changeDTO = new ChangeDTO();
        changeDTO.setCoinName(CoinName.ONE_POUND.name());
        changeDTO.setNoOfCoins(10L);
        changeDTOS.add(changeDTO);
        changeDTO = new ChangeDTO();
        changeDTO.setCoinName(CoinName.ONE_PENNY.name());
        changeDTO.setNoOfCoins(5L);
        changeDTOS.add(changeDTO);
        when(vendingService.getChange(101L)).thenReturn(changeDTOS);

        //when
        //then
        mockMvc.perform(get("/vending-api/return/change/101"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].coinName", is("ONE_POUND")))
                .andExpect(jsonPath("$[0].noOfCoins", is(10)))
                .andExpect(jsonPath("$[1].coinName", is("ONE_PENNY")))
                .andExpect(jsonPath("$[1].noOfCoins", is(5)));
    }

}