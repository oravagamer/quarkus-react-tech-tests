package quarkus.react.tech.tests;

import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import quarkus.react.tech.tests.gallery.pictures.PicturesService;

import java.io.File;
import java.util.UUID;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class PictureResourceTest {

    @Inject
    PicturesService picturesService;

    private final File PICTURE = new File("./../../../resources/test/default-gallery-settings.jpg");

    @BeforeAll
    public static void setup() {
        PicturesService picturesServiceMock = Mockito.mock(PicturesService.class);
        Mockito
                .when(picturesServiceMock.downloadPicture(6L))
                .thenReturn(new FileDTO(
                        UUID.randomUUID().toString().getBytes(),
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString()
                ));
        QuarkusMock.installMockForType(picturesServiceMock, PicturesService.class);
    }

    @Test
    public void testUploadPictureEndpoint() {
        given()
                .when()
                .multiPart("files", PICTURE)
                .multiPart("descriptions", "test")
                .post("/picture")
                .then()
                .statusCode(200);

        given()
                .when()
                .multiPart("files", PICTURE)
                .post("/picture")
                .then()
                .statusCode(200);
    }


    @Test
    public void testDownloadPictureEndpoint() {

        given()
                .when()
                .get("/picture/1")
                .then()
                .statusCode(404);
        given()
                .when()
                .get("/picture/6")
                .then()
                .statusCode(200);
    }

    @Test
    public void testChangeDescriptionEndpoint() {
        given()
                .when()
                .put("/picture/1/pic-desc")
                .then()
                .statusCode(204);
    }

    @Test
    public void testDeletePictureEndpoint() {
        given()
                .when()
                .delete("/picture/1")
                .then()
                .statusCode(204);
    }
}
