package com.apirest.webfluxsample.repository;

import com.apirest.webfluxsample.document.Playlist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PlaylistRepository extends ReactiveMongoRepository<Playlist, String> {
    Flux<Playlist> findAllByOrderByNome();
    Mono<Playlist> findByNome(String nome);
}
