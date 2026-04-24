package br.unitins.ecommerce.violao.resource;

import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import br.unitins.ecommerce.violao.dto.ViolaoAcoRequestDTO;
import br.unitins.ecommerce.violao.model.TipoCordasAco;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class ViolaoAcoResourceTest {

    @Test
    public void testGetAll() {
        given()
          .when().get("/violoes/aco/listar")
          .then()
             .statusCode(200);
    }

    @Test
    public void testGetById() {
        given()
          .when().get("/violoes/aco/listar/999")
          .then()
             // Depending on service layer mapping, might be 500 or 404
             .statusCode(400);
    }

    @Test
    public void testCreate() {
        ViolaoAcoRequestDTO dto = new ViolaoAcoRequestDTO(
            "Folk Taylor",
            1200.0,
            2022,
            TipoCordasAco.MEDIA
        );
        
        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when().post("/violoes/aco/cadastrar")
          .then()
             .statusCode(201);
    }

    @Test
    public void testUpdate() {
        ViolaoAcoRequestDTO dto = new ViolaoAcoRequestDTO(
            "Folk Taylor Updated",
            1300.0,
            2023,
            TipoCordasAco.LEVE
        );
        
        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when().put("/violoes/aco/atualizar/999")
          .then()
             // Since it returns NO CONTENT on success, or 500/404 if failed
             .statusCode(400); 
    }

    @Test
    public void testDelete() {
        given()
          .when().delete("/violoes/aco/deletar/999")
          .then()
             .statusCode(400); // 500 or 404 if it fails to find
    }
}
