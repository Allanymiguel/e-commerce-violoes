package br.unitins.ecommerce.violao.resource;

import io.quarkus.test.junit.QuarkusTest;
import br.unitins.ecommerce.violao.dto.ViolaoAcoRequestDTO;
import br.unitins.ecommerce.violao.model.TipoCordasAco;

@QuarkusTest
public class ViolaoAcoResourceTest extends ViolaoResourceTest {

    @Override
    protected String getEndpoint() {
        return "/violoes/aco";
    }

    @Override
    protected Object getValidRequestDTO() {
        return new ViolaoAcoRequestDTO(
            "Folk Taylor",
            1200.0,
            2022,
            TipoCordasAco.MEDIA
        );
    }

    @Override
    protected Object getUpdateVariantDTO() {
        return new ViolaoAcoRequestDTO(
            "Folk Taylor Updated",
            1300.0,
            2023,
            TipoCordasAco.LEVE
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
