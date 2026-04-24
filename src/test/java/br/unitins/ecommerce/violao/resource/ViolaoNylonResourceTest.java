package br.unitins.ecommerce.violao.resource;

import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import br.unitins.ecommerce.violao.dto.ViolaoNylonRequestDTO;
import br.unitins.ecommerce.violao.model.TipoCordasNylon;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class ViolaoNylonResourceTest {

    @Test
    public void testGetAll() {
        given()
          .when().get("/violoes/nylon/listar")
          .then()
             .statusCode(200);
    }

    @Test
    public void testGetById() {
        given()
          .when().get("/violoes/nylon/listar/999")
          .then()
             .statusCode(500);
    }

    @Test
    public void testCreate() {
        ViolaoNylonRequestDTO dto = new ViolaoNylonRequestDTO(
            "Acustico Yamaha",
            1500.0,
            2021,
            TipoCordasNylon.MEDIA
        );
        
        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when().post("/violoes/nylon/cadastrar")
          .then()
             .statusCode(201);
    }

    @Test
    public void testUpdate() {
        ViolaoNylonRequestDTO dto = new ViolaoNylonRequestDTO(
            "Acustico Yamaha Updated",
            1600.0,
            2021,
            TipoCordasNylon.ALTA
        );
        
        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when().put("/violoes/nylon/atualizar/999")
          .then()
             .statusCode(500);
    }

    @Test
    public void testDelete() {
        given()
          .when().delete("/violoes/nylon/deletar/999")
          .then()
             .statusCode(500);
    }
}
