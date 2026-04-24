package br.unitins.ecommerce.acessorio.resource;

import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import br.unitins.ecommerce.acessorio.dto.AcessorioRequestDTO;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AcessorioResourceTest {

    @Test
    public void testCadastrarAcessorio() {
        AcessorioRequestDTO dto = new AcessorioRequestDTO("Palheta", "Palheta média 0.71mm", 100, 2.5);
        
        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when().post("/acessorios/cadastrar")
          .then()
             .statusCode(201); // Created
    }

    @Test
    public void testListarAcessorios() {
        given()
          .when().get("/acessorios/listar")
          .then()
             .statusCode(200);
    }

    @Test
    public void testListarAcessorioPorId() {
        // Typically returns 404 or 500 if item not found, 200 if found. We test the endpoint gets hit.
        given()
          .when().get("/acessorios/999")
          .then()
             .statusCode(404); // assuming not found returns 404/500, but using 404/500 depends on exception mapping
    }

    @Test
    public void testListarAcessoriosPorNome() {
        given()
          .when().get("/acessorios/listar/Palheta")
          .then()
             .statusCode(200); // OK
    }

    @Test
    public void testAtualizarAcessorio() {
        AcessorioRequestDTO dto = new AcessorioRequestDTO("Cabo P10", "Cabo de 3 metros", 50, 35.0);
        
        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when().put("/acessorios/atualizar/999")
          .then()
             .statusCode(404); // Or appropriate error/NoContent depending on your service layer
    }

    @Test
    public void testDeletarAcessorio() {
        given()
          .when().delete("/acessorios/deletar/999")
          .then()
             .statusCode(204); // Or 404/500 depending on service exception for not found
    }
}
