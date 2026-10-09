
package com.sistema.chamada.service;

import com.sistema.chamada.dto.PresencaTurmaDTO;
import com.sistema.chamada.model.Chamada;
import com.sistema.chamada.model.Presenca;
import com.sistema.chamada.model.StatusChamada;
import com.sistema.chamada.repository.ChamadaRepository;
import com.sistema.chamada.repository.PresencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PresencaService {

    @Autowired
    private ChamadaRepository chamadaRepository;

    @Autowired
    private PresencaRepository presencaRepository;

    public List<PresencaTurmaDTO> calcularPorTurma(Long turmaId) {

        List<Chamada> chamadasFinalizadas =
                chamadaRepository.findByTurmaId(turmaId)
                        .stream()
                        .filter(chamada ->
                                chamada.getStatus() == StatusChamada.FINALIZADA)
                        .toList();

        if (chamadasFinalizadas.isEmpty()) {
            return List.of();
        }

        List<Long> idsChamadas = chamadasFinalizadas.stream()
                .map(Chamada::getId)
                .toList();

        List<Presenca> presencas =
                presencaRepository.findByChamadaIdIn(idsChamadas);

        Map<Long, Long> totalPresencasPorAluno = presencas.stream()
                .filter(presenca ->
                        Boolean.TRUE.equals(presenca.getPresente()))
                .collect(Collectors.groupingBy(
                        Presenca::getAlunoId,
                        Collectors.counting()
                ));

        return presencas.stream()
                .map(Presenca::getAlunoId)
                .distinct()
                .map(alunoId -> new PresencaTurmaDTO(
                        alunoId,
                        chamadasFinalizadas.size(),
                        totalPresencasPorAluno.getOrDefault(alunoId, 0L)
                ))
                .toList();
    }
}