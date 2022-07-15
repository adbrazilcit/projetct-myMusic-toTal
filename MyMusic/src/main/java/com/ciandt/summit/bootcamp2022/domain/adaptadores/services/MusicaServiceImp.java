package com.ciandt.summit.bootcamp2022.domain.adaptadores.services;

import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.MusicaRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicaServiceImp implements MusicaServicePort {

    @Autowired
    private  MusicaRepositoryPort musicaRepositoryPort;

    @Override
    public List<MusicaDTO> findMusicByFilter(String filtro) {
        List<Musica> musicas = this.musicaRepositoryPort.findByFilter(filtro, filtro);

        if(musicas.isEmpty())
            throw  new NotFoundException("As informações não foram encontradas");

        Comparator<MusicaDTO> comparatorNomeArtista = (p1, p2) -> p1.getArtista().getNome().compareTo(p2.getArtista().getNome());
        Comparator<MusicaDTO> comparatorNomeMusica =  (a1, a2) -> a1.getNome().compareTo(a2.getNome());

        return  musicas.stream().map(
                musica -> new MusicaDTO( musica.getId(), musica.getNome(), musica.getArtistaId())).sorted(comparatorNomeArtista.thenComparing(comparatorNomeMusica)).collect(Collectors.toList());
    }

    @Override
    public List<MusicaDTO> findAll() {
        List<Musica> musicas = this.musicaRepositoryPort.findAll();

        if(musicas.isEmpty())
            throw  new NotFoundException("As informações não foram encontradas");

        Comparator<MusicaDTO> comparatorNomeArtista = (p1, p2) -> p1.getArtista().getNome().compareTo(p2.getArtista().getNome());
        Comparator<MusicaDTO> comparatorNomeMusica =  (a1, a2) -> a1.getNome().compareTo(a2.getNome());

        return  musicas.stream().map(
                musica -> new MusicaDTO( musica.getId(), musica.getNome(), musica.getArtistaId())).sorted(comparatorNomeArtista.thenComparing(comparatorNomeMusica)).collect(Collectors.toList());
    }
}
