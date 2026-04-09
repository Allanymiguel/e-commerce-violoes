package br.unitins.ecommerce.acessorio.service;

import java.util.List;

import br.unitins.ecommerce.acessorio.model.Acessorio;

public interface AcessorioService {

    List<Acessorio> findAll();

    Acessorio findById(Long id);

    List<Acessorio> findByNome(String nome);

    Acessorio create(Acessorio acessorio);

    void update(Long id, Acessorio acessorio);

    void delete(Long id);
}