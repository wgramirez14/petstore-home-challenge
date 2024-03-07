package com.petstore.qa.utils;

import com.github.javafaker.Faker;
import com.petstore.qa.dto.pet.CategoryDto;
import com.petstore.qa.dto.pet.PetsResponseDto;
import com.petstore.qa.dto.pet.TagsDto;
import com.petstore.qa.dto.store.OrderDto;
import com.petstore.qa.dto.user.UserDto;
import com.petstore.qa.enums.PetStatus;
import com.petstore.qa.enums.PetType;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class DataGenerator {

  private static final Faker faker = new Faker();

  private static final ArrayList<TagsDto> tags = new ArrayList<>();

  public static PetsResponseDto generateRandomPetInfo() {
    tags.add(TagsDto.builder().id(1).name("tag1").build());

    return PetsResponseDto.builder()
        .id(faker.number().numberBetween(100, 999))
        .name(faker.dog().name())
        .status(PetStatus.AVAILABLE.getValue())
        .category(CategoryDto.builder().id(PetType.DOG.getID()).name(PetType.DOG.getName()).build())
        .tags(tags)
        .photoUrls(new ArrayList<>())
        .build();
  }

  public static String getRandomName() {
    return faker.dog().name();
  }

  public static OrderDto generateRandomOrderInfo() {

    return OrderDto.builder()
        .id(faker.number().numberBetween(100, 999))
        .petId(faker.number().numberBetween(100, 999))
        .quantity(faker.number().numberBetween(1, 10))
        .shipDate(LocalDateTime.now().toString())
        .status("placed")
        .complete(true)
        .build();
  }

  public static UserDto generateRandomUserInfo() {

    return UserDto.builder()
        .id(faker.number().numberBetween(100, 999))
        .username(faker.name().username())
        .firstName(faker.name().firstName())
        .lastName(faker.name().lastName())
        .email(faker.internet().safeEmailAddress())
        .password(faker.internet().password())
        .phone(faker.phoneNumber().cellPhone())
        .userStatus(1)
        .build();
  }
}
