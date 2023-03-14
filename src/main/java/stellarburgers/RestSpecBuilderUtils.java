package stellarburgers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

public class RestSpecBuilderUtils {

    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    private RestSpecBuilderUtils() {
    }

    public static RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder().setContentType(JSON).setBaseUri(URL).build();
    }
}
