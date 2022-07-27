package com.ciandt.summit.bootcamp2022.domain.adapters.services;

import com.ciandt.summit.bootcamp2022.domain.Artista;
import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.MusicaRepositoryPort;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.repository.MusicaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.swing.*;
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

    @BeforeEach
    public void setUp(){

    }


    @Test
    @DisplayName("Deve buscar uma Lista de músicas por filtro e retornar as informações de retorno de acordo com a lista mockada")
    public  void  findMusicByIdWithRightReturn(){

        // cenário

        Musica m = new Musica("25e9839b-0d17-480c-a0ae-36a54fea2c30", new Artista("bf870f08-4fd3-4b00-98a7-3997996cd306","Metallica"), "Welcome Home (Sanitarium)");


        String filtroMusica = "25e9839b-0d17-480c-a0ae-36a54fea2c30";

        Mockito.when(repository.findMusicById(filtroMusica)).thenReturn(
                m
        );

        //execução
        MusicaDTO musicaBuscada = this.service.findMusicById(filtroMusica);


        //Verificação
        assertEquals(m.getId(),musicaBuscada.getId());
    }

    @Test
    @DisplayName("Deve buscar uma Lista de músicas por filtro e retornar as informações de retorno de acordo com a lista mockada")
    public  void  findMusicByFilterWithRightReturn(){

        // cenário

        List<Musica> m = new ArrayList<Musica>();
        m.add(new Musica("25e9839b-0d17-480c-a0ae-36a54fea2c30", new Artista("bf870f08-4fd3-4b00-98a7-3997996cd306","Metallica"), "Welcome Home (Sanitarium)"));
        m.add(new Musica("c79afa2c-f9be-47b7-b1e1-1057bac049e8", new Artista("7ada007e-c740-40ea-8229-c45e4953a8b3","Pearl Jam"), "Aye Davanita"));
        m.add(new Musica("38f89d5b-725c-4913-b404-c3fb69c06d37", new Artista("d7b1794f-6104-49d3-b742-7397012d85b8","Prince"), "Juanita"));


        String filtroMusica = "anita";
        Mockito.when(repository.findByFilter(filtroMusica)).thenReturn(
               m
        );

        //execução
        List<MusicaDTO> musicaBuscadas = this.service.findMusicByFilter(filtroMusica);


        //Verificação
        assertEquals(m.size(),musicaBuscadas.size());
    }



    @Test
    @DisplayName("Deve buscar uma música por filtro e  retorna um resultado diferente da lista mockada")
    public  void  findMusicByFilterWithWrongReturn(){

        // cenário

        List<Musica> m = new ArrayList<Musica>();
        m.add(new Musica("25e9839b-0d17-480c-a0ae-36a54fea2c30", new Artista("bf870f08-4fd3-4b00-98a7-3997996cd306","Metallica"), "Welcome Home (Sanitarium)"));
        m.add(new Musica("c79afa2c-f9be-47b7-b1e1-1057bac049e8", new Artista("7ada007e-c740-40ea-8229-c45e4953a8b3","Pearl Jam"), "Aye Davanita"));
        m.add(new Musica("38f89d5b-725c-4913-b404-c3fb69c06d37", new Artista("d7b1794f-6104-49d3-b742-7397012d85b8","Prince"), "Juanita"));


        String filtroMusica = "anita";
        Mockito.when(repository.findByFilter(filtroMusica)).thenReturn(
                m
        );

        //execução
        List<MusicaDTO> musicaBuscadas = this.service.findMusicByFilter(filtroMusica);



        //Verificação
        assertNotEquals(2,musicaBuscadas.size());
    }



    @Test
    @DisplayName("Deve lançar uma exceção porque a música buscada não foi encontrada")
    public  void  findMusicByFilterNoMatcher() throws Exception{

        // cenário
        String filtroMusica = "filtroNaoExistente";

        Mockito.when(repository.findByFilter(filtroMusica)).thenReturn(
                new ArrayList<Musica>()
        );

        try {
            //execução
            this.service.findMusicByFilter(filtroMusica);
        } catch (Throwable e) {
            //Verificação
            assertEquals("As informações não foram encontradas", e.getMessage());
            assertEquals(NotFoundException.class, e.getClass());
        }
    }


    @Test
    @DisplayName("Deve trazer todos as músicas presentes no banco")
    public  void  findAll() throws Exception{

        // cenário
        Mockito.when(repository.findAll()).thenReturn(
                new ArrayList<Musica>()
        );

        try {
            //execução
            this.service.findAll();
        } catch (Throwable e) {
            //Verificação
            assertEquals("As informações não foram encontradas", e.getMessage());
            assertEquals(NotFoundException.class, e.getClass());
        }
    }



    @Test
    @DisplayName("Deve lançar uma exceção porque a música buscada não foi encontrada")
    public  void  findAllNoMatcher() throws Exception{

        // cenário
        Mockito.when(repository.findAll()).thenReturn(
                new ArrayList<Musica>()
        );

        try {
            //execução
            this.service.findAll();
        } catch (Throwable e) {
            //Verificação
            assertEquals("As informações não foram encontradas", e.getMessage());
            assertEquals(NotFoundException.class, e.getClass());
        }
    }
}
