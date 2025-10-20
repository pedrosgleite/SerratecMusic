package org.serratec.serratemusic.domain;

import java.util.HashSet;
import java.util.Set;

import org.serratec.serratemusic.enums.GeneroMusical;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "musica")
public class Musica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@NotBlank(message = "Preencha o titulo da musica")
	@Column(name = "titulo", nullable = false)
	@Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
	private String titulo;
	
	@NotNull(message = "Preencha a minutagm")
	@Column(name = "minutos", nullable = false)
	private Integer minutos; 
	
	@NotNull(message = "Preencha o gênero musical")
	@Column(name = "genero", nullable = false)
	private GeneroMusical genero;
	
	@ManyToMany
	@JoinTable(
	    name = "musica_artista",
	    joinColumns = @JoinColumn(name = "musica_id"),
	    inverseJoinColumns = @JoinColumn(name = "artista_id"))
		private Set<Artista> artistas = new HashSet<>();
	
	@ManyToMany(mappedBy = "musicas")
	@JsonIgnore
	private Set<Playlist> playlists = new HashSet<>();
	
	public Musica(Long id,String titulo,Integer minutos, GeneroMusical genero, Set<Artista> artistas,Set<Playlist> playlists) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.minutos = minutos;
		this.genero = genero;
		this.artistas = artistas;
		this.playlists = playlists;
	}
	
	

	public Musica() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Set<Artista> getArtistas() {
		return artistas;
	}



	public void setArtistas(Set<Artista> artistas) {
		this.artistas = artistas;
	}



	public Set<Playlist> getPlaylists() {
		return playlists;
	}



	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getMinutos() {
		return minutos;
	}

	public void setMinutos(Integer minutos) {
		this.minutos = minutos;
	}

	public GeneroMusical getGenero() {
		return genero;
	}

	public void setGenero(GeneroMusical genero) {
		this.genero = genero;
	}
	
	

}
