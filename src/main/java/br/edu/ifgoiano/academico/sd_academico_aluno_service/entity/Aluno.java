package br.edu.ifgoiano.academico.sd_academico_aluno_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import br.edu.ifgoiano.academico.sd_academico_aluno_service.enums.StatusAluno;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String matricula;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String curso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAluno status;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    public Aluno() {
    }

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();

        if (this.status == null) {
            this.status = StatusAluno.ATIVO;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public StatusAluno getStatus() {
        return status;
    }

    public void setStatus(StatusAluno status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}
