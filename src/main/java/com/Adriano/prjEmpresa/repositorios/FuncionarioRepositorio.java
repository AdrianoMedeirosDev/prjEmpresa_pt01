package com.Adriano.prjEmpresa.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Adriano.prjEmpresa.entidades.Funcionario;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long>{

}
