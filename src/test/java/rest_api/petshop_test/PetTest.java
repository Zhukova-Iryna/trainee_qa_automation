package rest_api.petshop_test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;;
import rest_api.petshop.OrderStatus;
import rest_api.petshop.PetStatus;
import rest_api.petshop.dto.OrderDto;
import rest_api.petshop.dto.PetDto;
import org.testng.annotations.Test;
import rest_api.petshop.dto.UserDto;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PetTest {
    private final static String BASE_URI = "https://petstore.swagger.io/v2";
    private RequestSpecification requestSpecification;
    private PetDto pet;
    private UserDto user;
    private OrderDto order;

    @BeforeClass
    public void configureRestAssure() {
        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = new RequestSpecBuilder().setBaseUri(BASE_URI)
                .addHeader("api_key", "1q2w3e4r")
                .setContentType(ContentType.JSON).build();
    }

    @BeforeMethod
    public void initDtos() {
        pet = new PetDto()
                .setId(364526)
                .setName("Jack")
                .setStatus(PetStatus.available);
        user = new UserDto()
                .setId(44445555)
                .setUsername("HaRRy")
                .setFirstName("Harry")
                .setLastName("Potter");
        order = new OrderDto()
                .setId(789987)
                .setPetId(3)
                .setQuantity(1)
                .setStatus(OrderStatus.placed)
                .setComplete(true);
    }

    @Test
    public void addNewPetTest() {
        PetDto responsePet = given().spec(requestSpecification)
                .body(pet)
                .when().post("/pet")
                .then().extract().response().as(PetDto.class);
        assertThat(responsePet).isEqualTo(pet);
    }

    @Test
    public void addNewUserTest() {
        JsonPath path = given().spec(requestSpecification)
                .body(user)
                .when().post("/user")
                .then().statusCode(200).extract().jsonPath();
        assertThat(Long.parseLong(path.get("message"))).isEqualTo(user.getId());
    }

    @Test
    public void addPasswordForUserTest() {
        given().spec(requestSpecification)
                .body(user)
                .when().post("/user")
                .then().statusCode(200);
        JsonPath path = given().spec(requestSpecification)
                .body(user.setPassword("80user80"))
                .when().put(String.format("/user/%s", user.getUsername()))
                .then().statusCode(200).extract().jsonPath();
        assertThat(Long.parseLong(path.get("message"))).isEqualTo(user.getId());
    }

    @Test
    public void logsUserIntoSystemTest() {
        JsonPath path = given().spec(requestSpecification)
                .body(user.setPassword("80user80"))
                .when().get("/user/login")
                .then().statusCode(200).extract().jsonPath();
        assertThat(path.get("message").toString()).contains("logged in user session:");
    }

    @Test
    public void findNewPetInAvailablePetsListTest() {
        given().spec(requestSpecification)
                .body(pet)
                .when().post("/pet")
                .then().statusCode(200);
        JsonPath path = given().spec(requestSpecification)
                .when().get("/pet/findByStatus?status=available")
                .then().extract().response().jsonPath();
        List<PetDto> pets = path.getList("", PetDto.class);
        assertThat(pets).contains(pet);
    }

    @Test
    public void createNewOrderTest() {
        OrderDto responseOrder = given().spec(requestSpecification)
                .body(order)
                .when().post("/store/order")
                .then().extract().response().as(OrderDto.class);
        assertThat(responseOrder).isEqualTo(order);
    }

    @Test
    public void correctInventoryResponseDataTest() {
        int availableStatusPets = given().spec(requestSpecification)
                .when().get("/pet/findByStatus?status=available")
                .then().extract().response().jsonPath().getList("", PetDto.class).size();
        int pendingStatusPets = given().spec(requestSpecification)
                .when().get("/pet/findByStatus?status=pending")
                .then().extract().response().jsonPath().getList("", PetDto.class).size();
        int soldStatusPets = given().spec(requestSpecification)
                .when().get("/pet/findByStatus?status=sold")
                .then().extract().response().jsonPath().getList("", PetDto.class).size();
        JsonPath path = given().spec(requestSpecification)
                .when().get("/store/inventory")
                .then().extract().response().jsonPath();
        assertThat(path.get("available").equals(availableStatusPets)
                && path.get("pending").equals(pendingStatusPets)
                && path.get("sold").equals(soldStatusPets)).isTrue();
    }

    @Test
    public void changePetStatusToSoldTest() {
        given().spec(requestSpecification)
                .body(pet)
                .when().post("/pet")
                .then().statusCode(200);
        JsonPath path = given().spec(requestSpecification)
                .body(pet.setStatus(PetStatus.sold))
                .when().post("/pet")
                .then().extract().response().jsonPath();
        assertThat(path.get("status").toString()).isEqualTo("sold");
    }

    @Test
    public void deletePetTest() {
        given().spec(requestSpecification)
                .body(pet)
                .when().post("/pet")
                .then().statusCode(200);
        JsonPath path = given().spec(requestSpecification)
                .when().delete(String.format("/pet/%d", pet.getId()))
                .then().statusCode(200).extract().response().jsonPath();
        assertThat(Long.parseLong(path.get("message"))).isEqualTo(pet.getId());
    }

    @Test
    public void deleteOrderTest() {
        given().spec(requestSpecification)
                .body(order)
                .when().post("/store/order")
                .then().statusCode(200);
        JsonPath path = given().spec(requestSpecification)
                .when().delete(String.format("/store/order/%d", order.getId()))
                .then().statusCode(200).extract().jsonPath();
        assertThat(Integer.parseInt(path.get("message"))).isEqualTo(order.getId());
    }
}
