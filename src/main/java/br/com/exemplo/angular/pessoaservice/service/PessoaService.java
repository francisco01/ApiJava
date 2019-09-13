package br.com.exemplo.angular.pessoaservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.exemplo.angular.pessoaservice.model.Pessoa;
import br.com.exemplo.angular.pessoaservice.model.Response;
import br.com.exemplo.angular.pessoaservice.repository.PessoaRepository;

@CrossOrigin(origins  = "*", allowedHeaders = "*" )
@RestController
@RequestMapping("/service")
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	public String login() {
		return "";
	}
	
//	@RequestMapping(value="/registrar", method= RequestMethod.POST, 
//			consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public @ResponseBody Response registrar(@RequestBody Pessoa pessoa) {
//		Pessoa p = pessoaRepository.findByUserName(pessoa.getUserName());
//		if (p != null) {
//			return new Response (0, "Usuário já cadastrado");
//		}else {
//			try {
//				pessoa.setSenha(passwordEncoder.encode(pessoa.getSenha()));
//				this.pessoaRepository.save(pessoa);
//				return new Response(1, "Usuário cadastrado com sucesso!");
//			}catch(Exception e) {
//				return new Response(0, e.getMessage());
//			}
//		}
//	}
	
	@RequestMapping(value="/pessoa", method= RequestMethod.PUT, 
			consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Response atualizar(@RequestBody Pessoa pessoa) {
		try {
			this.pessoaRepository.save(pessoa);
			return new Response(1, "Registro atualizado com sucesso!");
		}catch(Exception e) {
			return new Response(0, e.getMessage());
		}
	}
	
	@RequestMapping(value="/pessoa", method = RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Pessoa> consultar(){
		return this.pessoaRepository.findAll();
	}
	
	@RequestMapping(value="/pessoa/{nome}", method = RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Optional<Pessoa> buscar(@PathVariable("nome") String nome) {
		//System.out.println("Nome: " + nome);
		return this.pessoaRepository.findByUserName(nome);
		
	}
	
	@RequestMapping(value="/pessoa/{codigo}", method = RequestMethod.DELETE, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Response excluir(@PathVariable("codigo") Integer codigo){
 
		Pessoa pessoa = pessoaRepository.findByCodigo(codigo);
 
		try {
 
			pessoaRepository.delete(pessoa);
 
			return new Response(1, "Registro excluido com sucesso!");
 
		}catch(Exception e) {
			return new Response(0, e.getMessage());
		}
	}
	
	
	
}
