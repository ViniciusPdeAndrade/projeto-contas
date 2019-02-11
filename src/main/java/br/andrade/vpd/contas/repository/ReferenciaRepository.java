package br.andrade.vpd.contas.repository;


import javax.transaction.Transactional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.andrade.vpd.contas.model.Referencia;

@Transactional
@Repository
public interface ReferenciaRepository extends PagingAndSortingRepository<Referencia, Long>{

	@Query("select t from referencia t where ((:descricao = '') or lower(t.descricao) like lower(concat('%', : descricao. '%')) )")
	Page<Referencia> listByPage(Pageable pageable, @Param("descricao") String descricao);

}
