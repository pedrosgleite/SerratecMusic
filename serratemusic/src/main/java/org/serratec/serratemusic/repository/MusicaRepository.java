package org.serratec.serratemusic.repository;

import org.serratec.serratemusic.domain.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long>{

}
