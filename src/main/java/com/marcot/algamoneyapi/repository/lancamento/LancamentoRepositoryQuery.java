package com.marcot.algamoneyapi.repository.lancamento;

import com.marcot.algamoneyapi.model.Lancamento;
import com.marcot.algamoneyapi.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface LancamentoRepositoryQuery {

    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
