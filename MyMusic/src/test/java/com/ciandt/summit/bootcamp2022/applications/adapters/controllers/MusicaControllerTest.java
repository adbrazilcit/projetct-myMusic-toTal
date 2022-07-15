package com.ciandt.summit.bootcamp2022.applications.adapters.controllers;


import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(MusicaController.class)
public class MusicaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MusicaServicePort musicaServicePort;

    @Test
    void retornaNoContentQuandoNaoEncontrarFiltro() throws Exception {

        MultiValueMap<String, String> par = new LinkedMultiValueMap<>();

        MockHttpServletRequestBuilder request = get("/api/musicas?filtro=andreus");

       mvc.perform(request).andExpect(status().isNoContent());

    }
    @Test
    void retornaArrayQuandoEncontrarFiltro() throws Exception {
        MultiValueMap<String, String> par = new LinkedMultiValueMap<>();
//        par.add("filtro", "bru");

        MockHttpServletRequestBuilder request = get("/api/musicas?filtro=br");

        System.out.println(mvc.perform(request).andReturn().getResponse().getStatus());
    }


}
