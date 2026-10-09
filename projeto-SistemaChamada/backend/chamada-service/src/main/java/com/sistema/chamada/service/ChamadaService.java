package com.sistema.chamada.service;

import com.sistema.chamada.dto.ChamadaResponseDTO;
import com.sistema.chamada.dto.CriarChamadaDTO;
import com.sistema.chamada.dto.PresencaResponseDTO;
import com.sistema.chamada.dto.RegistrarPresencaDTO;
import com.sistema.chamada.model.Chamada;
import com.sistema.chamada.model.Presenca;
import com.sistema.chamada.model.StatusChamada;
import com.sistema.chamada.repository.ChamadaRepository;
import com.sistema.chamada.repository.PresencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamadaService {

    @Autowired
    private ChamadaRepository chamadaRepository;

    @Autowired
    private PresencaRepository presencaRepository;

    public ChamadaResponseDTO criar(CriarChamadaDTO dto) {

        Chamada chamada = new Chamada();

        chamada.setTurmaId(dto.getTurmaId());
        chamada.setData(dto.getData());
        chamada.setHorario(dto.getHorario());
        chamada.setStatus(StatusChamada.ABERTA);

        Chamada chamadaSalva = chamadaRepository.save(chamada);

        return new ChamadaResponseDTO(chamadaSalva);
    }

    public ChamadaResponseDTO buscarPorId(Long id) {

        Chamada chamada = chamadaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Chamada não encontrada"));

        return new ChamadaResponseDTO(chamada);
    }

    public List<ChamadaResponseDTO> listarPorTurma(Long turmaId) {

        return chamadaRepository.findByTurmaId(turmaId)
                .stream()
                .map(ChamadaResponseDTO::new)
                .toList();
    }

    public PresencaResponseDTO registrarPresenca(
            Long chamadaId,
            RegistrarPresencaDTO dto) {

        Chamada chamada = chamadaRepository.findById(chamadaId)
                .orElseThrow(() ->
                        new RuntimeException("Chamada não encontrada"));

        if (chamada.getStatus() == StatusChamada.FINALIZADA) {
            throw new RuntimeException(
                    "Não é possível registrar presença em uma chamada finalizada"
            );
        }

        Presenca presenca = presencaRepository
                .findByChamadaIdAndAlunoId(
                        chamadaId,
                        dto.getAlunoId()
                )
                .orElse(new Presenca());

        presenca.setChamadaId(chamadaId);
        presenca.setAlunoId(dto.getAlunoId());
        presenca.setPresente(dto.getPresente());

        Presenca presencaSalva =
                presencaRepository.save(presenca);

        return new PresencaResponseDTO(presencaSalva);
    }

    public PresencaResponseDTO atualizarPresenca(
            Long chamadaId,
            Long alunoId,
            RegistrarPresencaDTO dto) {

        Chamada chamada = chamadaRepository.findById(chamadaId)
                .orElseThrow(() ->
                        new RuntimeException("Chamada não encontrada"));

        if (chamada.getStatus() == StatusChamada.FINALIZADA) {
            throw new RuntimeException(
                    "Não é possível alterar presença de uma chamada finalizada"
            );
        }

        Presenca presenca = presencaRepository
                .findByChamadaIdAndAlunoId(
                        chamadaId,
                        alunoId
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Presença do aluno não encontrada"
                        ));

        presenca.setPresente(dto.getPresente());

        Presenca presencaAtualizada =
                presencaRepository.save(presenca);

        return new PresencaResponseDTO(presencaAtualizada);
    }

    public void finalizar(Long id) {

        Chamada chamada = chamadaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Chamada não encontrada"));

        chamada.setStatus(StatusChamada.FINALIZADA);

        chamadaRepository.save(chamada);
    }

    public void excluir(Long id) {

        Chamada chamada = chamadaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Chamada não encontrada"));

        if (chamada.getStatus() == StatusChamada.FINALIZADA) {
            throw new RuntimeException(
                    "Não é possível excluir uma chamada finalizada"
            );
        }

        chamadaRepository.deleteById(id);
    }
}