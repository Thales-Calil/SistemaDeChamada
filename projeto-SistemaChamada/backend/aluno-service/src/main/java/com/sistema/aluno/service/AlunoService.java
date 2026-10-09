package com.sistema.aluno.service;

import com.sistema.aluno.dto.AlunoRequestDTO;
import com.sistema.aluno.dto.AlunoResponseDTO;
import com.sistema.aluno.model.Aluno;
import com.sistema.aluno.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<AlunoResponseDTO> listarTodos() {
        return alunoRepository.findAll()
                .stream()
                .map(AlunoResponseDTO::new)
                .toList();
    }

    public AlunoResponseDTO buscarPorId(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        return new AlunoResponseDTO(aluno);
    }

    public AlunoResponseDTO cadastrar(AlunoRequestDTO dto) {

        Aluno aluno = new Aluno();

        aluno.setNome(dto.getNome());
        aluno.setMatricula(dto.getMatricula());
        aluno.setEmail(dto.getEmail());

        Aluno alunoSalvo = alunoRepository.save(aluno);

        return new AlunoResponseDTO(alunoSalvo);
    }

    public AlunoResponseDTO atualizar(Long id, AlunoRequestDTO dto) {

        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Aluno não encontrado"));

        aluno.setNome(dto.getNome());
        aluno.setMatricula(dto.getMatricula());
        aluno.setEmail(dto.getEmail());

        Aluno alunoAtualizado = alunoRepository.save(aluno);

        return new AlunoResponseDTO(alunoAtualizado);
    }

    public void excluir(Long id) {

        if (!alunoRepository.existsById(id)) {
            throw new RuntimeException("Aluno não encontrado");
        }

        alunoRepository.deleteById(id);
    }
}