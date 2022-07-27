package com.ciandt.summit.bootcamp2022.applications.adapters.controllers;

import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.PlaylistServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    @Autowired
    private Validator validator;
    @Autowired
    private PlaylistServicePort playlistServicePort;

    @PostMapping("/{playlistId}/musicas")
    public ResponseEntity<Data> addMusic(@PathVariable("playlistId") String playlistId, @RequestBody Data<MusicaDTO> data) {

        String musicaId = data.getData().get(0).getId();

        this.playlistServicePort.addMusicInPlaylist(playlistId, musicaId);

        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @DeleteMapping("/{playlistId}/musicas/{musicaId}")
    public ResponseEntity<Data> deleteMusic(@PathVariable("playlistId") String playlistId, @PathVariable("musicaId") String musicaId) {

        this.playlistServicePort.removeMusicFromPlaylist(playlistId, musicaId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
