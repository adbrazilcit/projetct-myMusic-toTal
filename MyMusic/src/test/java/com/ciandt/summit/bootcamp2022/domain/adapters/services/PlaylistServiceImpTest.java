package com.ciandt.summit.bootcamp2022.domain.adapters.services;

import com.ciandt.summit.bootcamp2022.domain.Artista;
import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.Playlist;
import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.PlaylistServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.PlaylistRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class PlaylistServiceImpTest {

    @Autowired
    private PlaylistServicePort service;

    @MockBean
    private PlaylistRepositoryPort repository;

    @MockBean
    private MusicaServicePort musicaServicePort;

    public Musica createMusica() {
        Musica musica = new Musica();
        musica.setId("25e9839b-0d17-480c-a0ae-36a54fea2c30");
        musica.setNome("Welcome Home (Sanitarium)");

        Artista artista = new Artista();
        artista.setId("bf870f08-4fd3-4b00-98a7-3997996cd306");
        artista.setNome("Metallica");

        musica.setArtistaId(artista);

        return musica;
    }

    @Test
    @DisplayName("Deve retornar todas as músicas")
    void shouldFindAll() {
        Playlist playlist = new Playlist();
        playlist.setId("1");

        ArrayList<Playlist> playlists = new ArrayList<>();
        playlists.add(playlist);

        when(repository.findAll()).thenReturn(playlists);

        this.service.findAll();

        assertThat(playlists).isNotNull();
        assertThat(playlists).isNotEmpty();
    }

    @Test
    @DisplayName("Deve retornar NotFoundException quando não encontrar playlist")
    void shouldThrowNotFoundWhenPlaylistIsNull() {
        Playlist playlist = new Playlist();
        playlist.setId("12");

        Musica musica = createMusica();

        when(musicaServicePort.findMusicById(musica.getId())).thenReturn(musica.toMusicaDTO());

        Throwable exception = assertThrows(NotFoundException.class, () -> this.service.addMusicInPlaylist(playlist.getId(), musica.getId()));

        assertThat(exception.getMessage()).isEqualTo("Playlist não encontrada!");
    }

    @Test
    @DisplayName("Deve retornar NotFoundException quando musica estiver vazia")
    void shouldThrowNotFoundIfPlaylistIsEmpty() {
        Playlist playlist = new Playlist();
        playlist.setId(" ");

        Throwable exception = assertThrows(NotFoundException.class, () -> this.service.findAll());

        assertThat(exception.getMessage()).isEqualTo("As informações não foram encontradas");
    }

    @Test
    @DisplayName("Deve adicionar uma musica na playlist")
    void shouldAddMusicinPlaylist() {
        Musica musica = createMusica();

        Playlist playlist = new Playlist();
        playlist.setId("a39926f4-6acb-4497-884f-d4e5296ef652");

        when(musicaServicePort.findMusicById(musica.getId())).thenReturn(musica.toMusicaDTO());
        when(repository.findById(playlist.getId())).thenReturn(playlist);

        willDoNothing().given(repository).save(playlist.getId(), musica.getId());
        this.service.addMusicInPlaylist(playlist.getId(), musica.getId());

        assertThat(playlist.getId()).isNotEmpty();
    }

    @Test
    @DisplayName("Deve buscar uma playlist por id")
    void shouldFindById() {
        Playlist playlist = new Playlist();
        playlist.setId("a39926f4-6acb-4497-884f-d4e5296ef65");

        when(repository.findById(playlist.getId())).thenReturn(playlist);

        this.service.findPlaylistById(playlist.getId());

        assertThat(playlist.getId()).isNotEmpty();
    }


    @Test
    @DisplayName("Deleta a música da playlist")
    public  void  deleteMusicWhenMusicAndPlaylistIsValid(){
        Playlist playlistMock = new Playlist();

        Mockito.when(repository.findById("654bbc71-3a9c-4434-a95d-4208f713e586")).thenReturn(
                playlistMock
        );

        this.service.removeMusicFromPlaylist("654bbc71-3a9c-4434-a95d-4208f713e586","4aa583c9-40ee-4dea-927d-006637a1efcf");

        Mockito.verify(repository,Mockito.times(1)).delete("654bbc71-3a9c-4434-a95d-4208f713e586","4aa583c9-40ee-4dea-927d-006637a1efcf");
    }


    @Test
    @DisplayName("Ao tentar deletar deve lançar a  exceção porque a playlist é inválida")
    public  void  ThrowExceptionWhenPlaylistIsInvalid(){

        Playlist playlistMock = null;

        Mockito.when(repository.findById("654bbc71-3a9c-4434-a95d-4208f713e586")).thenReturn(
                playlistMock
        );

        try {
            this.service.removeMusicFromPlaylist("654bbc71-3a9c-4434-a95d-4208f713e586", "4aa583c9-40ee-4dea-927d-006637a1efcf");
        }catch (Throwable e){
            assertEquals("Playlist não encontrada!", e.getMessage());
            assertEquals(NotFoundException.class, e.getClass());
        }

    }
}