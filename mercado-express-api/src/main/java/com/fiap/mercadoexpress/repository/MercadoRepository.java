package com.fiap.mercadoexpress.repository;

import com.fiap.mercadoexpress.model.Mercado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MercadoRepository extends JpaRepository<Mercado, Long> {

    List<Mercado> findByTipoIgnoreCase(String tipo);
}
