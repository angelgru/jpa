package com.angel.jpa.controller;

import com.angel.jpa.domain.Album;
import com.angel.jpa.domain.Singer;
import com.angel.jpa.repository.AlbumRepository;
import com.angel.jpa.repository.InstrumentRepository;
import com.angel.jpa.repository.SingerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class GeneralController {

    private AlbumRepository albumRepository;
    private InstrumentRepository instrumentRepository;
    private SingerRepository singerRepository;

    @Autowired
    public GeneralController(AlbumRepository albumRepository, InstrumentRepository instrumentRepository, SingerRepository singerRepository) {
        this.albumRepository = albumRepository;
        this.instrumentRepository = instrumentRepository;
        this.singerRepository = singerRepository;
    }

//    @GetMapping("/singer/{id}")
//    public Singer getSingerById(@PathVariable("id") Long id) throws Exception {
//        Singer singer = singerRepository.findById(id)
//                .orElseThrow(() -> new Exception("Singer with id " + id + " not found"));
//
//        return singer;
//    }

    @GetMapping("/singer/{id}")
    public Singer getSingerById(@PathVariable("id") Long id) throws Exception {
        Singer singer = singerRepository.findById(id)
                .orElseThrow(() -> new Exception("Singer with id " + id + " not found"));
        log.error(singer.getAlbums().toString());
        return singer;
    }

    @DeleteMapping("/singer/{id}")
    public @ResponseBody void deleteSingerById(@PathVariable("id") Long id) throws Exception {
        Singer singer = singerRepository.findById(id)
                .orElseThrow(() -> new Exception("Singer with id " + id + " not found"));
        singerRepository.delete(singer);
    }

    @DeleteMapping("/singer/orphan/{id}")
    public @ResponseBody void deleteSingerByIdOrphan(@PathVariable("id") Long id) throws Exception {
        Singer singer = singerRepository.findById(id)
                .orElseThrow(() -> new Exception("Singer with id " + id + " not found"));
        if(singer.getAlbums().size() >0) {
            singer.removeAlbum(singer.getAlbums().iterator().next());
            log.error("REMOVED");
        }
        singerRepository.save(singer);
    }


    @GetMapping("/album/{id}")
    public Album getAlbumById(@PathVariable("id") Long id) throws Exception {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new Exception("Album with id " + id + " not found"));
        albumRepository.save(album);
        return album;
    }

}
