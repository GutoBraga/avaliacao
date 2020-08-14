package com.rd.treinamentodev.AvaliacaoSpringBoot.service;

import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.AlunoDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.ResultData;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.AlunoEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private EntityManager em;


    public ResponseEntity gravar(AlunoDTO alunoDTO) throws Exception{
        AlunoEntity entity = new AlunoEntity();
        List<AlunoEntity> alunoCPF = alunoRepository.findByCpf(alunoDTO.getCpf());

        if (alunoCPF != null) {
            throw new Exception("dsCPF já cadastrado");
        }
        entity.setNomeAluno(alunoDTO.getNome());
        entity.setCpf(alunoDTO.getCpf());

        entity = alunoRepository.save(entity);

        ResultData resultData = new ResultData(HttpStatus.CREATED.value(), "Aluno cadastrado com sucesso", entity.getIdAluno());
        return ResponseEntity.status(HttpStatus.CREATED).body(resultData);
    }
}
