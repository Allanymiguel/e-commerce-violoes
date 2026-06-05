package br.unitins.ecommerce.produto.resource;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.unitins.ecommerce.acessorio.model.Acessorio;
import br.unitins.ecommerce.acessorio.repository.AcessorioRepository;
import br.unitins.ecommerce.violao.model.Violao;
import br.unitins.ecommerce.violao.repository.ViolaoRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class ProdutoResourceTest {

    @InjectMock
    ViolaoRepository violaoRepository;

    @InjectMock
    AcessorioRepository acessorioRepository;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setup() {
        PanacheQuery<Violao> violaoQuery = Mockito.mock(PanacheQuery.class);
        Mockito.when(violaoQuery.stream()).thenReturn(Stream.empty());
        Mockito.when(violaoRepository.findAll()).thenReturn(violaoQuery);

        PanacheQuery<Acessorio> acessorioQuery = Mockito.mock(PanacheQuery.class);
        Mockito.when(acessorioQuery.stream()).thenReturn(Stream.empty());
        Mockito.when(acessorioRepository.findAll()).thenReturn(acessorioQuery);
    }

    @Test
    public void testListar() {
        given()
          .when().get("/produtos")
          .then()
             .statusCode(200);
    }

    @Test
    public void testBuscarPorIdViolaoNotFound() {
        Mockito.when(violaoRepository.findById(999L)).thenReturn(null);

        given()
          .when().get("/produtos/VIOLAO/999")
          .then()
             .statusCode(404);
    }

    @Test
    public void testBuscarPorIdAcessorioNotFound() {
        Mockito.when(acessorioRepository.findById(999L)).thenReturn(null);

        given()
          .when().get("/produtos/ACESSORIO/999")
          .then()
             .statusCode(404);
    }

    @Test
    public void testBuscarPorIdCategoriaInvalida() {
        given()
          .when().get("/produtos/MOCHILA/1")
          .then()
             .statusCode(404);
    }
}
