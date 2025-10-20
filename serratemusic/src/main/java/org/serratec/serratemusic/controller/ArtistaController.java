package org.serratec.serratemusic.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratemusic.domain.Artista;
import org.serratec.serratemusic.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	@GetMapping
	@Operation(summary = "Lista todos os artistas", description = "Retorna todos os artistas cadastrados")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
	public ResponseEntity<List<Artista>> listar() {
	    return ResponseEntity.ok(artistaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Busca artista por ID", description = "Retorna um artista específico pelo seu ID")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Artista encontrado"),
    @ApiResponse(responseCode = "404", description = "Artista não encontrado")
    })
	public ResponseEntity<Artista> buscarPorId(@PathVariable Long id) { 
		Optional<Artista> artista = artistaRepository.findById(id);
		if(artista.isPresent()) {
			return ResponseEntity.ok(artista.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@PostMapping
	@Operation(summary = "Cria um novo artista", description = "Cadastra um novo artista no sistema")
    @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Artista criado com sucesso"),
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
	public ResponseEntity<Artista> criarArtista(@Valid @RequestBody Artista artista) {
	    Artista novoArtista = artistaRepository.save(artista);
	    return ResponseEntity.status(HttpStatus.CREATED).body(novoArtista);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um artista", description = "Remove um artista do sistema pelo seu ID")
    @ApiResponses({
    @ApiResponse(responseCode = "204", description = "Artista deletado com sucesso"),
    @ApiResponse(responseCode = "404", description = "Artista não encontrado")
    })
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!artistaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		artistaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
