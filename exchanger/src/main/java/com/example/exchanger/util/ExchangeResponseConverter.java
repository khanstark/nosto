package com.example.exchanger.util;

import com.example.exchanger.resp.ExchangerResponse;

public class ExchangeResponseConverter extends GenericModelConverter<ExchangerResponse> {
  @Override
  public Class<ExchangerResponse> getType() {
    return ExchangerResponse.class;
  }
}
