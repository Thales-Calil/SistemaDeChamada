package com.sistema.turma.service;

import com.sistema.turma.dto.DisciplinaRequestDTO;
import com.sistema.turma.dto.DisciplinaResponseDTO;
import com.sistema.turma.model.Disciplina;
import com.sistema.turma.repository.DisciplinaRepository;
import com.sistema.turma.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    public List<DisciplinaResponseDTO> listarTodos() {

        return disciplinaRepository.findAll()
                .stream()
                .map(DisciplinaResponseDTO::new)
                .toList();
    }

    public DisciplinaResponseDTO buscarPorId(Long id) {

        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Disciplina não encontrada"));

        return new DisciplinaResponseDTO(disciplina);
    }

    public DisciplinaResponseDTO cadastrar(
            DisciplinaRequestDTO dto) {

        Disciplina disciplina = new Disciplina();

        disciplina.setNome(dto.getNome());
        disciplina.setCodigo(dto.getCodigo());

        Disciplina disciplinaSalva =
                disciplinaRepository.save(disciplina);

        return new DisciplinaResponseDTO(disciplinaSalva);
    }

    public DisciplinaResponseDTO atualizar(
            Long id,
            DisciplinaRequestDTO dto) {

        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Disciplina não encontrada"));

        disciplina.setNome(dto.getNome());
        disciplina.setCodigo(dto.getCodigo());

        Disciplina disciplinaAtualizada =
                disciplinaRepository.save(disciplina);

        return new DisciplinaResponseDTO(disciplinaAtualizada);
    }

    public void excluir(Long id) {

        if (!disciplinaRepository.existsById(id)) {
            throw new RuntimeException("Disciplina não encontrada");
        }

        if (turmaRepository.existsByDisciplinaId(id)) {
            throw new RuntimeException(
                    "Não é possível excluir a disciplina porque ela está vinculada a uma turma"
            );
        }

        disciplinaRepository.deleteById(id);
    }
}