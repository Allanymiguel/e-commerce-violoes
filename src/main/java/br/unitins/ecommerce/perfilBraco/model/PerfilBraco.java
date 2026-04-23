package br.unitins.ecommerce.perfilBraco.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import br.unitins.ecommerce.violao.model.Violao;

@Entity
public class PerfilBraco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    private String nome;

    @NotBlank
    private String formato;

    @NotNull
    @Positive
    private Double espessura;

    @OneToMany(mappedBy = "perfilBraco")
    @JsonIgnore
    private List<Violao> violoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Double getEspessura() {
        return espessura;
    }

    public void setEspessura(Double espessura) {
        this.espessura = espessura;
    }

    public List<Violao> getVioloes() {
        return violoes;
    }

    public void setVioloes(List<Violao> violoes) {
        this.violoes = violoes;
    }
}
