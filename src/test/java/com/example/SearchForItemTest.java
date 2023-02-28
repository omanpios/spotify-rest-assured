package com.example;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import api.SearchForItem;
import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.module.jsv.JsonSchemaValidator;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;

public class SearchForItemTest {

    SoftAssertions softly = new SoftAssertions();

    @BeforeAll
    static void log() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
    }

    @Test
    public void verifyThatASuccessfulRequestReturnsA200StatusCode() {
        String token = "BQDJPeiYtHr5qFGIRC9PYF1_TiXcQpJmQeCXvBTlvgpcUpE9soN3xivSqTAej1dU_Sg48ZXXLKhGISG6aVnOouWon0XaLy1XLP1dOIRRh_oU8lP9GyC6aUreZrYEAIzgdbeb9g7k-7XmviqLx-cKx3WHnrtChb2HjMoSYHmfCAJFdn5_8Q";
        SearchForItem searchForItem = new SearchForItem(token, "remaster%20track:Doxy%20artist:Miles%20Davis", "track",
                "2");
        softly.assertThat(searchForItem.response().statusCode()).as("Status code").isEqualTo(200);
        softly.assertAll();
    }

    @Test
    public void verifyThatASuccessfulResponseMatcheJsonSchema() {
        String token = "BQDJPeiYtHr5qFGIRC9PYF1_TiXcQpJmQeCXvBTlvgpcUpE9soN3xivSqTAej1dU_Sg48ZXXLKhGISG6aVnOouWon0XaLy1XLP1dOIRRh_oU8lP9GyC6aUreZrYEAIzgdbeb9g7k-7XmviqLx-cKx3WHnrtChb2HjMoSYHmfCAJFdn5_8Q";
        SearchForItem searchForItem = new SearchForItem(token, "remaster%20track:Doxy%20artist:Miles%20Davis", "track",
                "2");
        assertThat(searchForItem.response().getBody().asString(), JsonSchemaValidator
                .matchesJsonSchema(new File("src/main/java/schema/SearchForItemResponseSchema.json")));
    }
}
