package application.repositories;

import org.springframework.data.repository.CrudRepository;

import application.model.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {
    
}
