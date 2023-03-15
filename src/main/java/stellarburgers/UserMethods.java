package stellarburgers;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static stellarburgers.RestSpecBuilderUtils.getBaseSpec;

public class UserMethods {

    private final static String USERS_PATH = "api/auth/";

    @Step("Create user")
    public ValidatableResponse createUser(User user) {
        return given().spec(getBaseSpec())
                .body(user)
                .when().post(USERS_PATH + "register")
                .then().log().all();
    }

    @Step("Delete user")
    public ValidatableResponse deleteUser(String accessToken) {
        return given().spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .when().delete(USERS_PATH + "user")
                .then().log().all();
    }

    @Step("Login user")
    public ValidatableResponse loginUser(UserCredentials userCredentials) {
        return given().spec(getBaseSpec())
                .body(userCredentials)
                .when().post(USERS_PATH + "login")
                .then().log().all();
    }

}