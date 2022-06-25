package ru.yandex.praktikum;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import ru.yandex.praktikum.scooter.api.model.Courier;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.scooter.api.CourierClient.createCourier;
//import static io.restassured.RestAssured.then;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class CourierTest {

    @Test
    public void courierTest(){


        Courier courier = new Courier();
        Response responseCreate = createCourier(courier);
        //RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        assertEquals(responseCreate.statusCode(), SC_CREATED);
        assertTrue(responseCreate.body().jsonPath().getBoolean("ok"));
       /* Boolean ok = given()
                .spec(requestSpecification)
                .body(courier)
                .when()
                .post(BASE_URL + "/api/v1/courier/")
                .then()
                .assertThat()
                .log().all()
                .statusCode(SC_CREATED)
                .extract()
                .path("ok");
        assertTrue(ok);*/
    }

    @Test
    public void sandboxTest(){
        Courier courier = new Courier();
        Response responseCreate = createCourier(courier);
        //RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        assertEquals(responseCreate.statusCode(), SC_CREATED);
        assertTrue(responseCreate.body().jsonPath().getBoolean("ok"));
       /* Boolean ok = given()
                .spec(requestSpecification)
                .body(courier)
                .when()
                .post(BASE_URL + "/api/v1/courier/")
                .then()
                .assertThat()
                .log().all()
                .statusCode(SC_CREATED)
                .extract()
                .path("ok");
        assertTrue(ok);*/
    }
}
