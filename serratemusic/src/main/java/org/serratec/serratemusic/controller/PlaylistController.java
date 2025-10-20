package org.serratec.serratemusic.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratemusic.domain.Playlist;
import org.serratec.serratemusic.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {
	
	@Autowired
	private PlaylistRepository playlistRepository;
	
	@GetMapping
	 @Operation(summary = "Lista todas as playlists")
	public ResponseEntity<List<Playlist>> listar() {
	    return ResponseEntity.ok(playlistRepository.findAll());
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Busca playlist por ID", description = "Retorna uma playlist específica pelo seu ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Playlist encontrada"),
		@ApiResponse(responseCode = "404", description = "Playlist não encontrada")
	})
	public ResponseEntity<Playlist> buscarPorId(@PathVariable Long id) { 
		Optional<Playlist> playlist = playlistRepository.findById(id);
		if(playlist.isPresent()) {
			return ResponseEntity.ok(playlist.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@Operation(summary = "Cria uma nova playlist", description = "Cadastra uma nova playlist no sistema")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Playlist criada com sucesso"),
		@ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
	})
	public ResponseEntity<Playlist> criarPlaylist(@Valid @RequestBody Playlist playlist) {
		Playlist novaPlaylist = playlistRepository.save(playlist);
	    return ResponseEntity.status(HttpStatus.CREATED).body(novaPlaylist);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Atualiza uma playlist", description = "Atualiza os dados de uma playlist existente pelo seu ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Playlist atualizada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Playlist não encontrada"),
		@ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
	})
	public ResponseEntity<Playlist> atualizar(@Valid @PathVariable Long id, @RequestBody Playlist playlist) {
		if (!playlistRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		playlist.setId(id);
		playlist.setNome(playlist.getNome());
		playlist.setDescricao(playlist.getDescricao());
		playlist = playlistRepository.save(playlist);
		return ResponseEntity.ok(playlist);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta uma playlist", description = "Remove uma playlist do sistema pelo seu ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Playlist deletada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Playlist não encontrada")
	})
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!playlistRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		playlistRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
}
