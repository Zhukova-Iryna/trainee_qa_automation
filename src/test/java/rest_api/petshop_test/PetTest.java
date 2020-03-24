package rest_api.petshop_test;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.*;
import rest_api.petshop.PetStatus;
import rest_api.petshop.dto.PetDto;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PetTest extends BaseTest {
    protected PetDto pet;

    @BeforeMethod
    public void initDtos() {
        pet = new PetDto()
                .setId(364526)
                .setName("Jack")
                .setStatus(PetStatus.AVAILABLE);
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
                .body(pet.setStatus(PetStatus.SOLD))
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
}
