package com.ciandt.summit.bootcamp2022.infrastructure.adapters.repository;

import com.ciandt.summit.bootcamp2022.domain.PlaylistMusicas;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.PlaylistMusicasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringPlaylistMusicasEntityRepository extends JpaRepository <PlaylistMusicasEntity, String> {

    List<PlaylistMusicasEntity> findAll();
    <PlayMod extends PlaylistMusicas> PlayMod save(PlayMod playlist);
    Optional<PlaylistMusicasEntity> findById(PlaylistEntity playlistId);








}
