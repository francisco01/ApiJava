package br.com.exemplo.angular.pessoaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.exemplo.angular.pessoaservice.model.Pessoa;
import br.com.exemplo.angular.pessoaservice.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

	public Tarefa findByCodigo(Integer codigo);
	
	@Query( value = "SELECT * FROM TAREFA WHERE ID_PESSOA = ?1", nativeQuery = true)
	public List<Tarefa> findByPessoa(Integer codigo);
	
	@Query(value= "SELECT p.id_pessoa, p.ativo, p.nome FROM PESSOA AS p JOIN TAREFA AS t "
			+ "on p.id_pessoa = t.id_pessoa and t.id_tarefa = ?1", nativeQuery = true)
	public Pessoa buscarPorPessoa(Integer codigo);
	
}
