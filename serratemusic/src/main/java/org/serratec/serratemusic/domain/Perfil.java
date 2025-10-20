package org.serratec.serratemusic.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "perfil")
public class Perfil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Preencha o telefone")
	@Column(nullable = false)
	@Size(max = 15, message = "O telefone deve ter no máximo 15 caracteres")
	private String telefone;
	
	@NotNull(message = "Preencha a data de nascimento")
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;
	
	public Perfil(Long id,
			@NotBlank(message = "Preencha o telefone") String telefone,
			@NotNull(message = "Preencha a data de nascimento") LocalDate dataNascimento)
	{
		super();
		this.id = id;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
	}

	
	public Perfil() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
	
	
}


