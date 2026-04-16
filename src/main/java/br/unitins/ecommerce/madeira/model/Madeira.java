package br.unitins.ecommerce.madeira.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;
import java.util.List;
import br.unitins.ecommerce.violao.model.Violao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Madeira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private String densidade;

    private String sonoridade;

    @OneToMany(mappedBy = "madeira")
    @JsonIgnore
    private List<Violao> violoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDensidade() {
        return densidade;
    }

    public void setDensidade(String densidade) {
        this.densidade = densidade;
    }

    public String getSonoridade() {
        return sonoridade;
    }

    public void setSonoridade(String sonoridade) {
        this.sonoridade = sonoridade;
    }
    
    public List<Violao> getVioloes() {
    return violoes;
    }

    public void setVioloes(List<Violao> violoes) {
        this.violoes = violoes;
    }
}
