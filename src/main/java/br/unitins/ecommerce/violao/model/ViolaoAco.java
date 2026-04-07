package br.unitins.ecommerce.violao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class ViolaoAco extends Violao {
    
    @Enumerated(EnumType.STRING)
    private TipoCordasAco tipoCordasAco;

    public TipoCordasAco getTipoCordasAco() {
        return tipoCordasAco;
    }

    public void setTipoCordasAco(TipoCordasAco tipoCordasAco) {
        this.tipoCordasAco = tipoCordasAco;
    }
}