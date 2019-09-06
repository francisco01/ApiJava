package br.com.exemplo.angular.pessoaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.exemplo.angular.pessoaservice.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

	public Tarefa findByCodigo(Integer codigo);
	@Query( value = "SELECT * FROM TAREFA WHERE ID_PESSOA = ?1", nativeQuery = true)
	public List<Tarefa> findByPessoa(Integer codigo);
}
