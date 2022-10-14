package DJ.tinder.testMethods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

public class CreateReadUpdateDelete {

    public static <T> T create(String uri, Class<T> clazz, Object objectToCreate) {
        return RestAssured
                .with()
                .contentType(ContentType.JSON)
                .body(objectToCreate)
                .when()
                .post(uri)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(clazz);
    }

    public static <T> T read(String uri, Class<T> clazz) {
        return RestAssured
                .given()
                .headers("Content-Type", ContentType.JSON)
                .get(uri)
                .as(clazz);
    }

    public static <T> T update(String uri, Class<T> clazz, Object objectToUpdate) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(objectToUpdate)
                .when()
                .put(uri)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(clazz);
    }

    public static void delete(Long id, String uri) {
        RestAssured
                .when()
                .delete(uri + "/" + id)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }



}
