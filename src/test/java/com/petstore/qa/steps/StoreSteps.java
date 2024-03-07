package com.petstore.qa.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.petstore.qa.dto.store.InventoryStatusListDto;
import com.petstore.qa.dto.store.OrderDto;
import com.petstore.qa.services.common.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoreSteps {

  private static final Logger logger = LoggerFactory.getLogger(PetSteps.class);

  public InventoryStatusListDto getInventoryByStatus() {
    try {
      return ServiceManager.getStoreService().getInventoryByStatus();
    } catch (JsonProcessingException e) {
      logger.warn(e.getMessage());
      return null;
    }
  }

  public OrderDto placeOrder(OrderDto order) {
    try {
      return ServiceManager.getStoreService().placeOrder(order);
    } catch (JsonProcessingException e) {
      logger.warn(e.getMessage());
      return null;
    }
  }

  public void deletePurchaseOrderById(int orderId) {

    ServiceManager.getStoreService().deletePurchaseOrderById(orderId);
  }

  public OrderDto findPurchaseOrderById(int orderId) {

    try {
      return ServiceManager.getStoreService().findPurchaseOrderById(orderId);
    } catch (JsonProcessingException e) {
      logger.warn(e.getMessage());
      return null;
    }
  }
}
