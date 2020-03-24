package rest_api.petshop_test;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import rest_api.petshop.OrderStatus;
import rest_api.petshop.dto.OrderDto;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest extends BaseTest {
    protected OrderDto order;

    @BeforeMethod
    public void initDtos() {
        order = new OrderDto()
                .setId(789987)
                .setPetId(3)
                .setQuantity(1)
                .setStatus(OrderStatus.PLACED)
                .setComplete(true);
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
