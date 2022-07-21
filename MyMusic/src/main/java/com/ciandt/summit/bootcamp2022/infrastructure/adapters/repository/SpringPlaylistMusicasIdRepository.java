package com.ciandt.summit.bootcamp2022.infrastructure.adapters.repository;

import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.PlayListMusicasId;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.PlaylistMusicasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringPlaylistMusicasIdRepository extends JpaRepository {

    List<PlayListMusicasId> findAll();
    Optional<PlayListMusicasId> findById(PlaylistEntity playlistId);
}
