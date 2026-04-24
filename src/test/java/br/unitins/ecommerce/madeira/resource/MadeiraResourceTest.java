package br.unitins.ecommerce.madeira.resource;

import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import br.unitins.ecommerce.madeira.dto.MadeiraRequestDTO;
import br.unitins.ecommerce.madeira.service.MadeiraService;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class MadeiraResourceTest {

  @InjectMock
  MadeiraService service;

    @Test
    public void testCadastrarMadeiraComSucesso() {
        MadeiraRequestDTO dto = new MadeiraRequestDTO("Mogno", "Alta", "Encorpada");
        
        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when().post("/madeiras/cadastrar")
          .then()
             .statusCode(201);
    }

    @Test
    public void testListarMadeirasComStatus200() {
        given()
          .when().get("/madeiras/listar")
          .then()
             .statusCode(200);
    }

    @Test
    public void testListarMadeiraPorIdInvalido() {
        given()
          .when().get("/madeiras/listar/999")
          .then()
             // Could be 404 or 500 depending on service exception mapper
             .statusCode(400); 
    }

    @Test
    public void testListarMadeirasPorTipo() {
        given()
          .when().get("/madeiras/listar/Mogno")
          .then()
             .statusCode(200);
    }

    @Test
    public void testAtualizarMadeira() {
        MadeiraRequestDTO dto = new MadeiraRequestDTO("Cedro", "Média", "Equilibrada");
        
        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when().put("/madeiras/atualizar/999")
          .then()
             // method is void, it returns HTTP 204 typically on success, or 500/404 if not found
             .statusCode(204); 
    }

    @Test
    public void testDeletarMadeira() {
        given()
          .when().delete("/madeiras/deletar/999")
          .then()
             .statusCode(400); // 500 or 404 if it fails due to lack of item
    }
}
