package br.com.zup.mercadolivre.repository;


import br.com.zup.mercadolivre.modelo.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaracteristicaRepository extends JpaRepository<Caracteristica,Long> {


}
