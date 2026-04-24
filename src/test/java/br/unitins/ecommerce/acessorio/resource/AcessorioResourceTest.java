package br.unitins.ecommerce.acessorio.resource;

import org.junit.jupiter.api.Test;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import br.unitins.ecommerce.acessorio.dto.AcessorioRequestDTO;
import br.unitins.ecommerce.acessorio.service.AcessorioService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class AcessorioResourceTest {

    @InjectMock
    AcessorioService service;

    @Test
    public void testCadastrarAcessorioSucesso() {
        AcessorioRequestDTO dto = new AcessorioRequestDTO("Palheta", "Palheta média 0.71mm", 100, 2.5);
        
        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when().post("/acessorios/cadastrar")
          .then()
             .statusCode(201);
    }

    @Test
    public void testCadastrarAcessorioInvalido() {
        // Enviando dados que violam o Bean Validation (@NotBlank, @Min, etc)
        AcessorioRequestDTO dto = new AcessorioRequestDTO("", "", -1, -1.0);
        
        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when().post("/acessorios/cadastrar")
          .then()
             .statusCode(400) // Aqui valida se sua RFC 7807 entrou em ação
             .body("title", is("Bad Request")) 
             .body("status", is(400))
             .body("violations", notNullValue());
    }

    @Test
    public void testListarAcessorios() {
        given()
          .when().get("/acessorios/listar")
          .then()
          .statusCode(200);
    }

    @Test
    public void testDeletarAcessorio() {
        // Como estamos usando Mock, o ID 999 não precisa existir de verdade no banco
        given()
          .when().delete("/acessorios/deletar/999")
          .then()
          .statusCode(204);
    }
}