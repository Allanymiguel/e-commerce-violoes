package br.unitins.ecommerce.listaDesejos.model;

import br.unitins.ecommerce.acessorio.model.Acessorio;
import br.unitins.ecommerce.usuario.model.Usuario;
import br.unitins.ecommerce.violao.model.Violao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ListaDesejos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_violao")
    private Violao violao;

    @ManyToOne
    @JoinColumn(name = "id_acessorio")
    private Acessorio acessorio;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Violao getViolao() { return violao; }
    public void setViolao(Violao violao) { this.violao = violao; }

    public Acessorio getAcessorio() { return acessorio; }
    public void setAcessorio(Acessorio acessorio) { this.acessorio = acessorio; }
}
