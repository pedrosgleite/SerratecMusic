package org.serratec.serratemusic.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratemusic.domain.Musica;
import org.serratec.serratemusic.repository.MusicaRepository;
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
@RequestMapping("/musicas")
public class MusicaController {
	
	@Autowired
	private MusicaRepository musicaRepository;
	
	@GetMapping
	@Operation(summary = "Lista todas as músicas", description = "Retorna todas as músicas cadastradas no sistema")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lista de músicas retornada com sucesso")
    })
	public ResponseEntity<List<Musica>> listar() {
	    return ResponseEntity.ok(musicaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Busca música por ID", description = "Retorna uma música específica pelo seu ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Música encontrada"),
        @ApiResponse(responseCode = "404", description = "Música não encontrada")
    })
	public ResponseEntity<Musica> buscarPorId(@PathVariable Long id) { 
		Optional<Musica> musica = musicaRepository.findById(id);
		if(musica.isPresent()) {
			return ResponseEntity.ok(musica.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	@PostMapping
	@Operation(summary = "Cria uma nova música", description = "Cadastra uma nova música no sistema")
    @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Música criada com sucesso"),
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
	public ResponseEntity<Musica> criarMusica(@Valid @RequestBody Musica musica) {
		Musica novaMusica = musicaRepository.save(musica);
	    return ResponseEntity.status(HttpStatus.CREATED).body(novaMusica);
	}
	@PutMapping("/{id}")
	@Operation(summary = "Atualiza uma música", description = "Atualiza os dados de uma música existente pelo seu ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Música atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Música não encontrada"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
	public ResponseEntity<Musica> atualizar(@Valid @PathVariable Long id, @RequestBody Musica musica) {
		if(!musicaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		musica.setId(id);
		musica = musicaRepository.save(musica);
		return ResponseEntity.ok(musica);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta uma música", description = "Remove uma música do sistema pelo seu ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Música deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Música não encontrada")
    })
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!musicaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		musicaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
