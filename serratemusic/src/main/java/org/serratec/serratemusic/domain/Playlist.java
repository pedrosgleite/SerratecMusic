package org.serratec.serratemusic.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "playlist")	
public class Playlist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Preencha o nome da playlist")
	@Column(name = "nome", nullable = false)
	@Size(max = 100, message = "O nome da playlist deve ter no máximo 100 caracteres")
	private String nome;
	
	@NotBlank(message = "Preencha a descrição da playlist")
	@Column(name = "descricao", nullable = false)
	@Size(max = 255, message = "A descrição da playlist deve ter no máximo 255 caracteres")
	private String descricao;
	
	@ManyToMany
	@JoinTable(
	    name = "playlist_musica",
	    joinColumns = @JoinColumn(name = "playlist_id"),
	    inverseJoinColumns = @JoinColumn(name = "musica_id"))
	@JsonManagedReference 
	private Set<Musica> musicas = new HashSet<>();
	
	@ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
	@JsonBackReference
    private Usuario usuario;
	
	public Playlist(Long id,String nome,String descricao, Set<Musica> musicas,Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.musicas = musicas;
		this.usuario = usuario;
	}
	

	public Playlist() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(Set<Musica> musicas) {
		this.musicas = musicas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
