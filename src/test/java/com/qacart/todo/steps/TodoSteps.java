package com.qacart.todo.steps;

import com.github.javafaker.Faker;
import com.qacart.todo.apis.TodoApi;
import com.qacart.todo.models.Todo;
import io.restassured.response.Response;

public class TodoSteps {
    public static Todo generateRandumTodo(){
        Faker faker = new Faker();
        boolean isCompleted = false;
        String item = faker.book().title();
        return new Todo (isCompleted, item);
    }
    public static String getTodoID(Todo todo, String token){
        Response response = TodoApi.addTodo(todo,token);
        return response.body().path("_id");

    }
}
