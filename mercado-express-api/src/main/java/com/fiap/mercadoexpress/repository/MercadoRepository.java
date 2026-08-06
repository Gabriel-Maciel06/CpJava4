package com.fiap.mercadoexpress.repository;

import com.fiap.mercadoexpress.model.Mercado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MercadoRepository extends JpaRepository<Mercado, Long> {

    List<Mercado> findByTipoIgnoreCase(String tipo);

    List<Mercado> findBySetorIgnoreCase(String setor);

    List<Mercado> findByNomeContainingIgnoreCase(String nome);
}
