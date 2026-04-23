package br.unitins.ecommerce.violao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "violoes_aco")
public class ViolaoAco extends Violao {
    
    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoCordasAco tipoCordasAco;

    public TipoCordasAco getTipoCordasAco() {
        return tipoCordasAco;
    }

    public void setTipoCordasAco(TipoCordasAco tipoCordasAco) {
        this.tipoCordasAco = tipoCordasAco;
    }
}