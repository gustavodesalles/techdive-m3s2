package org.techdive.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.techdive.dto.InscricaoReqDTO;
import org.techdive.dto.InscricaoRespDTO;
import org.techdive.mapper.InscricaoMapper;
import org.techdive.model.Aluno;
import org.techdive.model.Curso;
import org.techdive.model.Inscricao;
import org.techdive.service.InscricoesService;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class InscricoesControllerTest {

    @Mock
    private InscricoesService service;

    @InjectMocks
    private InscricoesController controller;

    @Test
    @DisplayName("Quando inscrição não existente, Deve inserir com sucesso")
    void inserir_sucesso() {
        Aluno aluno = new Aluno(23, "jimmy");
        Curso curso = new Curso("INE5411", "Organização de Computadores I", 3653);
        Inscricao inscricao = new Inscricao(8, aluno, curso);
        InscricaoReqDTO inscricaoReqDTO = InscricaoMapper.INSTANCE.toRequest(inscricao);
        Response result = controller.inserir(inscricaoReqDTO);
        assertEquals(Response.Status.CREATED.getStatusCode(), result.getStatus());
        assertNotNull(result.getEntity());
        assertInstanceOf(InscricaoRespDTO.class, result.getEntity());
    }
}
