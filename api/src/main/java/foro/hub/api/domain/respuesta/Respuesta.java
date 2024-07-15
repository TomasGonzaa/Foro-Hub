package foro.hub.api.domain.respuesta;

import com.fasterxml.jackson.annotation.JsonInclude;
import foro.hub.api.domain.topico.Topico;
import foro.hub.api.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mensaje;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id",nullable = false)
    private Topico topico;
    private LocalDateTime fecha;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;


    @Override
    public String toString() {
        return  "mensaje: " + mensaje +
                "\nusuario: " + usuario +
                "\nfecha: " + fecha;
    }
}
