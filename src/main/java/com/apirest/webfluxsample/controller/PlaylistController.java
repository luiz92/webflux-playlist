package com.apirest.webfluxsample.controller;

import com.apirest.webfluxsample.document.Playlist;
import com.apirest.webfluxsample.request.PlaylistRequest;
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
@RequestMapping("/playlist")
public class PlaylistController {
    private final PlaylistService playlistService;

    @GetMapping(value = "/buscarTodos", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Playlist> buscarTodos (){
        return playlistService.findAll();
    }

    @GetMapping("/buscarPorId/{id}")
    public Mono<Playlist> buscarPorId (@PathVariable String id){
        return playlistService.finById(id);
    }

    @GetMapping("/buscarPorNome/{nome}")
    public Mono<Playlist> buscarPorNome (@PathVariable String nome){
        return playlistService.finByIdNome(nome);
    }

    @GetMapping(value = "/buscarTodos/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Playlist>> buscarTodosPorIntervalo (){
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(3));
        Flux<Playlist> events = playlistService.findAll();
        return Flux.zip(interval, events);
    }

    @PostMapping("/adicionar")
    public Mono<Playlist> adicionar (@RequestBody PlaylistRequest playlist){
        return playlistService.save(playlist);
    }
}
