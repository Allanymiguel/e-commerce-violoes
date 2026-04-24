package br.unitins.ecommerce.marca.resource;

import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import br.unitins.ecommerce.marca.dto.MarcasRequestDTO;
import br.unitins.ecommerce.marca.service.MarcasService;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class MarcasResourceTest {

  @InjectMock
  MarcasService service;

    @Test
    public void testCadastrarMarca() {
        MarcasRequestDTO dto = new MarcasRequestDTO("Yamaha", "Japão", "www.yamaha.com");
        
        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when().post("/marcas/cadastrar").then().statusCode(201); // RESTEasy Reactive defaults to 201 when returning object
    }

    @Test
    public void testListarMarcas() {
        given()
          .when().get("/marcas/listar").then().statusCode(200);
    }

    @Test
    public void testListarMarcasPorId() {
        given()
          .when().get("/marcas/listar/1")
          .then()
             // Depending on service layer exception mapping, could be 404/500/204
             // Using 500/404 based on standard NotFound exceptions 
             .statusCode(200); 
    }
    @Test
    public void testListarMarcasPorIdInvalido() {
        given()
          .when().get("/marcas/listar/999")
          .then()
             // Depending on service layer exception mapping, could be 404/500/204
             // Using 500/404 based on standard NotFound exceptions 
             .statusCode(404); 
    }

    @Test
    public void testListarMarcasPorNome() {
        given()
          .when().get("/marcas/listar/nome/Yamaha")
          .then()
             .statusCode(200);
    }

    @Test
    public void testListarMarcasPorPais() {
        given()
          .when().get("/marcas/listar/pais/Japao")
          .then()
             .statusCode(200);
    }

    @Test
    public void testAtualizarMarca() {
        MarcasRequestDTO dto = new MarcasRequestDTO("Tagima", "Brasil", "www.tagima.com.br");
        
        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when().put("/marcas/atualizar/1")
          .then()
             // returns void so 204 No Content on success, or 500/404 if not found
             .statusCode(204); 
    }

    @Test
    public void testDeletarMarcaInvalido() {
        given()
          .when().delete("/marcas/deletar/999")
          .then()
             .statusCode(404); // Assuming 500 or 404 when ID not found
    }
    @Test
    public void testDeletarMarcaValido() {
        given()
          .when().delete("/marcas/deletar/1")
          .then()
             .statusCode(204); // Assuming 500 or 404 when ID not found
    }
}
