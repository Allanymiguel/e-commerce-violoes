package br.unitins.ecommerce.violao.resource;

import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public abstract class ViolaoResourceTest {

    protected abstract String getEndpoint();
    protected abstract Object getValidRequestDTO();
    protected abstract Object getUpdateVariantDTO();

    @Test
    public void testGetAll() {
        given()
          .when().get(getEndpoint() + "/listar")
          .then()
             .statusCode(200);
    }

    @Test
    public void testGetById() {
        given()
          .when().get(getEndpoint() + "/listar/1")
          .then()
             .statusCode(getExpectedGetByIdStatus());
    }

    @Test
    public void testCreate() {
        given()
          .contentType(ContentType.JSON)
          .body(getValidRequestDTO())
          .when().post(getEndpoint() + "/cadastrar")
          .then()
             .statusCode(201);
    }

    @Test
    public void testUpdate() {
        given()
          .contentType(ContentType.JSON)
          .body(getUpdateVariantDTO())
          .when().put(getEndpoint() + "/atualizar/1")
          .then()
             .statusCode(getExpectedUpdateStatus());
    }

    @Test
    public void testDelete() {
        given()
          .when().delete(getEndpoint() + "/deletar/999")
          .then()
             .statusCode(getExpectedDeleteStatus());
    }

    protected int getExpectedGetByIdStatus() { return 200; }
    protected int getExpectedUpdateStatus() { return 204; }
    protected int getExpectedDeleteStatus() { return 204; }
}
