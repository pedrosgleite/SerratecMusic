package org.serratec.serratemusic.domain;

import org.serratec.serratemusic.enums.GeneroMusical;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "musica")
public class Musica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@NotBlank(message = "Preencha o titulo da musica")
	@Column(name = "titulo", nullable = false)
	private String titulo;
	
	@NotBlank(message = "Preencha a minutagm")
	@Column(name = "minutos", nullable = false)
	private Integer minutos; 
	
	@NotBlank(message = "Preencha o gênero musical")
	@Column(name = "genero", nullable = false)
	private GeneroMusical genero;

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
