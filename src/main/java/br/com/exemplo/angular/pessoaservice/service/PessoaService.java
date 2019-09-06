package br.com.exemplo.angular.pessoaservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	
	@RequestMapping(value="/pessoa", method= RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Response salvar(@RequestBody Pessoa pessoa) {
		try {
			this.pessoaRepository.save(pessoa);
			return new Response(1, "Registro salvo com sucesso!");
		}catch(Exception e) {
			return new Response(0, e.getMessage());
		}
	}
	
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
	
	@RequestMapping(value="/pessoa/{codigo}", method = RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Pessoa buscar(@PathVariable("codigo") Integer codigo) {
		return this.pessoaRepository.findByCodigo(codigo);
		
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
