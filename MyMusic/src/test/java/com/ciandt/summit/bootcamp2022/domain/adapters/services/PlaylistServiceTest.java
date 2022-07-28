package com.ciandt.summit.bootcamp2022.domain.adapters.services;


import com.ciandt.summit.bootcamp2022.domain.Artista;
import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.Playlist;
import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.PlaylistServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.MusicaRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.PlaylistRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class PlaylistServiceTest {
    @Autowired
    private PlaylistServicePort servicePlaylist;

    @MockBean
    private PlaylistRepositoryPort repositoryPlaylist;



    @Test
    @DisplayName("Deleta a música da playlist")
    public  void  deleteMusicWhenMusicAndPlaylistIsValid(){
        Playlist playlistMock = new Playlist();

        Mockito.when(repositoryPlaylist.findById("654bbc71-3a9c-4434-a95d-4208f713e586")).thenReturn(
                playlistMock
        );

        this.servicePlaylist.removeMusicFromPlaylist("654bbc71-3a9c-4434-a95d-4208f713e586","4aa583c9-40ee-4dea-927d-006637a1efcf");

        Mockito.verify(repositoryPlaylist,Mockito.times(1)).delete("654bbc71-3a9c-4434-a95d-4208f713e586","4aa583c9-40ee-4dea-927d-006637a1efcf");
    }


    @Test
    @DisplayName("Ao tentar deletar deve lançar a  exceção porque a playlist é inválida")
    public  void  ThrowExceptionWhenPlaylistIsInvalid(){

        Playlist playlistMock = null;

        Mockito.when(repositoryPlaylist.findById("654bbc71-3a9c-4434-a95d-4208f713e586")).thenReturn(
                playlistMock
        );


        try {
            this.servicePlaylist.removeMusicFromPlaylist("654bbc71-3a9c-4434-a95d-4208f713e586", "4aa583c9-40ee-4dea-927d-006637a1efcf");
        }catch (Throwable e){
            assertEquals("Playlist não encontrada!", e.getMessage());
            assertEquals(NotFoundException.class, e.getClass());
        }

    }

}
