package com.example.exchanger.services;

import com.example.exchanger.resp.CurrencyExchangerResponse;
import com.example.exchanger.resp.ExchangerResponse;
import com.example.exchanger.util.constant.ResourceConstants;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.NumberFormat;
import java.util.Locale;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class ExchangeServiceImpl implements IExchangeService {
  @Override
  public CurrencyExchangerResponse convert(String from, String to, Double amount) {
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://api.exchangeratesapi.io/v1/latest?access_key=" + ResourceConstants.API_KEY
        + "&base="
        + from + "&symbols=" + to;
    double convertedAmount = 0.0;
    try {
      ExchangerResponse response
          = restTemplate.getForObject(url, ExchangerResponse.class);
      assert response != null;
      double conversionRate = response.getRates().get(to);
      convertedAmount = amount * conversionRate;
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    Locale locale = getLocalesFromIso(to);
    String convertedCurrency = NumberFormat.getCurrencyInstance(locale).format(convertedAmount);
    CurrencyExchangerResponse currencyExchangerResponse = new CurrencyExchangerResponse();
    currencyExchangerResponse.setConvertedValue(convertedCurrency);
    return currencyExchangerResponse;
  }

  private Locale getLocalesFromIso(String iso4217code) {
    Locale returnValue = null;
    for (Locale locale : NumberFormat.getAvailableLocales()) {
      String code = NumberFormat.getCurrencyInstance(locale).
          getCurrency().getCurrencyCode();
      if (iso4217code.equals(code)) {
        returnValue = locale;
        break;
      }
    }
    return returnValue;
  }
}
