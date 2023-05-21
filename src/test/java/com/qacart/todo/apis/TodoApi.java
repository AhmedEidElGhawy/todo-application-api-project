package com.qacart.todo.apis;

import com.qacart.todo.base.Specification;
import com.qacart.todo.data.Route;
import com.qacart.todo.models.Todo;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class TodoApi {

    public static Response addTodo(Todo todo, String token){
        return given()
            .spec(Specification.getRequestSpec())
            .body(todo)
            .auth().oauth2(token)
        .when()
            .post(Route.TODOS_ROUTE)
        .then()
            .log().all()
            .extract().response();
}
    public static Response  getTodo(String token, String taskID){
         return given()
            .spec(Specification.getRequestSpec())
            .auth().oauth2(token)
         .when()
            .get(Route.TODOS_ROUTE+"/"+taskID)
         .then()
            .log().all()
            .extract().response();
}
public static Response deleteATodo(String token, String taskID){
    return given()
            .spec(Specification.getRequestSpec())
            .auth().oauth2(token)
    .when()
            .delete(Route.TODOS_ROUTE+"/"+taskID)
    .then()
            .log().all()
            .extract().response();
}

}