package com.example.songsapi.repositories;

import com.example.songsapi.models.Song;
import org.springframework.data.repository.CrudRepository;

public interface songRepository extends CrudRepository<Song, Long> {

}


