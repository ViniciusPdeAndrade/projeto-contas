package br.andrade.vpd.contas.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.andrade.vpd.contas.model.Rendimento;

@Transactional
@Repository
public interface RendimentoRepository extends PagingAndSortingRepository<Rendimento, Long> {

	@Query("select t from Rendimento t where t.referencia.id = :referencia and ((:descricao = '') or lower(t.descricao) like lower(concat('%', :descricao , '%')) )")
	Page<Rendimento> listByPage(Pageable pageable, @Param("descricao") String descricao, @Param("referencia")Long referencia);
}
