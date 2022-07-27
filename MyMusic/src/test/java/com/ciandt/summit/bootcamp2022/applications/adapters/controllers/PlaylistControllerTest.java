package com.ciandt.summit.bootcamp2022.applications.adapters.controllers;

import com.ciandt.summit.bootcamp2022.domain.Artista;
import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.Playlist;
import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.domain.exceptions.BadRequestException;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.PlaylistServicePort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlaylistController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class PlaylistControllerTest {

    static final String PLAYLIST_API = "/api/playlists";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MusicaServicePort musicaServicePort;

    @MockBean
    private PlaylistServicePort playlistServicePort;

    public Musica createMusica() {
        Musica musica = new Musica();
        musica.setId("12659604-a4a1-4c4c-8a5f-29fff1ad2ac5");
        musica.setNome("24K Magic");

        Artista artista = new Artista();
        artista.setId("88ac7b00-9489-49ae-a5b1-79d3ba7fc2e6");
        artista.setNome("Bruno Mars");

        musica.setArtistaId(artista);

        return musica;
    }

    @Test
    @DisplayName("Deve retornar adicionar uma playlist")
    void shouldAddMusicToPlaylist() throws Exception {
        Musica musica = createMusica();

        Data<MusicaDTO> data = new Data<>();

        String playlistId = "12659604-a4a1-4c4c-8a5f-29fff1ad2ac5";

        data.setMusicaDTOS(List.of(musica.toMusicaDTO()));

        BDDMockito.given(playlistServicePort.findPlaylistById(playlistId)).willReturn(new Playlist());

        BDDMockito.given(musicaServicePort.findMusicById(musica.getId())).willReturn(musica.toMusicaDTO());

        BDDMockito.willDoNothing().given(this.playlistServicePort).addMusicInPlaylist(playlistId, musica.getId());

        String json = new ObjectMapper().writeValueAsString(data);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(PLAYLIST_API.concat("/" + playlistId + "/musicas"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve retornar bad request ao adicionar uma musica a uma playlist inexistente")
    void shouldReturnBadRequestWhenPlaylistNoExists() throws Exception {

        Musica musica = createMusica();

        Data<MusicaDTO> data = new Data<>();

        data.setMusicaDTOS(List.of(musica.toMusicaDTO()));

        BDDMockito.given(playlistServicePort.findPlaylistById(any(String.class))).willReturn(new Playlist());

        BDDMockito.given(musicaServicePort.findMusicById(musica.getId())).willReturn(musica.toMusicaDTO());

        willThrow(new BadRequestException("Playlist não encontrada!")).given(this.playlistServicePort).addMusicInPlaylist(any(String.class), any(String.class));

        String json = new ObjectMapper().writeValueAsString(data);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(PLAYLIST_API.concat("/7/musicas"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve remover uma musica de uma playlist")
    void ShouldRemoveAMusicFromPlaylist() throws Exception {
        BDDMockito
                .given(playlistServicePort.findPlaylistById("a39926f4-6acb-4497-884f-d4e5296ef652"))
                .willReturn(new Playlist("a39926f4-6acb-4497-884f-d4e5296ef652"));
        BDDMockito
                .given(musicaServicePort.findMusicById("c79afa2c-f9be-47b7-b1e1-1057bac049e8"))
                .willReturn(new MusicaDTO("c79afa2c-f9be-47b7-b1e1-1057bac049e8", "Aye Davanita", new Artista("7ada007e-c740-40ea-8229-c45e4953a8b3", "Pearl Jam")));

        MockHttpServletRequestBuilder request = delete("/api/playlists/a39926f4-6acb-4497-884f-d4e5296ef652/musicas/c79afa2c-f9be-47b7-b1e1-1057bac049e8");
        mvc.perform(request).andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar bad request ao remover uma musica de uma playlist inexistente")
    void ShouldReturnA400IfPlaylistNoExists() throws Exception {
        BDDMockito
                .given(playlistServicePort.findPlaylistById("15151515151515115"))
                .willThrow(new BadRequestException("Playlist não encontrada!"));

        BDDMockito
                .given(musicaServicePort.findMusicById("c79afa2c-f9be-47b7-b1e1-1057bac049e8"))
                .willReturn(new MusicaDTO("c79afa2c-f9be-47b7-b1e1-1057bac049e8", "Aye Davanita", new Artista("7ada007e-c740-40ea-8229-c45e4953a8b3", "Pearl Jam")));

        MockHttpServletRequestBuilder request = delete("/api/playlists/15151515151515115/musicas/c79afa2c-f9be-47b7-b1e1-1057bac049e8");
        mvc.perform(request).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar bad request ao remover uma musica que não existe")
    void ShouldReturnA400IfMusicNoExists() throws Exception {
        BDDMockito
                .given(playlistServicePort.findPlaylistById("a39926f4-6acb-4497-884f-d4e5296ef652"))
                .willReturn(new Playlist("a39926f4-6acb-4497-884f-d4e5296ef652"));
        BDDMockito
                .given(musicaServicePort.findMusicById("c84848484848"))
                .willThrow(new BadRequestException("Música não existe no banco de dados"));

        MockHttpServletRequestBuilder request = delete("/api/playlists/a39926f4-6acb-4497-884f-d4e5296ef652/musicas/c84848484848");
        mvc.perform(request).andExpect(status().isBadRequest());
    }
}