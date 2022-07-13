package com.ciandt.summit.bootcamp2022.infrastructure.configuracao;

import com.ciandt.summit.bootcamp2022.domain.adaptadores.services.MusicaServiceImp;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.MusicaRepositoryPort;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class BeanConfiguracao {

//    @Bean
//    MusicaServicePort musicaServiceMy(@Qualifier("musica")MusicaRepositoryPort musicaRepositoryPort){
//        return new MusicaServiceImp(musicaRepositoryPort);
//    }

}
