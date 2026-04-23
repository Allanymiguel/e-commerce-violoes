package br.unitins.ecommerce.violao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "violoes_nylon")
public class ViolaoNylon extends Violao {
    
    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoCordasNylon tipoCordasNylon;

    public TipoCordasNylon getTipoCordasNylon() {
        return tipoCordasNylon;
    }

    public void setTipoCordasNylon(TipoCordasNylon tipoCordasNylon) {
        this.tipoCordasNylon = tipoCordasNylon;
    }
}