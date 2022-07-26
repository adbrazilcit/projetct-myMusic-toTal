package com.ciandt.summit.bootcamp2022.applications.adapters.controllers;

import com.ciandt.summit.bootcamp2022.domain.Artista;
import com.ciandt.summit.bootcamp2022.domain.Playlist;
import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.domain.exceptions.BadRequestException;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.PlaylistServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlaylistController.class)
@AutoConfigureMockMvc(addFilters = false)
class PlaylistControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlaylistServicePort playlistServicePort;

    @MockBean
    private MusicaServicePort musicaServicePort;

    @Test
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