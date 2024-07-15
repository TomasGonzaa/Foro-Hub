package foro.hub.api.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

    Page<Topico> findAll(Pageable pageable);

    Page<Topico> findByActivoTrue(Pageable pageable);
}
