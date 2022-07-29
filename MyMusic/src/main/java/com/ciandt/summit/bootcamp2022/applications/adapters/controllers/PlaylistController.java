package com.ciandt.summit.bootcamp2022.applications.adapters.controllers;

import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.PlaylistServicePort;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;


@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistServicePort playlistServicePort;

    @PostMapping("/{playlistId}/musicas")
    @ApiOperation(value = "O endpoint adiciona uma música a playlist informanda na uri.")
    public ResponseEntity<Data> addMusic(@PathVariable("playlistId") String playlistId, @RequestBody Data<MusicaDTO> data) {

        String musicaId = data.getData().get(0).getId();

        this.playlistServicePort.addMusicInPlaylist(playlistId, musicaId);
        LOGGER.info("Adiciona uma música enviada a uma playlist.");

        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @DeleteMapping("/{playlistId}/musicas/{musicaId}")
    @ApiOperation(value = "O endpoint remove uma música da playlist informanda na uri.")
    public ResponseEntity<Data> deleteMusic(@PathVariable("playlistId") String playlistId, @PathVariable("musicaId") String musicaId) {

        this.playlistServicePort.removeMusicFromPlaylist(playlistId, musicaId);
        LOGGER.info("Remove uma música enviada de uma playlist informada.");

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
