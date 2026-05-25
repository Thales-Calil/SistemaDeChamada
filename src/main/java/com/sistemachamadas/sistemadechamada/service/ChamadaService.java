package com.sistemachamadas.sistemadechamada.service;

import com.sistemachamadas.sistemadechamada.dto.AulaDTO;
import com.sistemachamadas.sistemadechamada.model.Aula;
import com.sistemachamadas.sistemadechamada.model.TipoUsuario;
import com.sistemachamadas.sistemadechamada.model.Usuario;
import com.sistemachamadas.sistemadechamada.repository.AulaRepository;
import com.sistemachamadas.sistemadechamada.repository.PresencaRepository;
import com.sistemachamadas.sistemadechamada.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamadaService {

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PresencaRepository presencaRepository;

    /*
     * CADASTRAR NOVA CHAMADA
     */
    public void cadastrarChamada(AulaDTO aulaDTO) {

        Usuario professor = usuarioRepository.findById(aulaDTO.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        if (professor.getTipo() != TipoUsuario.PROFESSOR) {
            throw new RuntimeException("Apenas professores podem criar chamadas");
        }

        Aula aula = new Aula();
        aula.setDisciplina(aulaDTO.getDisciplina());
        aula.setData(aulaDTO.getData());
        aula.setProfessor(professor);

        aulaRepository.save(aula);
    }

    /*
     * LISTAR TODAS AS CHAMADAS
     */
    public List<Aula> listarChamada() {
        return aulaRepository.findAll();
    }

    public AulaDTO buscarPorId(Long id) {

        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamada não encontrada"));

        AulaDTO dto = new AulaDTO();
        dto.setId(aula.getId());
        dto.setDisciplina(aula.getDisciplina());
        dto.setData(aula.getData());
        dto.setProfessorId(aula.getProfessor().getId());

        return dto;
    }

    /*
     * EDITAR CHAMADA
     */
    public void editar(Long id, AulaDTO dto) {

        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamada não encontrada"));

        Usuario professor = usuarioRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        if (professor.getTipo() != TipoUsuario.PROFESSOR) {
            throw new RuntimeException("Usuário não é professor");
        }

        aula.setDisciplina(dto.getDisciplina());
        aula.setData(dto.getData());
        aula.setProfessor(professor);

        aulaRepository.save(aula);
    }

    /*
     * EXCLUIR CHAMADA
     */
    public void excluir(Long id) {

        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamada não encontrada"));

        presencaRepository.deleteAll(
                presencaRepository.findByAula(aula)
        );

        aulaRepository.delete(aula);
    }
}