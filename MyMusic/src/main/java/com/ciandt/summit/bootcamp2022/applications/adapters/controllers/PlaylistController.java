package com.ciandt.summit.bootcamp2022.applications.adapters.controllers;

import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.PlaylistServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistServicePort playlistServicePort;

    @PostMapping("/{playlistId}/musicas")
    public ResponseEntity<Data> addMusica(@RequestParam("playlistId") String playlistId, @RequestParam Data data) {

        this.playlistServicePort.addMusicInPlaylist(playlistId, data.getData().get(0).getId());

        return ResponseEntity.ok(data);
    }
}
