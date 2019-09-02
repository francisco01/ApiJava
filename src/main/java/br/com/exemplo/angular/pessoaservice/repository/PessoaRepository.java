package br.com.exemplo.angular.pessoaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.exemplo.angular.pessoaservice.model.Pessoa;

public interface PessoaRepository  extends JpaRepository<Pessoa, Integer>{
	
	public Pessoa findByCodigo(Integer codigo);
}
