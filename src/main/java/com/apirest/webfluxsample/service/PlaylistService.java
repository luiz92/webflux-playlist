package com.apirest.webfluxsample.service;

import com.apirest.webfluxsample.document.Playlist;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlaylistService {
    Flux<Playlist> findAll();
    Mono<Playlist>  finById(String id);
    Mono<Playlist> save(Playlist playlist);
}
