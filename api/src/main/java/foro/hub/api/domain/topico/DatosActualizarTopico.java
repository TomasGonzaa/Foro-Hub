package foro.hub.api.domain.topico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(@NotNull Long id,
                                    Curso curso,
                                    String titulo,
                                    String mensaje) {
}
