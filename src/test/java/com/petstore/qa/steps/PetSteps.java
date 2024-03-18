package com.petstore.qa.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.petstore.qa.decorators.PetServiceConsumer;
import com.petstore.qa.dto.pet.PetsResponseDto;
import com.petstore.qa.enums.PetStatus;
import com.petstore.qa.services.common.ServiceManager;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PetSteps {

  private static final Logger logger = LoggerFactory.getLogger(PetSteps.class);

  @Step("Find all available pets")
  public ArrayList<PetsResponseDto> findAvailablePets() {

    try {
      return PetServiceConsumer.findPetsByStatus(PetStatus.AVAILABLE.getValue());
    } catch (JsonProcessingException e) {
      logger.warn(e.getMessage());
      return null;
    }
  }

  @Step("Find a pet with ID {}")
  public PetsResponseDto findPetByID(int id) {

    try {
      return ServiceManager.getPetService().findPetByID(id);
    } catch (JsonProcessingException e) {
      logger.warn(e.getMessage());
      return null;
    }
  }

  @Step("Find pets with status {}")
  public ArrayList<PetsResponseDto> findPetsByStatus(String status) {
    try {
      return PetServiceConsumer.findPetsByStatus(PetStatus.fromName(status).getValue());
    } catch (JsonProcessingException e) {
      logger.warn(e.getMessage());
      return null;
    }
  }

  @Step("Find pets with tags {}")
  public ArrayList<PetsResponseDto> findPetByTags(List<String> tags) {
    try {
      return ServiceManager.getPetService().findPetByTags(new ArrayList<>(tags));
    } catch (JsonProcessingException e) {
      logger.warn(e.getMessage());
      return null;
    }
  }

  @Step("Add a new pet {}")
  public PetsResponseDto addNewPet(PetsResponseDto newPet) {
    try {
      return ServiceManager.getPetService().addNewPet(newPet);
    } catch (JsonProcessingException e) {
      logger.warn(e.getMessage());
      return null;
    }
  }

  @Step("Delete pet with ID {}")
  public void deletePet(int id) {
    ServiceManager.getPetService().deletePet(id);
  }

  @Step("Update pet {}")
  public PetsResponseDto updateExistingPet(PetsResponseDto existingPet) {
    try {
      return ServiceManager.getPetService().updateExistingPet(existingPet);
    } catch (JsonProcessingException e) {
      logger.warn(e.getMessage());
      return null;
    }
  }
}
