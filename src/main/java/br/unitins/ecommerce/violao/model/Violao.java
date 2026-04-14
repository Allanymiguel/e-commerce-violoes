package br.unitins.ecommerce.violao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;

import br.unitins.ecommerce.acessorio.model.Acessorio;
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

    /*
    Pensando na informação não aspecto físico: madeira não é uma composição 
    por existir à parte de Violão como nos filtros de pesquisa. 
    
    O violão inteiro é feito com apenas um tipo de madeira.
     */
    @ManyToOne
    private Madeira madeira;
    
    // Agregação: Acessórios não são vendidos apenas com violões, além de ser uma opção de filtro de pesquisa.
    @ManyToMany
    @JoinTable(
        name = "violao_acessorio",
        joinColumns = @JoinColumn(name = "id_violao"),
        inverseJoinColumns = @JoinColumn(name = "id_acessorio")
    )
    private List<Acessorio> acessorios;

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

    public List<Acessorio> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(List<Acessorio> acessorios) {
        this.acessorios = acessorios;
    }
}