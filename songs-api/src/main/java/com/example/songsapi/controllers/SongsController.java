package com.example.songsapi.controllers;

import com.example.songsapi.models.Song;
import com.example.songsapi.repositories.songRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
public class SongsController {

  //Ask spring to get dependency injection
  @Autowired
  private com.example.songsapi.repositories.songRepository songRepository;

  @GetMapping("/")
  public Iterable<Song> findAllSongs() {
    return songRepository.findAll();
  }

  //Same name as long as add @PathVariable
  @GetMapping("/{songId}")
  public Song findSongById(@PathVariable Long songId) {
    return songRepository.findOne(songId);
  }

  //Delete a song by ID
  @DeleteMapping("/{songId}")
  public HttpStatus deleteSongById(@PathVariable Long songId) {
    songRepository.delete(songId);
    return HttpStatus.OK;
  }

  //Posting
  @PostMapping("/")
  public Song createNewSomg(@RequestBody Song newSong) {
    return songRepository.save(newSong);
  }

  //Naive update
  @PatchMapping("/{songId}")
  public Song updateSongById(@PathVariable Long songId, @RequestBody Song songRequest) {

    Song songFromDb = songRepository.findOne(songId);

    songFromDb.setTitle(songRequest.getTitle());
    songFromDb.setArtist(songRequest.getArtist());
    songFromDb.setLength(songRequest.getLength());

    return songRepository.save(songFromDb);
  }

}
