package application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Aluno;
import application.repositories.AlunoRepository;

@RestController
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepo;

    @PostMapping("/alunos")
    public Aluno post(@RequestBody Aluno aluno) {
        return alunoRepo.save(aluno);
    }

    @GetMapping("/alunos/{id}")
    public Aluno getOne(@PathVariable Long id) {
        Optional<Aluno> resposta = alunoRepo.findById(id);
        if(resposta.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return resposta.get();
    }

    @GetMapping("/alunos")
    public List<Aluno> getAll() {
        return (List<Aluno>) alunoRepo.findAll();
    }

    @PutMapping("/alunos/{id}")
    public Aluno put(@RequestBody Aluno aluno,
            @PathVariable Long id) {
        Optional<Aluno> resposta = alunoRepo.findById(id);
        if(resposta.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        resposta.get().setNome(aluno.getNome());
        resposta.get().setIdade(aluno.getIdade());
        resposta.get().setCurso(aluno.getCurso());

        return alunoRepo.save(resposta.get());
    }

    @DeleteMapping("/alunos/{id}")
    public void delete(@PathVariable Long id) {
        if(alunoRepo.existsById(id)) {
            alunoRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
