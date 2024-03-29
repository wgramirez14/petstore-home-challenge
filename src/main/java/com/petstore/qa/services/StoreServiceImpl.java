package com.petstore.qa.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.petstore.qa.contracts.StoreService;
import com.petstore.qa.dto.store.InventoryStatusListDto;
import com.petstore.qa.dto.store.OrderDto;
import com.petstore.qa.services.common.BaseService;
import io.restassured.response.Response;
import java.text.MessageFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoreServiceImpl extends BaseService implements StoreService {

  private static final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);

  @Override
  public InventoryStatusListDto getInventoryByStatus() throws JsonProcessingException {
    Response response = httpRequest.get(properties.getProperty("store.inventory"));

    logger.info(MessageFormat.format("Response: {0}", response.body().asString()));

    return objectMapper.readValue(response.body().asString(), InventoryStatusListDto.class);
  }

  @Override
  public OrderDto placeOrder(OrderDto orderDto) throws JsonProcessingException {
    Response response =
        httpRequest
            .body(objectMapper.writeValueAsString(orderDto))
            .post(properties.getProperty("store.placeOrder"));

    logger.info(MessageFormat.format("Response: {0}", response.body().asString()));
    return objectMapper.readValue(response.body().asString(), OrderDto.class);
  }

  @Override
  public void deletePurchaseOrderById(int orderId) {

    httpRequest.pathParam("orderId", orderId).delete(properties.getProperty("store.orderById"));
  }

  @Override
  public OrderDto findPurchaseOrderById(int orderId) throws JsonProcessingException {

    Response response =
        httpRequest.pathParam("orderId", orderId).get(properties.getProperty("store.orderById"));

    logger.info(MessageFormat.format("Response: {0}", response.body().asString()));

    return objectMapper.readValue(response.body().asString(), OrderDto.class);
  }
}
