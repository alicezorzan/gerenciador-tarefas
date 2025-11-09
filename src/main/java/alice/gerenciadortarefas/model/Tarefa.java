package alice.gerenciadortarefas.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "tarefa")
public class Tarefa {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único da tarefa", example = "1")
    private Long id;
    @Getter
    @Schema(description = "Título da tarefa", example = "Comprar pão")
    private String titulo;
    @Getter
    @Schema(description = "Descrição detalhada da tarefa", example = "Ir à padaria comprar pão integral")
    private String descricao;
    @Schema(description = "Indica se a tarefa foi concluída", example = "false")
    private boolean concluida = false;

    public void setId(Long id) { this.id = id; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public boolean getConcluida() { return concluida; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }
}
