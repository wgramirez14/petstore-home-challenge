package com.petstore.qa.contracts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.petstore.qa.dto.store.InventoryStatusListDto;
import com.petstore.qa.dto.store.OrderDto;

public interface StoreService {

  InventoryStatusListDto getInventoryByStatus() throws JsonProcessingException;

  OrderDto placeOrder(OrderDto orderDto) throws JsonProcessingException;

  void deletePurchaseOrderById(int orderId);

  OrderDto findPurchaseOrderById(int orderId) throws JsonProcessingException;
}
