package tech.woloszyn.Elotech.domain.usecase.repository;

import tech.woloszyn.Elotech.domain.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
