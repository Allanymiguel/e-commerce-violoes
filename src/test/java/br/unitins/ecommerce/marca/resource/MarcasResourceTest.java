package br.unitins.ecommerce.marca.resource;

import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import br.unitins.ecommerce.marca.dto.MarcasRequestDTO;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class MarcasResourceTest {

    @Test
    public void testCadastrarMarca() {
        MarcasRequestDTO dto = new MarcasRequestDTO("Yamaha", "Japão", "www.yamaha.com");
        
        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when().post("/marcas/cadastrar")
          .then()
             .statusCode(200); // RESTEasy Reactive defaults to 200 when returning object
    }

    @Test
    public void testListarMarcas() {
        given()
          .when().get("/marcas/listar")
          .then()
             .statusCode(200);
    }

    @Test
    public void testListarMarcasPorId() {
        given()
          .when().get("/marcas/listar/999")
          .then()
             // Depending on service layer exception mapping, could be 404/500/204
             // Using 500/404 based on standard NotFound exceptions 
             .statusCode(500); 
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
          .when().put("/marcas/atualizar/999")
          .then()
             // returns void so 204 No Content on success, or 500/404 if not found
             .statusCode(500); 
    }

    @Test
    public void testDeletarMarca() {
        given()
          .when().delete("/marcas/deletar/999")
          .then()
             .statusCode(500); // Assuming 500 or 404 when ID not found
    }
}
