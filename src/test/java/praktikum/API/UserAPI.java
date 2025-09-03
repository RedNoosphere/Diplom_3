package praktikum.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import praktikum.model.User;

import static io.restassured.RestAssured.given;

public class UserAPI {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";

    @Step("Создание тестового пользователя через API")
    public static User createTestUser() {
        User user = new User();
        user.setEmail("testuser_" + java.util.UUID.randomUUID() + "@example.com");
        user.setPassword("password123");
        user.setName("Test User");

        Response response = given()
                .contentType("application/json")
                .body(user) // ✅ Сериализация автоматически!
                .when()
                .post(BASE_URL + "/auth/register")
                .then()
                .extract().response();

        if (response.statusCode() == 200) {
            String accessToken = response.path("accessToken");
            user.setAccessToken(accessToken);
            return user;
        } else {
            throw new RuntimeException("Не удалось создать тестового пользователя: " + response.asString());
        }
    }

    @Step("Удаление пользователя через API")
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
}