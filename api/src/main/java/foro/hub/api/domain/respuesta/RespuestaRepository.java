package foro.hub.api.domain.respuesta;

import foro.hub.api.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    Respuesta findFirstByTopicoOrderByFechaDesc(Topico topico);
}
