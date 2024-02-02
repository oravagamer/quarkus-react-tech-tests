package quarkus.react.tech.tests.gallery.pictures;

import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import quarkus.react.tech.tests.FileDTO;

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
                .when(picturesServiceMock.downloadPicture(2L))
                .thenReturn(new FileDTO(
                        UUID.randomUUID().toString().getBytes(),
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString()
                ));
        Mockito
                .when(picturesServiceMock.downloadPicture(3L))
                .thenReturn(null);
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
                .get("/picture/2")
                .then()
                .statusCode(200);
        given()
                .when()
                .get("/picture/3")
                .then()
                .statusCode(404);
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
