package br.edu.ifgoiano.academico.sd_academico_aluno_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.edu.ifgoiano.academico.sd_academico_aluno_service.entity.Aluno;
import br.edu.ifgoiano.academico.sd_academico_aluno_service.enums.StatusAluno;
import br.edu.ifgoiano.academico.sd_academico_aluno_service.repository.AlunoRepository;

import java.util.List;

@Service
public class AlunoService {

    private static final Logger logger = LoggerFactory.getLogger(AlunoService.class);

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public Aluno criarAluno(Aluno aluno) {
        logger.info("[ALUNO-SERVICE] Criando aluno com matrícula: {}", aluno.getMatricula());

        if (repository.existsByMatricula(aluno.getMatricula())) {
            logger.warn("[ALUNO-SERVICE] Matrícula já cadastrada: {}", aluno.getMatricula());
            throw new RuntimeException("Matrícula já cadastrada.");
        }

        if (repository.existsByEmail(aluno.getEmail())) {
            logger.warn("[ALUNO-SERVICE] Email já cadastrado: {}", aluno.getEmail());
            throw new RuntimeException("Email já cadastrado.");
        }

        if (aluno.getStatus() == null) {
            aluno.setStatus(StatusAluno.ATIVO);
        }

        Aluno salvo = repository.save(aluno);
        logger.info("[ALUNO-SERVICE] Aluno criado com sucesso - ID: {}, Matrícula: {}", salvo.getId(), salvo.getMatricula());
        return salvo;
    }

    public List<Aluno> listarTodos() {
        logger.info("[ALUNO-SERVICE] Listando todos os alunos");
        return repository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        logger.info("[ALUNO-SERVICE] Buscando aluno ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("[ALUNO-SERVICE] Aluno ID {} não encontrado", id);
                    return new RuntimeException("Aluno não encontrado.");
                });
    }

    public boolean alunoExiste(Long id) {
        boolean existe = repository.existsById(id);
        logger.info("[ALUNO-SERVICE] Verificando existência do aluno ID: {} -> {}", id, existe);
        return existe;
    }
}