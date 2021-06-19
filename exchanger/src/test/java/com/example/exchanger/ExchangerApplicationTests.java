package com.example.exchanger;

import com.example.exchanger.controller.ExchangeController;
import com.example.exchanger.resp.CurrencyExchangerResponse;
import com.example.exchanger.services.IExchangeService;
import com.example.exchanger.util.constant.ResourceConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;

class ExchangerApplicationTests {

  private MockMvc mockMvc;

  @Mock
  IExchangeService exchangeService;

  @InjectMocks
  private ExchangeController classUnderTest;

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(classUnderTest)
        .setControllerAdvice(Exception.class)
        .build();
  }


  @Test
	public void testGetConvertAPI() throws Exception {
    LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
    requestParams.add("from","EUR");
    requestParams.add("to","INR");
    requestParams.add("amount","1000.0");
    MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get(ResourceConstants.API_ROOT + ResourceConstants.API_VERSION + ResourceConstants.API_CONVERT);
    mockMvc.perform(getRequest.params(requestParams)).andExpect(MockMvcResultMatchers.status().isOk());
	}
}
