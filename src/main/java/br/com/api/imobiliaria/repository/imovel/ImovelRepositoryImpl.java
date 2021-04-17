package br.com.api.imobiliaria.repository.imovel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.com.api.imobiliaria.model.Imovel;
import br.com.api.imobiliaria.repository.filter.ImovelFilter;



public class ImovelRepositoryImpl implements ImovelRepositoryQuery{
	@PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Imovel> filtrar(ImovelFilter filtro, Pageable pageable) {

        // Usamos o CriteriaBuilder(cb) para criar a CriteriaQueyr (cQuery)
        // com a tipagem do tipo a ser selecionado (Produto)
        CriteriaBuilder cBuilder = manager.getCriteriaBuilder();


        // 1. Select p From Produto p
        CriteriaQuery<Imovel> cQuery = cBuilder.createQuery(Imovel.class );

        // 2. clausula from e joins
        Root<Imovel> produtoRoot = cQuery.from(Imovel.class );

        // 3. adiciona as restrições (os predicados) que serão passadas na clausula where
        Predicate[] restricoes = this.criaRestricoes(filtro, cBuilder, produtoRoot  );


        // 4. monta a consulta com as restrições
        cQuery.select(produtoRoot)
              .where(restricoes )
              .orderBy( cBuilder.desc(produtoRoot.get("valorDeAluguelSugerido")) );

        // 5. cria e executa a consula
        TypedQuery<Imovel> query = manager.createQuery(cQuery);
        adicionaRestricoesDePaginacao(query, pageable);

        return new PageImpl<Imovel>(query.getResultList(), pageable, total(filtro) );
    }


    private Predicate[] criaRestricoes(ImovelFilter filtro, CriteriaBuilder cBuilder, Root<Imovel> produtoRoot) {

        List<Predicate> predicates = new ArrayList<>();


        if ( !StringUtils.isEmpty( filtro.getTipoImovel()) ) {
            // where nome like %Computador%
            predicates.add(cBuilder.like(cBuilder.lower(produtoRoot.get("tipoImovel")), "%" + filtro.getTipoImovel().toLowerCase() + "%" ) );

        }

        if( Objects.nonNull( filtro.getValorDeAluguelSugerido()  ) ) {
            predicates.add( cBuilder.le( produtoRoot.get("precoAtual"), filtro.getValorDeAluguelSugerido() ));
        }



        return predicates.toArray(new Predicate[ predicates.size() ] );
    }



    private void adicionaRestricoesDePaginacao(TypedQuery<Imovel> query, Pageable pageable) {
        Integer paginaAtual = pageable.getPageNumber();
        Integer totalObjetosPorPagina = pageable.getPageSize();
        Integer primeiroObjetoDaPagina = paginaAtual * totalObjetosPorPagina;

        // 0 a n-1, n - (2n -1), ...
        query.setFirstResult(primeiroObjetoDaPagina );
        query.setMaxResults(totalObjetosPorPagina );

    }

    private Long total(ImovelFilter filtro) {
        CriteriaBuilder cBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> cQuery = cBuilder.createQuery(Long.class);

        Root<Imovel> rootProduto = cQuery.from(Imovel.class);

        Predicate[] predicates = criaRestricoes(filtro, cBuilder, rootProduto);

        cQuery.where(predicates );
        cQuery.select( cBuilder.count(rootProduto) );

        return manager.createQuery(cQuery).getSingleResult();
    }

}
