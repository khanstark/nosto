package com.example.exchanger.services;

import com.example.exchanger.resp.CurrencyExchangerResponse;

/**
 * Service interface for heimdall utilities
 *
 * @author mohdkaif
 *
 */
public interface IExchangeService {

  /**
   * Method to convert given monetary value to given currency
   *
   * @param from
   * @param to
   * @param amount
   * @return
   */
  CurrencyExchangerResponse convert(String from, String to, Double amount);

}
