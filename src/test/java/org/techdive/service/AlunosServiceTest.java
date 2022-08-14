package org.techdive.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.techdive.dao.AlunosDAO;
import org.techdive.exception.RegistroExistenteException;
import org.techdive.exception.RegistroNaoEncontradoException;
import org.techdive.model.Aluno;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlunosServiceTest {

    @Mock
    private AlunosDAO alunosDAO;

    @InjectMocks
    private AlunosService alunosService;

    @Test
    @DisplayName("Quando aluno existente, Deve lançar exceção")
    void inserir_falhaExistente() {
        Aluno aluno = new Aluno(23, "jimmy");
        when(alunosDAO.find(anyInt())).thenReturn(Optional.of(aluno));
        assertThrows(RegistroExistenteException.class, () -> alunosService.inserir(aluno));
    }

    @Test
    @DisplayName("Quando aluno não existente, Deve inserir com sucesso")
    void inserir_sucesso() {
        Aluno aluno = new Aluno(23, "jimmy");
        when(alunosDAO.find(anyInt())).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> alunosService.inserir(aluno));
    }

    @Test
    @DisplayName("Quando aluno não existente, Deve lançar exceção")
    void alterar_falhaNaoExistente() {
        Aluno aluno = new Aluno(23, "jimmy");
        when(alunosDAO.find(anyInt())).thenReturn(Optional.empty());
        assertThrows(RegistroNaoEncontradoException.class, () -> alunosService.alterar(aluno));
    }

    @Test
    @DisplayName("Quando aluno existente, Deve alterar com sucesso")
    void alterar_sucesso() {
        Aluno aluno = new Aluno(23, "jimmy");
        when(alunosDAO.find(anyInt())).thenReturn(Optional.of(aluno));
        assertDoesNotThrow(() -> alunosService.alterar(aluno));
    }
}
