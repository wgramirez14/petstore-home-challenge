package com.petstore.qa.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.qa.dto.pet.PetsResponseDto;
import com.petstore.qa.services.common.ServiceManager;
import io.restassured.response.Response;
import java.util.ArrayList;

public abstract class PetServiceConsumer {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static ArrayList<PetsResponseDto> findPetsByStatus(String status)
      throws JsonProcessingException {

    Response response = ServiceManager.getPetService().findPetsByStatus(status);

    return new ObjectMapper()
        .readValue(
            response.body().asString(),
            objectMapper
                .getTypeFactory()
                .constructCollectionType(ArrayList.class, PetsResponseDto.class));
  }
}
