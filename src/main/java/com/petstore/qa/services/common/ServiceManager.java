package com.petstore.qa.services.common;

import com.petstore.qa.contracts.PetService;
import com.petstore.qa.contracts.StoreService;
import com.petstore.qa.contracts.UserService;
import com.petstore.qa.services.PetServiceImpl;
import com.petstore.qa.services.StoreServiceImpl;
import com.petstore.qa.services.UserServiceImpl;
import java.util.Objects;

public abstract class ServiceManager {

  private static PetServiceImpl petServiceImpl;
  private static StoreServiceImpl storeServiceImpl;
  private static UserServiceImpl userServiceImpl;

  public static PetService getPetService() {
    return Objects.requireNonNullElseGet(petServiceImpl, PetServiceImpl::new);
  }

  public static StoreService getStoreService() {
    return Objects.requireNonNullElseGet(storeServiceImpl, StoreServiceImpl::new);
  }

  public static UserService getUserService() {
    return Objects.requireNonNullElseGet(userServiceImpl, UserServiceImpl::new);
  }
}
