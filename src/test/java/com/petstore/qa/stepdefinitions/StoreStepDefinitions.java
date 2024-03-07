package com.petstore.qa.stepdefinitions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.petstore.qa.common.BaseStepDefinitions;
import com.petstore.qa.dto.store.InventoryStatusListDto;
import com.petstore.qa.dto.store.OrderDto;
import com.petstore.qa.steps.StoreSteps;
import com.petstore.qa.utils.DataGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import org.apache.http.HttpStatus;

public class StoreStepDefinitions extends BaseStepDefinitions {

  private InventoryStatusListDto inventoryResponse;
  private OrderDto orderRequested, orderResponse;

  @Steps StoreSteps storeSteps;

  @Given("a new order to be placed")
  public void aNewOrderToBePlaced() {
    orderRequested = DataGenerator.generateRandomOrderInfo();
  }

  @When("the user look for them")
  public void theUserLookForThem() {
    inventoryResponse = storeSteps.getInventoryByStatus();
  }

  @When("the user add the new order")
  public void theUserAddTheNewOrder() {
    orderResponse = storeSteps.placeOrder(orderRequested);
  }

  @Then("inventory is listed by status")
  public void inventoryIsListedByStatus() {
    validateResponse(HttpStatus.SC_OK, "schemas/inventoryListStatus.json");
  }

  @Then("new order is created successful")
  public void newOrderIsCreatedSuccessful() {
    validateResponse(HttpStatus.SC_OK, "schemas/order.json");

    assertThat(orderRequested.getId(), equalTo(orderResponse.getId()));
    assertThat(orderRequested.getPetId(), equalTo(orderResponse.getPetId()));
    assertThat(orderRequested.getQuantity(), equalTo(orderResponse.getQuantity()));
    assertThat(
        orderRequested.getShipDate().substring(0, 19),
        equalTo(orderResponse.getShipDate().substring(0, 19)));
    assertThat(orderRequested.getStatus(), equalTo(orderResponse.getStatus()));
    assertThat(orderRequested.isComplete(), equalTo(orderResponse.isComplete()));

    userDeletesThePurchaseOrder();
  }

  @When("user deletes the purchase order")
  public void userDeletesThePurchaseOrder() {
    storeSteps.deletePurchaseOrderById(orderRequested.getId());
  }

  @When("the user look for the purchase created")
  public void theUserLookForThePurchaseCreated() {
    orderResponse = storeSteps.findPurchaseOrderById(orderRequested.getId());
  }

  @Then("new order is deleted successful")
  public void newOrderIsDeletedSuccessful() {
    validateResponse(HttpStatus.SC_OK, null);
  }

  @Then("the order is listed")
  public void theOrderIsListed() {
    validateResponse(HttpStatus.SC_OK, "schemas/order.json");
    userDeletesThePurchaseOrder();
  }
}
