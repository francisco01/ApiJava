package br.com.exemplo.angular.pessoaservice.model;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPrinciple implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private String userName;
	
	@JsonIgnore
	private String senha;
	
	public UserPrinciple(Integer codigo, String userName, String senha) {
		this.codigo = codigo;
		this.userName = userName;
		this.senha = senha;
	}
	
	 public static UserPrinciple build(Pessoa pessoa) {
	       
	        return new UserPrinciple(
	                pessoa.getCodigo(),
	                pessoa.getUserName(),
	                pessoa.getSenha()
	        );
	    }
	 
	 

	public Integer getCodigo() {
		return codigo;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UserPrinciple pessoa = (UserPrinciple) o;
        return Objects.equals(codigo, pessoa.codigo);
    }
	
	
	

}
