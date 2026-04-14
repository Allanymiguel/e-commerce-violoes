package br.unitins.ecommerce.violao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;

import br.unitins.ecommerce.madeira.model.Madeira;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Violao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double precoBase;
    private Integer anoFabricacao;

    @ManyToOne
    private Madeira madeira;

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
    public Double getPrecoBase() {
        return precoBase;
    }
    public void setPrecoBase(Double precoBase) {
        this.precoBase = precoBase;
    }
    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }
    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Madeira getMadeira() {
        return madeira;
    }

    public void setMadeira(Madeira madeira) {
        this.madeira = madeira;
    }
}