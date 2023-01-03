package com.marcot.algamoneyapi.repository;

import com.marcot.algamoneyapi.model.Lancamento;
import com.marcot.algamoneyapi.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> , LancamentoRepositoryQuery {
}
