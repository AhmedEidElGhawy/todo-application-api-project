package com.qacart.todo.testcases;

import com.qacart.todo.apis.TodoApi;
import com.qacart.todo.data.ErrorMessages;
import com.qacart.todo.models.Error;
import com.qacart.todo.models.Todo;
import com.qacart.todo.steps.TodoSteps;
import com.qacart.todo.steps.UserSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
@Feature("Todo Feature")
public class TodoTest {
    @Story("Should Be Able To Add Todo")
    @Test (description = "Should Be Able To Add Todo")
    public void shouldBeAbleToAddTodo() {
        Todo todo = TodoSteps.generateRandumTodo();
        String token = UserSteps.getUserToken();
        Response response = TodoApi.addTodo(todo,token);
        Todo returnedTodo = response.body().as(Todo.class);

        assertThat(response.statusCode(),equalTo(201));
        assertThat(returnedTodo.getItem(),equalTo(todo.getItem()));
        assertThat(returnedTodo.getIsCompleted(),is(equalTo(todo.getIsCompleted())));
    }
    @Story("Should Not Be Able To Add Todo If IsCompleted Is Missing")
    @Test (description = "Should Not Be Able To Add Todo If IsCompleted Is Missing")
    public void shouldNotBeAbleToAddTodoIfIsCompletedIsMissing() {
        Todo todo = new Todo( "Learn Appium");
        String token = UserSteps.getUserToken();
        Response response = TodoApi.addTodo(todo,token);
        Error returnedError = response.body().as(Error.class);

        assertThat(response.statusCode(),equalTo(400));
        assertThat(returnedError.getMessage(),equalTo(ErrorMessages.IS_COMPLETED_IS_REQUIRED));
    }
    @Story("Should Be Able To Get A Todo By Id")
    @Test (description = "Should Be Able To Get A Todo By Id")
    public void shouldBeAbleToGetATodoById() {
        String token = UserSteps.getUserToken();
        Todo todo = TodoSteps.generateRandumTodo();
        String todoID = TodoSteps.getTodoID(todo, token);
        Response response = TodoApi.getTodo(token, todoID);
        Todo returnedTodo = response.body().as(Todo.class);

        assertThat(response.statusCode(),equalTo(200));
        assertThat(returnedTodo.getItem(),is(equalTo(todo.getItem())));
        assertThat(returnedTodo.getIsCompleted(),is(equalTo(false)));

    }
    @Story("Should Be Able To Delete Todo By Id")
    @Test (description = "Should Be Able To Delete Todo By Id")
    public void shouldBeAbleToDeleteTodoById() {

        String token = UserSteps.getUserToken();
        Todo todo = TodoSteps.generateRandumTodo();
        String todoID = TodoSteps.getTodoID(todo, token);
        Response response = TodoApi.deleteATodo(token, todoID);
        Todo returnedTodo = response.body().as(Todo.class);

        assertThat(response.statusCode(),equalTo(200));
        assertThat(returnedTodo.getIsCompleted(),is(equalTo(false)));
        assertThat(returnedTodo.getItem(),is(equalTo(todo.getItem())));
    }
}