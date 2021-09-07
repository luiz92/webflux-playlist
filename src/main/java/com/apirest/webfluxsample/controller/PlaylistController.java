package com.apirest.webfluxsample.controller;

import com.apirest.webfluxsample.document.Playlist;
import com.apirest.webfluxsample.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RequiredArgsConstructor
@RestController
public class PlaylistController {
    private final PlaylistService playlistService;

    @GetMapping(value = "/playlist")
    public Flux<Playlist> buscarTodos(){
        return playlistService.findAll();
    }

    @GetMapping(value = "/playlist/{id}")
    public Mono<Playlist> buscarPorId(@PathVariable String id){
        return playlistService.finById(id);
    }

    @GetMapping(value = "/playlist/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Playlist>> buscarTodosPorIntervalo(){
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(3));
        Flux<Playlist> events = playlistService.findAll();
        return Flux.zip(interval, events);
    }

    @PostMapping(value = "/playlist")
    public Mono<Playlist> save(@RequestBody Playlist playlist){
        return playlistService.save(playlist);
    }
}
