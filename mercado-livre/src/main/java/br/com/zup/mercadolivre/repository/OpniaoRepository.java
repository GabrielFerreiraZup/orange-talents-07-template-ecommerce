package br.com.zup.mercadolivre.repository;

import br.com.zup.mercadolivre.modelo.Opniao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpniaoRepository extends JpaRepository<Opniao,Long> {
}
