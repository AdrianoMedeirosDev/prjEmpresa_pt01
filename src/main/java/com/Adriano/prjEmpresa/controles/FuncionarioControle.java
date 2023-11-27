package com.Adriano.prjEmpresa.controles;

import java.util.List;


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

import com.Adriano.prjEmpresa.entidades.Funcionario;
import com.Adriano.prjEmpresa.servicos.FuncionarioServico;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioControle {
	private final FuncionarioServico funcionarioServico;

	public FuncionarioControle(FuncionarioServico funcionarioServico) {
		this.funcionarioServico = funcionarioServico;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza funcionario por ID")
	public ResponseEntity<Funcionario> findFuncionariobyId(@PathVariable Long funcodigo) {
		Funcionario funcionario = funcionarioServico.findFuncionarioById(funcodigo);
		if (funcionario != null) {
			return ResponseEntity.ok(funcionario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta todos os funcionarios")
	public ResponseEntity<List<Funcionario>> findAllFuncionarios() {
		List<Funcionario> funcionarios = funcionarioServico.findAllFuncionario();
		return ResponseEntity.ok(funcionarios);
	}

	@PostMapping
	@Operation(summary = "Cadastra um funcionario")
	public ResponseEntity<Funcionario> insertFuncionariosControl(@RequestBody @Valid Funcionario funcionario) {
		Funcionario novoFuncionario = funcionarioServico.insertFuncionario(funcionario);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);
	}

	@PutMapping("/id")
	@Operation(summary = "Altera um funcionario")
	public ResponseEntity<Funcionario> updateFuncionarioControl(@PathVariable Long funcodigo, @RequestBody @Valid Funcionario funcionario) {
		Funcionario mudafuncionario = funcionarioServico.updateFuncionario(funcodigo, funcionario);
		if (mudafuncionario != null) {
			return ResponseEntity.ok(funcionario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/id")
	@Operation(summary = "Exclui um funcionario")
	public ResponseEntity<String> deleteFuncionario(@PathVariable Long funcodigo) {
		boolean remover = funcionarioServico.deleteFuncionario(funcodigo);
		if (remover) {
			return ResponseEntity.ok().body("funcionario Excluido com sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
