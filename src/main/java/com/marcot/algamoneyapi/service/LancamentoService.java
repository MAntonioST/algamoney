package com.marcot.algamoneyapi.service;

import com.marcot.algamoneyapi.model.Lancamento;
import com.marcot.algamoneyapi.model.Pessoa;
import com.marcot.algamoneyapi.repository.LancamentoRepository;
import com.marcot.algamoneyapi.repository.PessoaRepository;
import com.marcot.algamoneyapi.repository.filter.LancamentoFilter;
import com.marcot.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.List;

@Service
public class LancamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(Lancamento lancamento) {
        Optional<Pessoa> pessoa =  pessoaRepository.findById(lancamento.getPessoa().getCodigo());
              if(pessoa.get().isInativo() || !pessoa.isPresent()){
                  throw new PessoaInexistenteOuInativaException();
              }
         return lancamentoRepository.save(lancamento);
    }

    public List<Lancamento> filtroLancamento(LancamentoFilter lancamentoFilter){
        if(Objects.isNull(lancamentoFilter)){
            return lancamentoRepository.findAll();
        }
        return lancamentoRepository.filtrar(lancamentoFilter);
    }

}
