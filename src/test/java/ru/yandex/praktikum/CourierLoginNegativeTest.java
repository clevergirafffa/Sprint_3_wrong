package ru.yandex.praktikum;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.scooter.api.model.Courier;
import ru.yandex.praktikum.scooter.api.model.CourierCredentials;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.scooter.api.CourierClient.*;

public class CourierLoginNegativeTest {
    private int courierId = 0;
    private Response responseCreate;
    private  CourierCredentials courierCredentials;
    Courier courier;

    @Before
    public void init(){
        courier = new Courier();
        responseCreate = createCourier(courier);
        assertEquals(responseCreate.statusCode(), SC_CREATED);
        assertTrue(responseCreate.body().jsonPath().getBoolean("ok"));
        courierCredentials = new CourierCredentials(courier.getLogin(), courier.getPassword());
        Response responseLogin = login(courierCredentials);
        assertEquals(SC_OK, responseLogin.statusCode());
        courierId = responseLogin.body().jsonPath().getInt("id");

    }
    @After
    public void cleanup() {
        deleteCourier(courierId);
    }

    @Test
    public void courierWrongLoginTest() {
        courierCredentials.corruptLogin();
        Response responseLogin = login(courierCredentials);
        assertEquals(SC_NOT_FOUND, responseLogin.statusCode());
        assertEquals("Учетная запись не найдена", responseLogin.body().jsonPath().getString("message"));
    }

    @Test
    public void courierWrongPasswordTest() {
        courierCredentials.corruptPassword();
        Response responseLogin = login(courierCredentials);
        assertEquals(SC_NOT_FOUND, responseLogin.statusCode());
        assertEquals("Учетная запись не найдена", responseLogin.body().jsonPath().getString("message"));
    }

    @Test
    public void courierEmptyLoginTest() {
        courierCredentials.removeLogin();
        Response responseLogin = login(courierCredentials);
        assertEquals(SC_BAD_REQUEST, responseLogin.statusCode());
        assertEquals("Недостаточно данных для входа", responseLogin.body().jsonPath().getString("message"));
    }

    @Test
    public void courierEmptyPasswordTest() {
        courierCredentials.removePassword();
        Response responseLogin = login(courierCredentials);
        assertEquals(SC_BAD_REQUEST, responseLogin.statusCode());
        assertEquals("Недостаточно данных для входа", responseLogin.body().jsonPath().getString("message"));
    }


}
