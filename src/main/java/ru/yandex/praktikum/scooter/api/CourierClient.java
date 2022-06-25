package ru.yandex.praktikum.scooter.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.yandex.praktikum.scooter.api.model.Courier;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class CourierClient extends BaseApiClient {

    public static Response createCourier(Courier courier){
        return  given()
                .spec(getRequestSpecification())
                .body(courier)
                .when()
                .post(BASE_URL + "/api/v1/courier/");
    }

}
