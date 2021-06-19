package com.example.exchanger.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;

@Slf4j
public abstract class GenericModelConverter<T> implements AttributeConverter<T, String> {

  @Autowired
  private ObjectMapper mapper;

  @SneakyThrows
  @Override
  public String convertToDatabaseColumn(T attribute) {
    if (null != attribute) {
      try {
        return mapper.writeValueAsString(attribute);
      } catch (JsonProcessingException e) {
        log.error("failed to convert to json field", e);
        throw new Exception(e.getMessage());
      }
    }
    return null;
  }

  @SneakyThrows
  @Override
  public T convertToEntityAttribute(String dbData) {
    if (null != dbData && !StringUtils.isEmpty(dbData)) {
      try {
        return mapper.readValue(dbData, getType());
      } catch (JsonProcessingException e) {
        log.error("failed to convert json to dto", e);
        throw new Exception(e.getMessage());
      }
    }
    return null;
  }

  public abstract Class<T> getType();

}
