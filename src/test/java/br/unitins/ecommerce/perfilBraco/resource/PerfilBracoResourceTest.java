package br.unitins.ecommerce.perfilBraco.resource;

import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import br.unitins.ecommerce.perfilBraco.dto.PerfilBracoRequestDTO;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class PerfilBracoResourceTest {

    @Test
    public void testCadastrarPerfilBraco() {
        PerfilBracoRequestDTO dto = new PerfilBracoRequestDTO("Fino C", "C Moderno", 21.0);
        
        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when().post("/perfis-braco/cadastrar")
          .then()
             .statusCode(201); // RESTEasy Reactive usually maps returning an object to 200 OK
    }

    @Test
    public void testListarPerfisBraco() {
        given()
          .when().get("/perfis-braco/listar")
          .then()
             .statusCode(200);
    }

    @Test
    public void testListarPerfilBracoPorId() {
        given()
          .when().get("/perfis-braco/listar/999")
          .then()
             // Depending on service layer, could be 404 Not Found or 500 when missing
             .statusCode(400); 
    }

    @Test
    public void testListarPerfisBracoPorNome() {
        given()
          .when().get("/perfis-braco/listar/Fino")
          .then()
             .statusCode(200);
    }

    @Test
    public void testAtualizarPerfilBraco() {
        PerfilBracoRequestDTO dto = new PerfilBracoRequestDTO("D Grosso", "Vintage D", 24.5);
        
        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when().put("/perfis-braco/atualizar/999")
          .then()
             // void return type, maps to 204 or throws exception 500/404 if missing
             .statusCode(400); 
    }

    @Test
    public void testDeletarPerfilBraco() {
        given()
          .when().delete("/perfis-braco/deletar/999")
          .then()
             .statusCode(400); // Exception if missing, so typically 500 or 404
    }
}
