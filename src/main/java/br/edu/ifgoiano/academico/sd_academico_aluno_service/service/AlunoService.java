package br.edu.ifgoiano.academico.sd_academico_aluno_service.service;

import org.springframework.stereotype.Service;

import br.edu.ifgoiano.academico.sd_academico_aluno_service.entity.Aluno;
import br.edu.ifgoiano.academico.sd_academico_aluno_service.enums.StatusAluno;
import br.edu.ifgoiano.academico.sd_academico_aluno_service.repository.AlunoRepository;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public Aluno criarAluno(Aluno aluno) {

        if (repository.existsByMatricula(aluno.getMatricula())) {
            throw new RuntimeException("Matrícula já cadastrada.");
        }

        if (repository.existsByEmail(aluno.getEmail())) {
            throw new RuntimeException("Email já cadastrado.");
        }

        if (aluno.getStatus() == null) {
            aluno.setStatus(StatusAluno.ATIVO);
        }

        return repository.save(aluno);
    }

    public List<Aluno> listarTodos() {
        return repository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
    }

    public boolean alunoExiste(Long id) {
        return repository.existsById(id);
    }
}