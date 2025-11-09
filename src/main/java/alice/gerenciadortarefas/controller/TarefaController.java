package alice.gerenciadortarefas.controller;

import alice.gerenciadortarefas.model.Tarefa;
import alice.gerenciadortarefas.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@Tag(name = "Tarefas", description = "Painel de gerenciamento de tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    @Operation(summary = "Lista todas as tarefas", description = "Retorna uma lista completa de tarefas cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<List<Tarefa>> listarTodas() {
        return ResponseEntity.ok(tarefaService.listarTodas());
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria uma nova tarefa", description = "Recebe um objeto Tarefa e salva na sessão")
    public ResponseEntity<Tarefa> criar(@RequestBody Tarefa tarefa) {
        Tarefa nova = tarefaService.salvar(tarefa);
        return ResponseEntity.ok(nova);
    }

    @PutMapping("/{id}/concluir")
    @Operation(
            summary = "Conclui uma tarefa",
            description = "Marca a tarefa como concluída pelo ID informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa concluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    public ResponseEntity<Tarefa> concluir(@PathVariable Long id) {
        return tarefaService.buscarPorId(id)
                .map(tarefa -> {
                    tarefa.setConcluida(true);
                    Tarefa atualizada = tarefaService.salvar(tarefa);
                    return ResponseEntity.ok(atualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/remover")
    @Operation(
            summary = "Remove uma tarefa",
            description = "Remove a tarefa pelo ID informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarefa removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
