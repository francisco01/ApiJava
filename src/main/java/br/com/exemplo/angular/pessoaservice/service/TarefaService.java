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

import br.com.exemplo.angular.pessoaservice.model.Response;
import br.com.exemplo.angular.pessoaservice.model.Tarefa;
import br.com.exemplo.angular.pessoaservice.repository.TarefaRepository;

@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class TarefaService {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	//@Autowired
	//private PessoaRepository pessoaRepository;
	
	@RequestMapping(value="/tarefa", method= RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Response salvar(@RequestBody Tarefa tarefa) {
		
		try {
			this.tarefaRepository.save(tarefa);
			return new Response (1, "Tarefa salva com sucesso");
		}catch(Exception e) {
			return new Response (0, e.getMessage());
		}
		
	}
	
//	@RequestMapping(value="/tarefa", method = RequestMethod.GET, 
//			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public @ResponseBody List<Tarefa> consultar(){
//		return this.tarefaRepository.findAll();
//	}
	
	@RequestMapping(value="/tarefa/{codigo}", method = RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Tarefa> consultar( @PathVariable("codigo") Integer codigo ){
		//System.out.println("codigo: " + codigo);
		return this.tarefaRepository.findByPessoa(codigo);
	}
	
	@RequestMapping(value="/tarefa/editar/{codigo}", method = RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Tarefa buscar(@PathVariable("codigo") Integer codigo) {
		return this.tarefaRepository.findByCodigo(codigo);
		
	}
//	
//	@RequestMapping(value="/tarefa/{codigo}", method = RequestMethod.GET, 
//			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public Tarefa buscar(@PathVariable("codigo") Integer codigo) {
//		return this.tarefaRepository.findByCodigo(codigo);
//		
//	}
	
	@RequestMapping(value="/tarefa", method= RequestMethod.PUT, 
			consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Response atualizar(@RequestBody Tarefa tarefa) {
		try {
			this.tarefaRepository.save(tarefa);
			return new Response(1, "Registro atualizado com sucesso!");
		}catch(Exception e) {
			return new Response(0, e.getMessage());
		}
	}
	
	@RequestMapping(value="/tarefa/{codigo}", method = RequestMethod.DELETE, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Response excluir(@PathVariable("codigo") Integer codigo){
 
		Tarefa tarefa = tarefaRepository.findByCodigo(codigo);
 
		try {
 
			tarefaRepository.delete(tarefa);
 
			return new Response(1, "Registro excluido com sucesso!");
 
		}catch(Exception e) {
			return new Response(0, e.getMessage());
		}
	}
	

}
