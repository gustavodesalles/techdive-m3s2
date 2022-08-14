package org.techdive.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.techdive.dto.CursoDTO;
import org.techdive.service.CursosService;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class CursosControllerTest {

    @Mock
    private CursosService service;

    @InjectMocks
    private CursosController controller;

    @Test
    @DisplayName("Quando curso não existente, Deve inserir com sucesso")
    void inserir_sucesso() {
        CursoDTO cursoDTO = new CursoDTO("INE5411", "Organização de Computadores I", 3653);
        Response result = controller.inserir(cursoDTO);
        assertEquals(Response.Status.CREATED.getStatusCode(), result.getStatus());
        assertNotNull(result.getEntity());
        assertInstanceOf(CursoDTO.class, result.getEntity());
    }

    @Test
    @DisplayName("Quando aluno existente, Deve excluir com sucesso")
    void excluir_sucesso() {
        CursoDTO cursoDTO = new CursoDTO("INE5411", "Organização de Computadores I", 3653);
        Response result = controller.remover(cursoDTO.getCodigo());
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), result.getStatus());
        assertNotNull(result);
        assertDoesNotThrow(() -> service.excluir(cursoDTO.getCodigo()));
    }
}
