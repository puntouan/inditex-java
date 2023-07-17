package com.challenge.inditexjava.product;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
@SuppressWarnings("NewClassNamingConvention")
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class GetProductRateByDateShould {

    @LocalServerPort
    private int port;

    private final long brandId = 1;
    private final long productId = 35455;

    @Test
    void returnTheCorrectRateWhenTheRequestIsAt10_00hOnThe14th() {
        shouldReturnTheRate1WhenApplicationDateIs("2020-06-14T10:00:00");
    }

    @Test
    void returnTheCorrectRateWhenTheRequestIsAt16_00hOnThe14th() {
        var applicationDate = "2020-06-14T16:00:00";

        given().
                param("date", applicationDate).
                port(port).
        when().
                get("/brand/" + brandId + "/product/" + productId + "/prices").
        then().
                statusCode(200).
                body("brandId", equalTo(1)).
                body("productId", equalTo(35455)).
                body("rateId", equalTo(2)).
                body("startDateTime", equalTo("2020-06-14T15:00:00")).
                body("endDateTime", equalTo("2020-06-14T18:30:00")).
                body("price", equalTo("25.45€"));

    }

    @Test
    void returnTheCorrectRateWhenTheRequestIsAt21_00hOnThe14th() {
        shouldReturnTheRate1WhenApplicationDateIs("2020-06-14T21:00:00");
    }

    @Test
    void returnTheCorrectRateWhenTheRequestIsAt10_00hOnThe15th() {
        var applicationDate = "2020-06-15T10:00:00";

        given().
                param("date", applicationDate).
                port(port).
                when().
                get("/brand/" + brandId + "/product/" + productId + "/prices").
                then().
                statusCode(200).
                body("brandId", equalTo(1)).
                body("productId", equalTo(35455)).
                body("rateId", equalTo(3)).
                body("startDateTime", equalTo("2020-06-15T00:00:00")).
                body("endDateTime", equalTo("2020-06-15T11:00:00")).
                body("price", equalTo("30.5€"));

    }

    @Test
    void returnTheCorrectRateWhenTheRequestIsAt21_00hOnThe16th() {
        var applicationDate = "2020-06-16T21:00:00";

        given().
                param("date", applicationDate).
                port(port).
        when().
                get("/brand/" + brandId + "/product/" + productId + "/prices").
        then().
                statusCode(200).
                body("brandId", equalTo(1)).
                body("productId", equalTo(35455)).
                body("rateId", equalTo(4)).
                body("startDateTime", equalTo("2020-06-15T16:00:00")).
                body("endDateTime", equalTo("2020-12-31T23:59:59")).
                body("price", equalTo("38.95€"));

    }

    @Test
    void returnErrorWhenThereIsNoRateForProductBrandAndDate() {
        var applicationDate = "2020-06-10T10:00";

        given().
                param("date", applicationDate).
                port(port).
        when().
                get("/brand/" + brandId + "/product/" + productId + "/prices").
        then().
                statusCode(404).
                body("status", equalTo("404 Not Found")).
                body(
                        "error",
                        equalTo("There is no rate for product " + productId + " and brand " + brandId + " for the date " + applicationDate)
                );

    }

    private void shouldReturnTheRate1WhenApplicationDateIs(String applicationDate) {
        given().
                param("date", applicationDate).
                port(port).
        when().
                get("/brand/" + brandId + "/product/" + productId + "/prices").
        then().
                statusCode(200).
                body("brandId", equalTo(1)).
                body("productId", equalTo(35455)).
                body("rateId", equalTo(1)).
                body("startDateTime", equalTo("2020-06-14T00:00:00")).
                body("endDateTime", equalTo("2020-12-31T23:59:59")).
                body("price", equalTo("35.5€"));

    }


}
