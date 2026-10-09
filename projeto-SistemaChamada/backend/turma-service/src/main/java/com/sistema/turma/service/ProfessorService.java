package com.sistema.turma.service;

import com.sistema.turma.dto.ProfessorRequestDTO;
import com.sistema.turma.dto.ProfessorResponseDTO;
import com.sistema.turma.model.Professor;
import com.sistema.turma.repository.ProfessorRepository;
import com.sistema.turma.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    public List<ProfessorResponseDTO> listarTodos() {
        return professorRepository.findAll()
                .stream()
                .map(ProfessorResponseDTO::new)
                .toList();
    }

    public ProfessorResponseDTO buscarPorId(Long id) {

        Professor professor = professorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Professor não encontrado"));

        return new ProfessorResponseDTO(professor);
    }

    public ProfessorResponseDTO cadastrar(ProfessorRequestDTO dto) {

        Professor professor = new Professor();

        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());

        Professor professorSalvo = professorRepository.save(professor);

        return new ProfessorResponseDTO(professorSalvo);
    }

    public ProfessorResponseDTO atualizar(
            Long id,
            ProfessorRequestDTO dto) {

        Professor professor = professorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Professor não encontrado"));

        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());

        Professor professorAtualizado =
                professorRepository.save(professor);

        return new ProfessorResponseDTO(professorAtualizado);
    }

    public void excluir(Long id) {

        if (!professorRepository.existsById(id)) {
            throw new RuntimeException("Professor não encontrado");
        }

        if (turmaRepository.existsByProfessorId(id)) {
            throw new RuntimeException(
                    "Não é possível excluir o professor porque ele está vinculado a uma turma"
            );
        }

        professorRepository.deleteById(id);
    }
}