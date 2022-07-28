package com.ciandt.summit.bootcamp2022.domain.adapters.services;

import com.ciandt.summit.bootcamp2022.domain.Artista;
import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.MusicaRepositoryPort;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class MusicaServiceImpTest {
    @Autowired
    private MusicaServicePort service;

    @MockBean
    private MusicaRepositoryPort repository;

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
    @DisplayName("Deve buscar uma Lista de músicas por filtro e retornar as informações de retorno de acordo com a lista mockada")
    public void findMusicByIdWithRightReturn() {

        Musica musica = createMusica();

        String filtroMusica = "25e9839b-0d17-480c-a0ae-36a54fea2c30";

        Mockito.when(repository.findMusicById(filtroMusica)).thenReturn(
                musica
        );

        MusicaDTO musicaBuscada = this.service.findMusicById(filtroMusica);

        assertEquals(musica.getId(), musicaBuscada.getId());
    }

    @Test
    @DisplayName("Deve buscar uma Lista de músicas por filtro e retornar as informações de retorno de acordo com a lista mockada")
    public void findMusicByFilterWithRightReturn() {
        List<Musica> musica = new ArrayList<>();
        musica.add(createMusica());

        String filtroMusica = "anita";

        Mockito.when(repository.findByFilter(filtroMusica)).thenReturn(
                musica
        );

        List<MusicaDTO> musicaBuscadas = this.service.findMusicByFilter(filtroMusica);

        assertEquals(musica.size(), musicaBuscadas.size());
    }

    @Test
    @DisplayName("Deve buscar uma música por filtro e  retorna um resultado diferente da lista mockada")
    public void findMusicByFilterWithWrongReturn() {
        List<Musica> musicas = new ArrayList<>();
        musicas.add(createMusica());

        String filtroMusica = "anita";

        Mockito.when(repository.findByFilter(filtroMusica)).thenReturn(
                musicas
        );

        List<MusicaDTO> musicaBuscadas = this.service.findMusicByFilter(filtroMusica);

        assertNotEquals(2, musicaBuscadas.size());
    }


    @Test
    @DisplayName("Deve lançar uma exceção quando a música buscada não for encontrada")
    public void findMusicByFilterNoMatcher() {

        String filtroMusica = "filtroNaoExistente";

        Mockito.when(repository.findByFilter(filtroMusica)).thenReturn(
                new ArrayList<>()
        );

        try {
            this.service.findMusicByFilter(filtroMusica);
        } catch (Throwable e) {
            assertEquals("As informações não foram encontradas", e.getMessage());
            assertEquals(NotFoundException.class, e.getClass());
        }
    }


    @Test
    @DisplayName("Deve trazer todas músicas presentes no banco")
    public void findAll() {

        List<Musica> musicasLista = new ArrayList<>();
        musicasLista.add(createMusica());

        Mockito.when(repository.findAll()).thenReturn(
                musicasLista
        );

        List<MusicaDTO> musicasDTO = this.service.findAll();

        assertEquals(musicasLista.size(), musicasDTO.size());
        assertEquals(musicasLista.get(0).getId(), musicasDTO.get(0).getId());

        try {
            this.service.findAll();
        } catch (Throwable e) {
            assertEquals("As informações não foram encontradas", e.getMessage());
            assertEquals(NotFoundException.class, e.getClass());
        }
    }

    @Test
    @DisplayName("Deve lançar uma exceção quando a música buscada não for encontrada")
    public void findAllNoMatcher() {

        Mockito.when(repository.findAll()).thenReturn(
                new ArrayList<>()
        );

        try {
            this.service.findAll();
        } catch (Throwable e) {
            assertEquals("As informações não foram encontradas", e.getMessage());
            assertEquals(NotFoundException.class, e.getClass());
        }
    }
}
