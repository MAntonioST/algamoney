package com.marcot.algamoneyapi.repository.lancamento;

import com.marcot.algamoneyapi.model.Lancamento;
import com.marcot.algamoneyapi.model.Lancamento_;
import com.marcot.algamoneyapi.repository.filter.LancamentoFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LancamentoRepositoryQueryImpl implements  LancamentoRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);
        PageImpl<Lancamento> query1 = getLancamentos(lancamentoFilter, pageable, builder, criteria, root);
        if (Objects.nonNull(query1))
            return query1;
        TypedQuery<Lancamento> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, count());

    }

    private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
                                       Root<Lancamento> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(lancamentoFilter.getDescricao())) {
            predicates.add(builder.like(
                    builder.lower(root.get(Lancamento_.descricao)), "%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
        }

        if (Objects.nonNull(lancamentoFilter.getDataVencimentoDe())) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento), lancamentoFilter.getDataVencimentoDe()));
        }

        if (Objects.nonNull(lancamentoFilter.getDataVencimentoAte())) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento), lancamentoFilter.getDataVencimentoAte()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private PageImpl<Lancamento> getLancamentos(LancamentoFilter lancamentoFilter, Pageable pageable, CriteriaBuilder builder, CriteriaQuery<Lancamento> criteria, Root<Lancamento> root) {
        if(Objects.nonNull(lancamentoFilter.getDescricao())
                || Objects.nonNull(lancamentoFilter.getDataVencimentoAte())
                || Objects.nonNull(lancamentoFilter.getDataVencimentoDe())){
            Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
            criteria.where(predicates);
            TypedQuery<Lancamento> query = manager.createQuery(criteria);
            adicionarRestricoesDePaginacao(query, pageable);
            return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
        }
        return null;
    }
    private void adicionarRestricoesDePaginacao(TypedQuery<Lancamento> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long total(LancamentoFilter lancamentoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    public long count() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

}
