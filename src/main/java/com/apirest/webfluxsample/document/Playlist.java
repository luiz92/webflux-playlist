package com.apirest.webfluxsample.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Playlist {
    @Id
    private String id;
    private String nome;

    public Playlist(String nome) {
        this.nome= nome;
    }
}
