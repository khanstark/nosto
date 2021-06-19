package com.example.exchanger.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

@Getter
@Setter
@ToString
public class ExchangerResponse implements Serializable {

  private String success;
  private Timestamp timestamp;
  private String base;
  private String date;
  private Map<String, Double> rates;

}
