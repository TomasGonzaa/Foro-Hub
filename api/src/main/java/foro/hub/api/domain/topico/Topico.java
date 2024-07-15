package foro.hub.api.domain.topico;

import foro.hub.api.domain.respuesta.Respuesta;
import foro.hub.api.domain.usuarios.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Curso curso;
    private String titulo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private @NotNull Usuario usuario;
    private String mensaje;
    private LocalDateTime fecha;
    @OneToMany(mappedBy = "topico", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas = new ArrayList<>();
    private Boolean activo;


    public Topico(DatosRegistroTopico datosRegistroTopico, Usuario usuario, List<Respuesta> respuesta) {
        this.curso = datosRegistroTopico.curso();
        this.titulo = datosRegistroTopico.titulo();
        this.usuario = usuario;
        this.mensaje = datosRegistroTopico.mensaje();
        this.fecha = LocalDateTime.now();
        this.respuestas = respuesta;
        this.activo = true;
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.curso() != null){
            curso.actualizarDatos(datosActualizarTopico.curso());
        }
        if (datosActualizarTopico.titulo() != null)
        {this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null){
            this.mensaje = datosActualizarTopico.mensaje();
        }
    }

    public void desactivarTopico() {
        this.activo = false;
    }
}
