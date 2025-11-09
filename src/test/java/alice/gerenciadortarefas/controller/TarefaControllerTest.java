package alice.gerenciadortarefas.controller;

import alice.gerenciadortarefas.model.Tarefa;
import alice.gerenciadortarefas.service.TarefaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TarefaControllerTest {

    @Mock
    private TarefaService tarefaService;

    @InjectMocks
    private TarefaController tarefaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testeListarTodasTarefas() {
        Tarefa t1 = new Tarefa();
        t1.setId(1L);
        t1.setTitulo("Tarefa 1");
        Tarefa t2 = new Tarefa();
        t2.setId(2L);
        t2.setTitulo("Tarefa 2");

        when(tarefaService.listarTodas()).thenReturn(Arrays.asList(t1, t2));

        ResponseEntity<List<Tarefa>> response = tarefaController.listarTodas();

        assertNotNull(response);
        assertEquals(2, response.getBody().size());
        verify(tarefaService, times(1)).listarTodas();
    }

    @Test
    void testeCriarTarefa() {
        Tarefa t = new Tarefa();
        t.setTitulo("Nova Tarefa");

        when(tarefaService.salvar(ArgumentMatchers.any(Tarefa.class))).thenReturn(t);

        ResponseEntity<Tarefa> response = tarefaController.criar(t);

        assertNotNull(response);
        assertEquals("Nova Tarefa", response.getBody().getTitulo());
        verify(tarefaService, times(1)).salvar(t);
    }

    @Test
    void testeConcluirTarefa_Sucesso() {
        Tarefa t = new Tarefa();
        t.setId(1L);
        t.setConcluida(false);

        when(tarefaService.buscarPorId(1L)).thenReturn(Optional.of(t));
        when(tarefaService.salvar(t)).thenReturn(t);

        ResponseEntity<Tarefa> response = tarefaController.concluir(1L);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getConcluida());
        verify(tarefaService, times(1)).buscarPorId(1L);
        verify(tarefaService, times(1)).salvar(t);
    }

    @Test
    void testeConcluirTarefa_NaoEncontada() {
        when(tarefaService.buscarPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Tarefa> response = tarefaController.concluir(1L);

        assertEquals(404, response.getStatusCodeValue());
        verify(tarefaService, times(1)).buscarPorId(1L);
        verify(tarefaService, never()).salvar(any());
    }

    @Test
    void testeRemoverTarefa() {
        doNothing().when(tarefaService).deletar(1L);

        ResponseEntity<Void> response = tarefaController.remover(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(tarefaService, times(1)).deletar(1L);
    }
}
