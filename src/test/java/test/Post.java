package test;


import io.restassured.response.Response;

import model.Book;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import util.BaseAPITest;


import static io.restassured.RestAssured.given;
import static test.Methods.*;

public class Post extends BaseAPITest {


    @Test
    public void requireFieldAsTitle() {


        Response res = given()
                .param("author", "Jane Archer")
                .contentType("application/json")
                .when()
                .request("GET", "/api/books/")
                .then()
                .statusCode(400)
                .extract()
                .response();

        System.out.println(res.asString());
        Assert.assertEquals(true, res.asString().contains("Field 'title' is required"));

    }

    @Test
    public void requireFieldAsAuthor() {


        Response res = given()
                .param("title", "Reliability of late night deployments")
                .contentType("application/json")
                .when()
                .request("GET", "/api/books/")
                .then()
                .statusCode(400)
                .extract()
                .response();

        System.out.println(res.asString());
        Assert.assertEquals(true, res.asString().contains("Field 'author' is required"));

    }

    @Test
    public void requireField() {


        Response res = given()
                .param("title", "Reliability of late night deployments")
                .param("author", "Jane Archer")
                .contentType("application/json")
                .when()
                .request("GET", "/api/books/")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(res.asString());
        Assert.assertEquals(true, res.asString().contains("id"));

    }


    @Test
    public void verifyEmptyStore() {

        Response res = (Response) given()
                .when()
                .request("GET", "/api/books/")
                .then()
                .statusCode(200)
                .body("isEmpty()", Matchers.is(true));


        String responseString = res.asString();
        System.out.println(responseString);


    }

    @Test
    public void noSendWithPut() {


        Response res = (Response) given()
                .contentType("application/json")
                .when()
                .request("PUT", "/api/books/")
                .then()
                .statusCode(400);


        System.out.println(res.asString());


    }

    @Test
    public void getwithPutId() {


        Book book = addBook();


        Response res = (Response) given()
                .contentType("application/json")
                .when()
                .request("GET", "/api/books/")
                .then()
                .statusCode(200);


        System.out.println(res.asString());


    }
}



