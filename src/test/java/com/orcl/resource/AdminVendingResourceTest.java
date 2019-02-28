package com.orcl.resource;

import com.orcl.dto.CoinDTO;
import com.orcl.model.CoinDeposit;
import com.orcl.service.VendingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminVendingResource.class)
public class AdminVendingResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendingService vendingService;

    @Test
    public void verifyInitMachine() throws Exception {
        String data = "[\n" +
                "\t{\n" +
                "    \t\"coinName\"\t\t:\t\"ONE_PENNY\",\n" +
                "    \t\"initialCount\"\t:\t100\n" +
                "\t},\n" +
                "\t{\n" +
                "    \t\"coinName\"\t\t:\t\"TWO_PENCE\",\n" +
                "    \t\"initialCount\"\t:\t200\n" +
                "\t}]";
        mockMvc.perform(post("/vending-api/admin/init-machine").content(data).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(204));
        verify(vendingService, times(1)).initMachine(Matchers.anyListOf(CoinDTO.class));

    }

    @Test
    public void verifyGetStock() throws Exception {
        mockMvc.perform(get("/vending-api/admin/current-stock"))
                .andExpect(status().is(200));
        verify(vendingService, times(1)).getInventory();
    }

}