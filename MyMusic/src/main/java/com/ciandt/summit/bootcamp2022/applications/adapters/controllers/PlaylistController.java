package com.ciandt.summit.bootcamp2022.applications.adapters.controllers;

import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.PlaylistServicePort;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistServicePort playlistServicePort;

    @PostMapping("/{playlistId}/musicas")
    public Data addMusica(@PathVariable("playlistId") String playlistId, @RequestBody Data data) {

        this.playlistServicePort.addMusicInPlaylist(playlistId, data.getData().get(0).getId());

        return data;
    }
}
