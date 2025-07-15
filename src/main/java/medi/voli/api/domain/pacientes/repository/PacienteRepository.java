package medi.voli.api.domain.pacientes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
    Page<PacienteEntity> findAllByAtivoTrue(Pageable paginacao);
}
