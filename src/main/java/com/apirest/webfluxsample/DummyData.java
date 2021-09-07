package com.apirest.webfluxsample;

import com.apirest.webfluxsample.document.Playlist;
import com.apirest.webfluxsample.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class DummyData implements CommandLineRunner {
    private final PlaylistRepository playlistRepository;

    @Override
    public void run(String... args){

        playlistRepository.deleteAll()
                .thenMany(
                        Flux.just("Playlist 1",
                                        "Playlist 2",
                                        "Playlist 3",
                                        "Playlist 4",
                                        "Playlist 5")
                                .map(nome -> new Playlist(UUID.randomUUID().toString(), nome))
                                .flatMap(playlistRepository::save))
                .subscribe(System.out::println);
    }
}
