package com.marcot.algamoneyapi.service;

import com.marcot.algamoneyapi.model.Lancamento;
import com.marcot.algamoneyapi.model.Pessoa;
import com.marcot.algamoneyapi.repository.LancamentoRepository;
import com.marcot.algamoneyapi.repository.PessoaRepository;
import com.marcot.algamoneyapi.repository.filter.LancamentoFilter;
import com.marcot.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        validarPessoa(lancamento);
        return lancamentoRepository.save(lancamento);
    }

    public Page<Lancamento> filtroDeLancamento(LancamentoFilter lancamentoFilter, Pageable pageable){
        return lancamentoRepository.filtrar(lancamentoFilter, pageable);
    }


    public Lancamento atualizar(Long codigo, Lancamento lancamento) {
        Lancamento lancamentoSalvo = buscarLancamentoExistente(codigo);
        if (!lancamento.getPessoa().equals(lancamentoSalvo.getPessoa())) {
            validarPessoa(lancamento);
        }

        BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");

        return lancamentoRepository.save(lancamentoSalvo);
    }

    private void validarPessoa(Lancamento lancamento) {
        Optional<Pessoa> pessoa = null;
        if (lancamento.getPessoa().getCodigo() != null) {
            pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
        }

        if (pessoa.isEmpty() || pessoa.get().isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }
    }

    private Lancamento buscarLancamentoExistente(Long codigo) {
/* 		Optional<Lancamento> lancamentoSalvo = lancamentoRepository.findById(codigo);
		if (lancamentoSalvo.isEmpty()) {
			throw new IllegalArgumentException();
		} */
        return lancamentoRepository.findById(codigo).orElseThrow(() -> new IllegalArgumentException());
    }

}
