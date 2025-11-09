package alice.gerenciadortarefas.repository;

import alice.gerenciadortarefas.model.Tarefa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TarefaRepositoryTest {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Test
    void testeSalvarTarefa() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Título Teste");
        tarefa.setDescricao("Descrição Teste");
        Tarefa salva = tarefaRepository.save(tarefa);
        assertNotNull(salva.getId(), "ID não deve ser nulo após salvar");
        Optional<Tarefa> encontrada = tarefaRepository.findById(salva.getId());
        assertTrue(encontrada.isPresent(), "Tarefa deve ser encontrada pelo ID");
        assertEquals("Título Teste", encontrada.get().getTitulo());
        assertEquals("Descrição Teste", encontrada.get().getDescricao());
        assertFalse(encontrada.get().getConcluida());
    }

    @Test
    void testeDeletarTarefa() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Para deletar");
        tarefa.setDescricao("Teste deletar");

        Tarefa salva = tarefaRepository.save(tarefa);
        Long id = salva.getId();

        tarefaRepository.deleteById(id);

        Optional<Tarefa> encontrada = tarefaRepository.findById(id);
        assertFalse(encontrada.isPresent(), "Tarefa não deve ser encontrada após deletar");
    }
}
