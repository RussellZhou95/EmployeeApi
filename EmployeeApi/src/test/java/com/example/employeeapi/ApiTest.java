package com.example.employeeapi;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApiTest {

//    @Test
//    void getEmployee(){
//        String endpoint="http://localhost:8080/employeeApi";
//        ValidatableResponse response = given().queryParam("id", 1).when().get(endpoint).then().assertThat().statusCode(200).
//                body("id",equalTo(1)).body("name",equalTo("Russell")).body("age",equalTo(27)).body("title",equalTo("api developer"));
////        response.log().body();
//    }

//    @Test
//    void getAllEmployees(){
//        String endpoint="http://localhost:8080/employeeApi/employee";
//        //can verify the headers
//        ValidatableResponse response = given().when().get(endpoint).then().log().headers().header("Content-Type","application/json");
//        response.log().body();
//    }

//    @Test
//    void createNewEmployee(){
//        String endpoint="http://localhost:8080/employeeApi";
//        String body="""
//                    {
//                                "id": 4,
//                                "name": "Kidd",
//                                "age": 47,
//                                "title": "coach"
//                    }
//                """;
//        ValidatableResponse response = given().body(body).when().accept(ContentType.TEXT).post(endpoint).then();
//        response.log().body();
//    }
//
//    @Test
//    void updateEmployee(){
//        String endpoint="http://localhost:8080/employeeApi";
//        String body="""
//                    {
//                                "id": 3,
//                                "name": "Kidd",
//                                "age": 47,
//                                "title": "coach"
//                    }
//                """;
//
//        ValidatableResponse response = given().body(body).when().put(endpoint).then();
//        response.log().body();
//    }
//
//    @Test
//    void deleteEmployee(){
//        String endpoint="http://localhost:8080/employeeApi/3";
//
//        ValidatableResponse response = given().when().delete(endpoint).then();
//        response.log().body();
//    }

//    @Test
//    void createSerializedEmployee(){
//        String endpoint="http://localhost:8080/employeeApi";
//        Employee employee=new Employee("James",46,"cleaner");
//
//        ValidatableResponse response = given().body(employee).when().post(endpoint).then();
//        response.log().body();
//    }

//    @Test
//    void getDeserializedEmployee(){
//        String endpoint="http://localhost:8080/employeeApi";
//        Employee expectedEmployee=new Employee(1l,"Russell",27,"api developer");
//        //Deserialize json to object
//        Employee actualEmployee = given().queryParam("id", 1).when().get(endpoint).as(Employee.class);
//
//
//        assertThat(expectedEmployee, samePropertyValuesAs(actualEmployee));
//
//    }
}
