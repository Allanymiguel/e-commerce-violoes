package br.unitins.ecommerce.acessorio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import br.unitins.ecommerce.violao.model.Violao;

@Entity
public class Acessorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @Column(name = "quantidade_estoque")
    private Integer quantidadeEstoque;

    @Column(name = "preco_unitario")
    private Double precoUnitario;

    @ManyToMany(mappedBy = "acessorios")
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public List<Violao> getVioloes() {
        return violoes;
    }

    public void setVioloes(List<Violao> violoes) {
        this.violoes = violoes;
    }
}