package rest_api.petshop_test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

public class BaseTest {
    private final static String BASE_URI = "https://petstore.swagger.io/v2";
    protected static RequestSpecification requestSpecification;

    @BeforeClass
    public void configureRestAssure() {
        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = new RequestSpecBuilder().setBaseUri(BASE_URI)
                .addHeader("api_key", "1q2w3e4r")
                .setContentType(ContentType.JSON).addFilter(new AllureRestAssured()).build();
    }

    @AfterClass
    public void resetConfigureRestAssure() {
        RestAssured.reset();
    }
}
