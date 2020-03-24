package rest_api.petshop_test;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import rest_api.petshop.dto.UserDto;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTest extends BaseTest {
    protected UserDto user;

    @BeforeMethod
    public void initUser() {
        user = new UserDto()
                .setId(44445555)
                .setUsername("HaRRy")
                .setFirstName("Harry")
                .setLastName("Potter");
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
}
