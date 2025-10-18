package org.serratec.serratemusic.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "artista")
public class Artista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Preencha o nome do artista")
	@Column(nullable = false)
	private String nome;
	
	@NotBlank(message = "Preencha a nacionalidade do artista")
	@Column(nullable = false)
	private String nacionalidade;

}
