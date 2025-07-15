package medi.voli.api.domain.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuariosRepository extends JpaRepository<UsuariosEntity, Long> {
    UserDetails findByLogin(String login);
}
