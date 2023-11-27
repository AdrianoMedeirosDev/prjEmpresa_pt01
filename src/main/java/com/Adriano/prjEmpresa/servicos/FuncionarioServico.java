package com.Adriano.prjEmpresa.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Adriano.prjEmpresa.entidades.Funcionario;
import com.Adriano.prjEmpresa.repositorios.FuncionarioRepositorio;

@Service
public class FuncionarioServico {
	private final FuncionarioRepositorio funcionarioRepositorio;

	public FuncionarioServico(FuncionarioRepositorio funcionarioRepositorio) {
		this.funcionarioRepositorio = funcionarioRepositorio;
	}

	// preparando as buscas por id
	public Funcionario findFuncionarioById(Long funcodigo) {
		Optional<Funcionario> Funcionario = funcionarioRepositorio.findById(funcodigo);
		return Funcionario.orElse(null);
	}

	// preparando a busca geral
	public List<Funcionario> findAllFuncionario() {
		return funcionarioRepositorio.findAll();
	}

	// salvando o Jogo
	public Funcionario insertFuncionario(Funcionario funcionario) {
		return funcionarioRepositorio.save(funcionario);
	}

	// fazendo o update do jogo com o optional
	public Funcionario updateFuncionario(Long funcodigo, Funcionario novoFuncionario) {
		Optional<Funcionario> funcionarioOptional = funcionarioRepositorio.findById(funcodigo);
		if (funcionarioOptional.isPresent()) {
			Funcionario funcionarioExistente = funcionarioOptional.get();
			funcionarioExistente.setFunnome(novoFuncionario.getFunnome());
			funcionarioExistente.setFuncodigo(novoFuncionario.getFuncodigo());
			return funcionarioRepositorio.save(funcionarioExistente);
		} else {
			return null;
		}
	}

	// deletando o update do funcionario com o optional
	public boolean deleteFuncionario(Long funcodigo) {
		Optional<Funcionario> funcionarioExistente = funcionarioRepositorio.findById(funcodigo);
		if (funcionarioExistente.isPresent()) {
			funcionarioRepositorio.deleteById(funcodigo);
			return true;
		} else {
			return false;
		}
	}
}
