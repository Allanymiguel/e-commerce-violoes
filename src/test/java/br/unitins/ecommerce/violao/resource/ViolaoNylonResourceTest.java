package br.unitins.ecommerce.violao.resource;

import io.quarkus.test.junit.QuarkusTest;
import br.unitins.ecommerce.violao.dto.ViolaoNylonRequestDTO;
import br.unitins.ecommerce.violao.model.TipoCordasNylon;

@QuarkusTest
public class ViolaoNylonResourceTest extends ViolaoResourceTest {

    @Override
    protected String getEndpoint() {
        return "/violoes/nylon";
    }

    @Override
    protected Object getValidRequestDTO() {
        return new ViolaoNylonRequestDTO(
            "Acustico Yamaha",
            1500.0,
            2021,
            TipoCordasNylon.MEDIA
        );
    }

    @Override
    protected Object getUpdateVariantDTO() {
        return new ViolaoNylonRequestDTO(
            "Acustico Yamaha Updated",
            1600.0,
            2021,
            TipoCordasNylon.ALTA
        );
    }

    @Override
    protected int getExpectedGetByIdStatus() {
        return 404; 
    }
    
    @Override
    protected int getExpectedUpdateStatus() {
        return 404; 
    }
}
