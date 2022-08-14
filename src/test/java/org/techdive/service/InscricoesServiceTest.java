package org.techdive.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.techdive.dao.InscricoesDAO;
import org.techdive.exception.RegistroExistenteException;
import org.techdive.model.Aluno;
import org.techdive.model.Curso;
import org.techdive.model.Inscricao;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InscricoesServiceTest {

    @Mock
    private InscricoesDAO inscricoesDAO;

    @InjectMocks
    private InscricoesService inscricoesService;

    @Test
    @DisplayName("Quando curso não existente, Deve inserir com sucesso")
    void inserir_sucesso() {
        Aluno aluno = new Aluno(23, "jimmy");
        Curso curso = new Curso("INE5411", "Organização de Computadores I", 3653);
        Inscricao inscricao = new Inscricao(8, aluno, curso);
        Mockito.lenient().when(inscricoesDAO.find(anyInt())).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> inscricoesService.inserir(inscricao));
    }

    @Test
    @DisplayName("Quando curso existente, Deve excluir com sucesso")
    void excluir_sucesso() {
        Aluno aluno = new Aluno(23, "jimmy");
        Curso curso = new Curso("INE5411", "Organização de Computadores I", 3653);
        Inscricao inscricao = new Inscricao(8, aluno, curso);
        Mockito.lenient().when(inscricoesDAO.find(anyInt())).thenReturn(Optional.of(inscricao));
        assertDoesNotThrow(() -> inscricoesService.excluir(inscricao.getId()));
    }
}
