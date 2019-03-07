package br.andrade.vpd.contas.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.andrade.vpd.contas.model.ContaPadrao;

@Transactional
@Repository
public interface ContaPadraoRepository extends PagingAndSortingRepository<ContaPadrao, Long> {
	
	@Query("select t from ContaPadrao t where ((:descricao = '') or lower(t.descricao) like lower(concat('%', :descricao,'%')) )")
	Page<ContaPadrao> listByPage(Pageable pageable, @Param("descricao") String descricao);

}
