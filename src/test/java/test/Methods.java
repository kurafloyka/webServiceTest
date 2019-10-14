package test;

import io.restassured.response.Response;
import model.Book;
import util.BaseAPITest;


import java.security.SecureRandom;

import static io.restassured.RestAssured.given;


public class Methods extends BaseAPITest {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();


    public static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            // 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            // debug
            //System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);

            sb.append(rndChar);

        }

        return sb.toString();

    }


    public static int usingMathClass() {
        double randomDouble = Math.random();
        randomDouble = randomDouble * 10000 + 1;
        int randomInt = (int) randomDouble;
        System.out.println(randomInt);
        return randomInt;
    }

    public static Book addBook() {

        int id = usingMathClass();
        String author = generateRandomString(10);
        String title = generateRandomString(10);


        Response res = given()
                .param("author", "Jane Archer")
                .param("title", "title2")
                .param("id", id)
                .contentType("application/json")
                .when()
                .request("PUT", "/api/books/")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(res.asString());

        Book book = new Book(id, author, title);

        return book;
    }

}
