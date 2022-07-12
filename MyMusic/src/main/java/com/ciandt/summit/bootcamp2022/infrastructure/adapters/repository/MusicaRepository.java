package com.ciandt.summit.bootcamp2022.infrastructure.adapters.repository;

import com.ciandt.summit.bootcamp2022.domain.ports.repository.MusicaRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class MusicaRepository implements MusicaRepositoryPort {

    private final SpringMusicaRepository springMusicaRepository;

    public MusicaRepository(SpringMusicaRepository springMusicaRepository) {
        this.springMusicaRepository = springMusicaRepository;
    }
}
