package praktikum.utils;

import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;

public class UserGenerator {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";

    public static User createTestUser() {
        String email = "testuser_" + UUID.randomUUID() + "@example.com";
        String password = "password123";
        String name = "Test User";

        String requestBody = String.format("{\"email\": \"%s\", \"password\": \"%s\", \"name\": \"%s\"}",
                email, password, name);

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(BASE_URL + "/auth/register")
                .then()
                .extract().response();

        if (response.statusCode() == 200) {
            String accessToken = response.path("accessToken");
            return new User(email, password, name, accessToken);
        } else {
            throw new RuntimeException("Не удалось создать тестового пользователя: " + response.asString());
        }
    }

    public static void deleteUser(String accessToken) {
        if (accessToken != null) {
            given()
                    .header("Authorization", accessToken)
                    .when()
                    .delete(BASE_URL + "/auth/user")
                    .then()
                    .statusCode(202);
        }
    }

    public static class User {
        public final String email;
        public final String password;
        public final String name;
        public final String accessToken;

        public User(String email, String password, String name, String accessToken) {
            this.email = email;
            this.password = password;
            this.name = name;
            this.accessToken = accessToken;
        }
    }
}