package com.rd.treinamentodev.AvaliacaoSpringBoot.service;

import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.AlunoDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.CursoDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.InstrutorDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.TurmaDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.AlunoEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.CursoEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.InstrutorEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.TurmaEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.repository.CursoRepository;
import com.rd.treinamentodev.AvaliacaoSpringBoot.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    public List<TurmaDTO> listar(){
        List<TurmaEntity> listEntity = turmaRepository.findAll();
        List<TurmaDTO> listDTO = new ArrayList<>();

        for(TurmaEntity turm : listEntity) {
            TurmaDTO dto = new TurmaDTO();
            CursoDTO cursoDTO = new CursoDTO();

            cursoDTO.setNome(turm.getCurso().getNomeCurso());

            dto.setCurso(cursoDTO);
            dto.setDtInicio(SDF.format(turm.getDtInicio()));
            dto.setDtFim(SDF.format(turm.getDtFinal()));

            List<InstrutorDTO> instrutores = new ArrayList<>();

            for(InstrutorEntity instrutor : turm.getInstrutores()) {
                InstrutorDTO insDTO = new InstrutorDTO();

                insDTO.setNome(instrutor.getNomeInstrutor());
                insDTO.setValorHora(instrutor.getValorHora());

                instrutores.add(insDTO);
            }

            List<AlunoDTO> alunos = new ArrayList<>();

            for(AlunoEntity aluno : turm.getAlunos()) {
                AlunoDTO aluDTO = new AlunoDTO();

                aluDTO.setNome(aluno.getNomeAluno());
                aluDTO.setCpf(aluno.getCpf());

                alunos.add(aluDTO);
            }
            dto.setInstrutores(instrutores);
            dto.setAlunos(alunos);
            listDTO.add(dto);
        }

        return listDTO;
    }
}
