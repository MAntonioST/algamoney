package com.marcot.algamoneyapi.repository.lancamento;

import com.marcot.algamoneyapi.model.Lancamento;
import com.marcot.algamoneyapi.repository.filter.LancamentoFilter;
import java.util.List;
public interface LancamentoRepositoryQuery {

    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
