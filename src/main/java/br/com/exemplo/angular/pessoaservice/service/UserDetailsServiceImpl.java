package br.com.exemplo.angular.pessoaservice.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.exemplo.angular.pessoaservice.model.Pessoa;
import br.com.exemplo.angular.pessoaservice.model.UserPrinciple;
import br.com.exemplo.angular.pessoaservice.repository.PessoaRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PessoaRepository pessoaRepositorio;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Pessoa p = pessoaRepositorio.findByUserName(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username : " + username));

		return UserPrinciple.build(p);
	}

}
