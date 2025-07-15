package medi.voli.api.domain.consultas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

    Page<ConsultaEntity> findAllByAtivoTrue(Pageable pageable);
}
