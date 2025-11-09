package alice.gerenciadortarefas.service;

import alice.gerenciadortarefas.model.Tarefa;
import alice.gerenciadortarefas.repository.TarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TarefaServiceTest {

    private TarefaRepository tarefaRepository;
    private TarefaService tarefaService;

    @BeforeEach
    void setUp() {
        tarefaRepository = mock(TarefaRepository.class);
        tarefaService = new TarefaService(tarefaRepository);
    }

    @Test
    void testeListarTodasTarefas() {
        Tarefa t1 = new Tarefa();
        t1.setTitulo("Tarefa 1");
        Tarefa t2 = new Tarefa();
        t2.setTitulo("Tarefa 2");

        when(tarefaRepository.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<Tarefa> todas = tarefaService.listarTodas();

        assertEquals(2, todas.size());
        assertEquals("Tarefa 1", todas.get(0).getTitulo());
        assertEquals("Tarefa 2", todas.get(1).getTitulo());
    }

    @Test
    void testeBuscarTarefaPorId() {
        Tarefa t = new Tarefa();
        t.setId(1L);
        t.setTitulo("Tarefa Teste");

        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(t));

        Optional<Tarefa> resultado = tarefaService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Tarefa Teste", resultado.get().getTitulo());
    }

    @Test
    void testeSalvarTarefa() {
        Tarefa t = new Tarefa();
        t.setTitulo("Nova Tarefa");

        when(tarefaRepository.save(t)).thenReturn(t);

        Tarefa salva = tarefaService.salvar(t);

        assertEquals("Nova Tarefa", salva.getTitulo());
        verify(tarefaRepository, times(1)).save(t);
    }

    @Test
    void testeDeletarTarefa() {
        Long id = 1L;
        doNothing().when(tarefaRepository).deleteById(id);

        tarefaService.deletar(id);

        verify(tarefaRepository, times(1)).deleteById(id);
    }
}
