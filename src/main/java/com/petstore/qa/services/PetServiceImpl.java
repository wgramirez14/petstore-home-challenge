package com.petstore.qa.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.petstore.qa.contracts.PetService;
import com.petstore.qa.dto.pet.PetsResponseDto;
import com.petstore.qa.services.common.BaseService;
import io.restassured.response.Response;
import java.text.MessageFormat;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PetServiceImpl extends BaseService implements PetService {

  private static final Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);

  @Override
  public Response findPetsByStatus(String status) {

    Response response =
        httpRequest.queryParam("status", status).get(properties.getProperty("pets.petByStatus"));

    logger.info(MessageFormat.format("Response: {0}", response.body().asString()));

    return response;
  }

  @Override
  public PetsResponseDto findPetByID(int id) throws JsonProcessingException {

    Response response = httpRequest.pathParam("id", id).get(properties.getProperty("pets.petById"));

    logger.info(MessageFormat.format("Response: {0}", response.body().asString()));
    return objectMapper.readValue(response.body().asString(), PetsResponseDto.class);
  }

  @Override
  public ArrayList<PetsResponseDto> findPetByTags(ArrayList<String> tags)
      throws JsonProcessingException {

    tags.forEach(tag -> httpRequest.queryParam("tags", tag));
    Response response = httpRequest.get(properties.getProperty("pets.petByTags"));

    logger.info(MessageFormat.format("Response: {0}", response.body().asString()));

    return objectMapper.readValue(
        response.body().asString(),
        objectMapper
            .getTypeFactory()
            .constructCollectionType(ArrayList.class, PetsResponseDto.class));
  }

  @Override
  public PetsResponseDto addNewPet(PetsResponseDto newPet) throws JsonProcessingException {

    Response response =
        httpRequest
            .body(objectMapper.writeValueAsString(newPet))
            .post(properties.getProperty("pets.pet"));
    logger.info(MessageFormat.format("Response: {0}", response.body().asString()));
    return objectMapper.readValue(response.body().asString(), PetsResponseDto.class);
  }

  @Override
  public void deletePet(int id) {
    httpRequest.pathParam("id", id).delete(properties.getProperty("pets.petById"));
  }

  @Override
  public PetsResponseDto updateExistingPet(PetsResponseDto existingPet)
      throws JsonProcessingException {
    Response response =
        httpRequest
            .body(objectMapper.writeValueAsString(existingPet))
            .put(properties.getProperty("pets.pet"));
    logger.info(MessageFormat.format("Response: {0}", response.body().asString()));
    return objectMapper.readValue(response.body().asString(), PetsResponseDto.class);
  }
}
