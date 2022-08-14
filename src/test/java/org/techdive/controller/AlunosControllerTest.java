package org.techdive.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.techdive.dto.AlunoDTO;
import org.techdive.exception.RegistroExistenteException;
import org.techdive.mapper.AlunoMapper;
import org.techdive.model.Aluno;
import org.techdive.service.AlunosService;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AlunosControllerTest {

    @Mock
    private AlunosService service;

    @InjectMocks
    private AlunosController controller;


    @Test
    @DisplayName("Quando aluno existente, Deve lançar exceção")
    void inserir_falhaExistente() {
        AlunoDTO alunoDTO = new AlunoDTO(1234, "nome do aluno");
        Aluno aluno = AlunoMapper.INSTANCE.toModel(alunoDTO);
        Mockito.doThrow(new RegistroExistenteException("Aluno", alunoDTO.getMatricula().toString())).when(service).inserir(Mockito.any(Aluno.class));
        assertThrows(RegistroExistenteException.class, () -> controller.inserir(alunoDTO));
    }

    @Test
    @DisplayName("Quando aluno não existente, Deve inserir com sucesso")
    void inserir_sucesso() {
        AlunoDTO alunoDTO = new AlunoDTO(1234, "nome do aluno");
        Response result = controller.inserir(alunoDTO);
        assertEquals(Response.Status.CREATED.getStatusCode(), result.getStatus());
        assertNotNull(result.getEntity());
        assertInstanceOf(AlunoDTO.class, result.getEntity());
    }

    @Test
    @DisplayName("Quando aluno existente, Deve excluir com sucesso")
    void excluir_sucesso() {
        AlunoDTO alunoDTO = new AlunoDTO(1234, "nome do aluno");
        Response result = controller.remover(alunoDTO.getMatricula());
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), result.getStatus());
        assertNotNull(result);
        assertDoesNotThrow(() -> service.excluir(alunoDTO.getMatricula()));
    }

    @Test
    @DisplayName("Quando aluno existente, Deve obter com sucesso")
    void obter_sucesso() {
        AlunoDTO alunoDTO = new AlunoDTO(1234, "nome do aluno");
        Response result = controller.obter(alunoDTO.getMatricula());
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        assertNotNull(result);
        assertInstanceOf(Response.class, result);
    }

}