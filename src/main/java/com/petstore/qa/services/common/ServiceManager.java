package com.petstore.qa.services.common;

import com.petstore.qa.services.PetService;
import com.petstore.qa.services.StoreService;
import com.petstore.qa.services.UserService;
import java.util.Objects;

public class ServiceManager {

  private static PetService petService;
  private static StoreService storeService;
  private static UserService userService;

  public static PetService getPetService() {

    return Objects.requireNonNullElseGet(petService, PetService::new);
  }

  public static StoreService getStoreService() {

    return Objects.requireNonNullElseGet(storeService, StoreService::new);
  }

  public static UserService getUserService() {

    return Objects.requireNonNullElseGet(userService, UserService::new);
  }
}
