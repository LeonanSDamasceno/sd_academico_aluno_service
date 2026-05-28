package br.edu.ifgoiano.academico.sd_academico_aluno_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import br.edu.ifgoiano.academico.sd_academico_aluno_service.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByMatricula(String matricula);

    Optional<Aluno> findByEmail(String email);

    boolean existsByMatricula(String matricula);

    boolean existsByEmail(String email);
}