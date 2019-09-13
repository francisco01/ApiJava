package br.com.exemplo.angular.pessoaservice.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.exemplo.angular.pessoaservice.JWT.JwtProvider;
import br.com.exemplo.angular.pessoaservice.JWT.JwtResponse;
import br.com.exemplo.angular.pessoaservice.form.LoginForm;
import br.com.exemplo.angular.pessoaservice.form.SignUpForm;
import br.com.exemplo.angular.pessoaservice.model.Pessoa;
import br.com.exemplo.angular.pessoaservice.model.Response;
import br.com.exemplo.angular.pessoaservice.repository.PessoaRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PessoaRepository pessoaRepositorio;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> autenticar(@Valid @RequestBody LoginForm loginForm) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginForm.getUserName(), loginForm.getSenha()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername()));
	}

//	@RequestMapping(value = "/signup", method= RequestMethod.POST, 
//			consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	@PostMapping("/signup")
	public ResponseEntity<?> registrar(@Valid @RequestBody SignUpForm signUpRequest) {
		
		//System.out.println("Teste" + signUpRequest.);

		if (pessoaRepositorio.existsByUserName(signUpRequest.getUserName())) {
			return new ResponseEntity<>(new Response(0,"Fail -> UserName is already in use!"),
					HttpStatus.BAD_REQUEST);
		}
		System.out.println("NOME: " + signUpRequest.getUserName());

		// Creating user's account
		Pessoa pessoa = new Pessoa(signUpRequest.getUserName(),
				encoder.encode(signUpRequest.getSenha()));

	
		pessoaRepositorio.save(pessoa);

		return new ResponseEntity<>(new Response(0,"User registered successfully!"), HttpStatus.OK);
	}

}
