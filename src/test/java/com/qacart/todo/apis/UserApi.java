package com.qacart.todo.apis;

import com.qacart.todo.base.Specification;
import com.qacart.todo.data.Route;
import com.qacart.todo.models.User;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserApi {
    public static Response register(User user){
        return given()
                .spec(Specification.getRequestSpec())
                .body(user)
        .when()
                .post(Route.REGISTER_ROUTE)
        .then()
                .log().all()
                .extract().response();
    }
    public static Response login(User user){

        return given()
                .spec(Specification.getRequestSpec())
                .body(user)
        .when()
                .post(Route.LOGIN_ROUTE)
        .then()
                .log().all()
                .extract().response();
    }
}
