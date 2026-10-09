package com.sistema.turma.service;

import com.sistema.turma.dto.TurmaRequestDTO;
import com.sistema.turma.dto.TurmaResponseDTO;
import com.sistema.turma.model.Disciplina;
import com.sistema.turma.model.Professor;
import com.sistema.turma.model.Turma;
import com.sistema.turma.repository.DisciplinaRepository;
import com.sistema.turma.repository.ProfessorRepository;
import com.sistema.turma.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<TurmaResponseDTO> listarTodos() {
        return turmaRepository.findAll()
                .stream()
                .map(TurmaResponseDTO::new)
                .toList();
    }

    public TurmaResponseDTO buscarPorId(Long id) {

        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Turma não encontrada"));

        return new TurmaResponseDTO(turma);
    }

    public TurmaResponseDTO cadastrar(TurmaRequestDTO dto) {

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() ->
                        new RuntimeException("Professor não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() ->
                        new RuntimeException("Disciplina não encontrada"));

        Turma turma = new Turma();

        turma.setNome(dto.getNome());
        turma.setProfessor(professor);
        turma.setDisciplina(disciplina);

        Turma turmaSalva = turmaRepository.save(turma);

        return new TurmaResponseDTO(turmaSalva);
    }

    public TurmaResponseDTO atualizar(Long id, TurmaRequestDTO dto) {

        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Turma não encontrada"));

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() ->
                        new RuntimeException("Professor não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() ->
                        new RuntimeException("Disciplina não encontrada"));

        turma.setNome(dto.getNome());
        turma.setProfessor(professor);
        turma.setDisciplina(disciplina);

        Turma turmaAtualizada = turmaRepository.save(turma);

        return new TurmaResponseDTO(turmaAtualizada);
    }

    public void excluir(Long id) {

        if (!turmaRepository.existsById(id)) {
            throw new RuntimeException("Turma não encontrada");
        }

        turmaRepository.deleteById(id);
    }
}