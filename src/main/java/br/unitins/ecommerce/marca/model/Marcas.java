package br.unitins.ecommerce.marca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import br.unitins.ecommerce.violao.model.Violao;

@Entity
public class Marcas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "pais_origem")
    private String paisOrigem;

    @Column(name = "url_website")
    private String website;

    @OneToMany(mappedBy = "marca")
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

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Violao> getVioloes() {
        return violoes;
    }

    public void setVioloes(List<Violao> violoes) {
        this.violoes = violoes;
    }
}
