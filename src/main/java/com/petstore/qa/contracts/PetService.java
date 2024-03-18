package com.petstore.qa.contracts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.petstore.qa.dto.pet.PetsResponseDto;
import io.restassured.response.Response;
import java.util.ArrayList;

public interface PetService {

  Response findPetsByStatus(String status);

  PetsResponseDto findPetByID(int id) throws JsonProcessingException;

  ArrayList<PetsResponseDto> findPetByTags(ArrayList<String> tags) throws JsonProcessingException;

  PetsResponseDto addNewPet(PetsResponseDto newPet) throws JsonProcessingException;

  void deletePet(int id);

  PetsResponseDto updateExistingPet(PetsResponseDto existingPet) throws JsonProcessingException;
}
