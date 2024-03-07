package com.petstore.qa.stepdefinitions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.petstore.qa.common.BaseStepDefinitions;
import com.petstore.qa.dto.pet.PetsResponseDto;
import com.petstore.qa.dto.pet.TagsDto;
import com.petstore.qa.steps.PetSteps;
import com.petstore.qa.utils.DataGenerator;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.serenitybdd.annotations.Steps;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PetsStepDefinitions extends BaseStepDefinitions {

  private static final Logger logger = LoggerFactory.getLogger(PetsStepDefinitions.class);

  private ArrayList<PetsResponseDto> pets;
  private PetsResponseDto petRequested, petResponse;
  @Steps PetSteps petSteps;

  @Given("there are pets registered")
  public void thereArePetsRegistered() {
    pets = petSteps.findAvailablePets();
  }

  @Given("a new pet to be added")
  public void aNewPetToBeAdded() {
    petRequested = DataGenerator.generateRandomPetInfo();
  }

  @Given("a new pet to be added with missing info")
  public void aNewPetToBeAddedWithMissingInfo() {
    petRequested = DataGenerator.generateRandomPetInfo();
    petRequested.setId(null);
  }

  @Given("a pet that already exists")
  public void aPetThatAlreadyExists() {
    aNewPetToBeAdded();
    userRegistersTheNewPet();
  }

  @When("the user search for a pet by ID")
  public void theUserSearchForAPetByID() {

    assertThat(pets, not(nullValue()));
    petResponse = petSteps.findPetByID(pets.get(0).getId());
  }

  @When("the user search for the pets with the status {}")
  public void theUserSearchForThePetsWithTheStatusStatus(String status) {
    assertThat(pets, not(nullValue()));
    pets = petSteps.findPetsByStatus(status);
  }

  @When("the user search for a pet that is not being registered")
  public void theUserSearchForAPetThatIsNotBeingRegistered() {
    assertThat(pets, not(nullValue()));
    petResponse = petSteps.findPetByID(99999);
  }

  @When("the user search for the pets by tags {listOfStrings}")
  public void theUserSearchForThePetsByTagTagAndTag(List<String> list) {
    pets = petSteps.findPetByTags(list);
  }

  @When("user registers the new pet")
  public void userRegistersTheNewPet() {
    petResponse = petSteps.addNewPet(petRequested);
  }

  @When("user registers the pet already registered")
  public void userRegistersThePetAlreadyRegistered() {
    petRequested = petResponse;
    petResponse = petSteps.addNewPet(petRequested);
  }

  @When("the user delete the pet")
  public void theUserDeleteThePet() {
    petSteps.deletePet(petResponse.getId());
  }

  @When("user edits the pet")
  public void userEditsThePet() {
    petRequested = petResponse;
    petRequested.setName(DataGenerator.getRandomName());
    petResponse = petSteps.updateExistingPet(petRequested);
  }

  @Then("the pet is listed")
  public void thePetIsListed() {
    validateResponse(HttpStatus.SC_OK, "schemas/pet.json");

    assertThat(petResponse, not(nullValue()));
    assertThat(pets.get(0).getId(), equalTo(petResponse.getId()));
    assertThat(pets.get(0).getName(), equalTo(petResponse.getName()));
    assertThat(pets.get(0).getCategory().getId(), equalTo(petResponse.getCategory().getId()));
    assertThat(pets.get(0).getCategory().getName(), equalTo(petResponse.getCategory().getName()));
    assertThat(pets.get(0).getStatus(), equalTo(petResponse.getStatus()));
  }

  @Then("all pets are listed according to the status {}")
  public void allPetsAreListedAccordingToTheStatus(String status) {
    pets.forEach(pet -> assertThat(pet.getStatus(), equalTo(status)));
  }

  @Then("the pet is not found")
  public void thePetIsNotFound() {
    assertThat(petResponse, is(nullValue()));
  }

  @Then("all pets are listed according to the tags {listOfStrings} defined")
  public void allPetsAreListedAccordingToTheTagDefined(List<String> list) {

    boolean tagExist = false;
    for (PetsResponseDto pet : pets) {
      for (TagsDto tag : pet.getTags()) {
        for (String stringTag : list) {
          logger.warn(tag.getName() + " - " + stringTag);
          if (tag.getName().equals(stringTag)) {
            tagExist = true;
            break;
          }
        }
        if (tagExist) break;
      }
      if (tagExist) break;
    }

    assertThat(tagExist, is(Boolean.TRUE));
  }

  @Then("the new pet is registered/updated successful")
  public void theNewPetIsRegisteredSuccessful() {
    validateResponse(HttpStatus.SC_OK, "schemas/pet.json");

    petResponse = petSteps.findPetByID(petRequested.getId());
    assertThat(petResponse, not(nullValue()));
    assertThat(petRequested.getId(), equalTo(petResponse.getId()));
    assertThat(petRequested.getName(), equalTo(petResponse.getName()));
    assertThat(petRequested.getCategory().getId(), equalTo(petResponse.getCategory().getId()));
    assertThat(petRequested.getCategory().getName(), equalTo(petResponse.getCategory().getName()));
    assertThat(petRequested.getStatus(), equalTo(petResponse.getStatus()));

    petSteps.deletePet(petRequested.getId());

    petInfoIsNoLongerPresent();
  }

  @Then("the new pet is not registered")
  public void theNewPetIsNotRegistered() {
    validateResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, null);
    assertThat(petResponse, is(nullValue()));
  }

  @Then("pet info is no longer present")
  public void petInfoIsNoLongerPresent() {
    validateResponse(HttpStatus.SC_OK, null);
    petResponse = petSteps.findPetByID(petResponse.getId());
    assertThat(petResponse, is(nullValue()));
  }

  @ParameterType("(?:[^,]*)(?:,\\s?[^,]*)*")
  public List<String> listOfStrings(String arg) {
    return Arrays.asList(arg.split(",\\s?"));
  }
}
