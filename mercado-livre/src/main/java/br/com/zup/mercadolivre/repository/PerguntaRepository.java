package br.com.zup.mercadolivre.repository;

import br.com.zup.mercadolivre.modelo.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta,Long> {
}
