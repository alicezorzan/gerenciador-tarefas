package alice.gerenciadortarefas.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TarefaTest {

    @Test
    void testeGettersAndSetters() {
        Tarefa tarefa = new Tarefa();

        tarefa.setId(1L);
        assertEquals(1L, tarefa.getId());

        tarefa.setTitulo("Título de teste");
        assertEquals("Título de teste", tarefa.getTitulo());

        tarefa.setDescricao("Descrição de teste");
        assertEquals("Descrição de teste", tarefa.getDescricao());

        assertFalse(tarefa.getConcluida());
        tarefa.setConcluida(true);
        assertTrue(tarefa.getConcluida());
    }
}
