package com.apirest.webfluxsample.service;

import com.apirest.webfluxsample.document.Playlist;
import com.apirest.webfluxsample.repository.PlaylistRepository;
import com.apirest.webfluxsample.request.PlaylistRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public Flux<Playlist> findAll() {
        return playlistRepository.findAllByOrderByNome();
    }

    public Mono<Playlist> finById(String id) {
        return playlistRepository.findById(id);
    }

    public Mono<Playlist> save(PlaylistRequest playlistRequest) {
        var playlist = new Playlist(playlistRequest.getNome());
        return playlistRepository.save(playlist);
    }
}
