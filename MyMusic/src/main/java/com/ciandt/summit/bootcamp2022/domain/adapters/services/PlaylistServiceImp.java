package com.ciandt.summit.bootcamp2022.domain.adapters.services;

import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.Playlist;
import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.PlaylistServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.MusicaRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.PlaylistRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistServiceImp implements PlaylistServicePort {

    @Autowired
    private PlaylistRepositoryPort playlistRepositoryPort;

    @Autowired
    private MusicaRepositoryPort musicaRepositoryPort;

    @Autowired
    private MusicaServicePort musicaServicePort;

    @Override
    public List<Playlist> findAll() {
        List<Playlist> playlists = this.playlistRepositoryPort.findAll();

        if (playlists.isEmpty())
            throw new NotFoundException("As informações não foram encontradas");

        return playlists;
    }

    @Override
    public Playlist findPlaylistById(String param) {
        return this.playlistRepositoryPort.findById(param);
    }

    @Override
    public void addMusicInPlaylist(String playlistId, String musicaId) {
        Musica musica = musicaServicePort.findMusicById(musicaId).toMusic();
        Playlist playlist = playlistRepositoryPort.findById(playlistId);

        if (playlist == null) {
            throw new NotFoundException("Playlist não encontrada!");
        } else if (musica == null) {
            throw new NotFoundException("Música não encontrada!");
        }

        playlistRepositoryPort.save(playlistId, musicaId);
    }

    @Override
    public void removeMusicFromPlaylist(String playlistId, String musicaId) {
        Musica musica = musicaServicePort.findMusicById(musicaId).toMusic();
        Playlist playlist = playlistRepositoryPort.findById(playlistId);


        if (playlist == null) {
            throw new NotFoundException("Playlist não encontrada!");
        } else if (musica == null) {
            throw new NotFoundException("Música não encontrada!");
        }

        playlistRepositoryPort.delete(playlistId, musicaId);

    }

}
