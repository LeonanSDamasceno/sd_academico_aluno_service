package br.edu.ifgoiano.academico.sd_academico_aluno_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.edu.ifgoiano.academico.sd_academico_aluno_service.entity.Aluno;
import br.edu.ifgoiano.academico.sd_academico_aluno_service.service.AlunoService;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private static final Logger logger = LoggerFactory.getLogger(AlunoController.class);

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno criarAluno(@RequestBody Aluno aluno) {
        logger.info("[ALUNO-SERVICE] POST /alunos - matrícula: {}", aluno.getMatricula());
        return service.criarAluno(aluno);
    }

    @GetMapping
    public List<Aluno> listarTodos() {
        logger.info("[ALUNO-SERVICE] GET /alunos");
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Aluno buscarPorId(@PathVariable Long id) {
        logger.info("[ALUNO-SERVICE] GET /alunos/{}", id);
        return service.buscarPorId(id);
    }

    @GetMapping("/{id}/existe")
    public boolean alunoExiste(@PathVariable Long id) {
        logger.info("[ALUNO-SERVICE] GET /alunos/{}/existe", id);
        return service.alunoExiste(id);
    }
}
