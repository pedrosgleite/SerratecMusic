package org.serratec.serratemusic.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "artista")
public class Artista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Preencha o nome do artista")
	@Column(nullable = false)
	@Size(max = 100, message = "O nome do artista deve ter no máximo 100 caracteres")
	private String nome;
	
	@NotBlank(message = "Preencha a nacionalidade do artista")
	@Column(nullable = false)
	@Size(max = 50, message = "A nacionalidade do artista deve ter no máximo 50 caracteres")
	private String nacionalidade;
	
	@ManyToMany(mappedBy = "artistas")	
	@JsonIgnore
	private Set<Musica> musicas = new HashSet<>();
	
	public Artista() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Artista(Long id, String nome, String nacionalidade, Set<Musica> musicas) {
		super();
		this.id = id;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
		this.musicas = musicas;
	}
	

	public Set<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(Set<Musica> musicas) {
		this.musicas = musicas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	

}
