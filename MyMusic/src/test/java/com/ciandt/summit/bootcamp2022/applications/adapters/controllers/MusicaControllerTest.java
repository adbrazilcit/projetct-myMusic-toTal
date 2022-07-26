package com.ciandt.summit.bootcamp2022.applications.adapters.controllers;


import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(MusicaController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class MusicaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MusicaServicePort musicaServicePort;

    @Test
    void returnNoContentWhenFilterNotFound() throws Exception {

        MockHttpServletRequestBuilder request = get("/api/musicas?filtro=andreus");
        BDDMockito.given(musicaServicePort.findMusicByFilter("andreus")).willThrow(new NotFoundException("As informações não foram encontradas"));
        mvc.perform(request).andExpect(status().isNoContent());
    }

    @Test
    void retornaArrayQuandoEncontrarFiltro() throws Exception {

        MockHttpServletRequestBuilder request = get("/api/musicas?filtro=bruno");
        BDDMockito.given(musicaServicePort.findMusicByFilter("bruno")).willReturn(new ArrayList<MusicaDTO>());
        mvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void retornaArrayPreechidoAllQuandoPassadoSemfiltro() throws Exception {

        MockHttpServletRequestBuilder request = get("/api/musicas");
        BDDMockito.given(musicaServicePort.findAll()).willReturn(new ArrayList<MusicaDTO>());
        mvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void retornaErro400QuandoFiltroMenorQue3Caracter() throws Exception {

        MockHttpServletRequestBuilder request = get("/api/musicas?filtro=br");
        mvc.perform(request).andExpect(status().isBadRequest());
        System.out.println(mvc.perform(request).andReturn().getResponse().getStatus());
    }
}
