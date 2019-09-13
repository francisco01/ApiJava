package br.com.exemplo.angular.pessoaservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.exemplo.angular.pessoaservice.model.Pessoa;

public interface PessoaRepository  extends JpaRepository<Pessoa, Integer>{
	
	public Pessoa findByCodigo(Integer codigo);
	//public Pessoa findByUserName(String nome);
	public boolean existsByUserName(String nome);
	//public boolean existsByEmail(String email);
	//Optional<Pessoa> findByEmail(String email);
	Optional<Pessoa> findByUserName(String nome);
}
