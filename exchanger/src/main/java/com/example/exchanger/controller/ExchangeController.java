package com.example.exchanger.controller;

import com.example.exchanger.resp.CurrencyExchangerResponse;
import com.example.exchanger.services.IExchangeService;
import com.example.exchanger.util.constant.ResourceConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(ResourceConstants.API_ROOT + ResourceConstants.API_VERSION + ResourceConstants.API_CONVERT)
public class ExchangeController {

  @Autowired
  private IExchangeService exchangeService;

  @GetMapping
  public ResponseEntity<CurrencyExchangerResponse> convert(@RequestParam String from, @RequestParam String to, @RequestParam Double amount) {
    log.info("Attempting conversion from {} to {}", from, to);
    return ResponseEntity.ok(exchangeService.convert(from, to, amount));
  }

}
