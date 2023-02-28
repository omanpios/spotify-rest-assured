package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class SearchForItem {

    Response response;
    private final String baseUri = "https://api.spotify.com/v1/";

    public SearchForItem(String token, String q, String type, String limit) {
        RequestSpecification request = given()
                .baseUri(baseUri)
                .auth().oauth2(token)
                .queryParams("q", q, "type", type, "limit", limit);

        response = request.when()
                .get("search")
                .then()
                .extract()
                .response();
    }

    public Response response() {
        return response;
    }
}
