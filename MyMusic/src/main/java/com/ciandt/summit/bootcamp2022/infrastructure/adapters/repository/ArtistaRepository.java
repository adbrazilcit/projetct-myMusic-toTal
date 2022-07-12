package com.ciandt.summit.bootcamp2022.infrastructure.adapters.repository;

import com.ciandt.summit.bootcamp2022.domain.ports.repository.ArtistaRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.MusicaRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class ArtistaRepository implements ArtistaRepositoryPort {
    private final SpringArtistaRepository springArtistaRepository;
    public ArtistaRepository(SpringArtistaRepository springArtistaRepository){
        this.springArtistaRepository = springArtistaRepository;
    }
}
