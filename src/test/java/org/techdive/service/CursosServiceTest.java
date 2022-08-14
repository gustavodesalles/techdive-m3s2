package org.techdive.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.techdive.dao.CursosDAO;
import org.techdive.exception.RegistroExistenteException;
import org.techdive.exception.RegistroNaoEncontradoException;
import org.techdive.model.Curso;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CursosServiceTest {

    @Mock
    private CursosDAO cursosDAO;

    @InjectMocks
    private CursosService cursosService;

    @Test
    @DisplayName("Quando curso existente, Deve lançar exceção")
    void inserir_falhaExistente() {
        Curso curso = new Curso("INE5411", "Organização de Computadores I", 3653);
        when(cursosDAO.find(anyString())).thenReturn(Optional.of(curso));
        assertThrows(RegistroExistenteException.class, () -> cursosService.inserir(curso));
    }

    @Test
    @DisplayName("Quando curso não existente, Deve inserir com sucesso")
    void inserir_sucesso() {
        Curso curso = new Curso("INE5411", "Organização de Computadores I", 3653);
        when(cursosDAO.find(anyString())).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> cursosService.inserir(curso));
    }

    @Test
    @DisplayName("Quando curso não existente, Deve lançar exceção")
    void alterar_falhaNaoExistente() {
        Curso curso = new Curso("INE5411", "Organização de Computadores I", 3653);
        when(cursosDAO.find(anyString())).thenReturn(Optional.empty());
        assertThrows(RegistroNaoEncontradoException.class, () -> cursosService.alterar(curso));
    }

    @Test
    @DisplayName("Quando curso existente, Deve alterar com sucesso")
    void alterar_sucesso() {
        Curso curso = new Curso("INE5411", "Organização de Computadores I", 3653);
        when(cursosDAO.find(anyString())).thenReturn(Optional.of(curso));
        assertDoesNotThrow(() -> cursosService.alterar(curso));
    }
}
