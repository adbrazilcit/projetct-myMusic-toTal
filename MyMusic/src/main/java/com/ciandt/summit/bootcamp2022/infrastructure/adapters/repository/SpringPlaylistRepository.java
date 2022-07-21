package com.ciandt.summit.bootcamp2022.infrastructure.adapters.repository;

import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringPlaylistRepository extends JpaRepository<PlaylistEntity, String> {

    List<PlaylistEntity> findAll();
    Optional<PlaylistEntity> findById(String id);



}
