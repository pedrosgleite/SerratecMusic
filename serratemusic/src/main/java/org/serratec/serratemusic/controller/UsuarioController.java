package org.serratec.serratemusic.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratemusic.domain.Usuario;
import org.serratec.serratemusic.repository.UsuarioRepository;
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
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	@Operation(summary = "Lista todos os usuários", description = "Retorna todos os usuários cadastrados no sistema")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
	})
	public ResponseEntity<List<Usuario>> listar() {
	    return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Busca usuário por ID", description = "Retorna um usuário específico pelo seu ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Usuário encontrado"),
		@ApiResponse(responseCode = "404", description = "Usuário não encontrado")
	})
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) { 
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@Operation(summary = "Cria um novo usuário", description = "Cadastra um novo usuário no sistema")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
		@ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
	})
	public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario) {
	    Usuario novoUsuario = usuarioRepository.save(usuario);
	    return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
	}   
	
	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um usuário", description = "Atualiza os dados de um usuário existente pelo seu ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
		@ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
	})
	public ResponseEntity<Usuario> atualizar(@Valid @PathVariable Long id, @RequestBody Usuario usuario) {
		if(!usuarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		usuario.setId(id);
		usuario = usuarioRepository.save(usuario);
		return ResponseEntity.ok(usuario);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um usuário", description = "Remove um usuário do sistema pelo seu ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Usuário não encontrado")
	})
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!usuarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		usuarioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}