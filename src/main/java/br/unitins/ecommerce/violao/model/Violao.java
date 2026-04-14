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
import br.unitins.ecommerce.marca.model.Marcas;
import br.unitins.ecommerce.perfilBraco.model.PerfilBraco;

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
    
    @ManyToMany
    @JoinTable(
        name = "violao_acessorio",
        joinColumns = @JoinColumn(name = "id_violao"),
        inverseJoinColumns = @JoinColumn(name = "id_acessorio")
    )
    private List<Acessorio> acessorios;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marcas marca;

    @ManyToOne
    @JoinColumn(name = "id_perfil_braco")
    private PerfilBraco perfilBraco;

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

    public Marcas getMarca() {
        return marca;
    }

    public void setMarca(Marcas marca) {
        this.marca = marca;
    }

    public PerfilBraco getPerfilBraco() {
        return perfilBraco;
    }

    public void setPerfilBraco(PerfilBraco perfilBraco) {
        this.perfilBraco = perfilBraco;
    }
}