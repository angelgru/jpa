package com.angel.jpa.controller;

import com.angel.jpa.domain.Album;
import com.angel.jpa.domain.Instrument;
import com.angel.jpa.domain.Singer;
import com.angel.jpa.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/singer")
@Slf4j
public class SingerController {

    private final SingerService singerService;

    @Autowired
    public SingerController(SingerService singerService) {
        this.singerService = singerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Singer> getSingerById(@PathVariable("id") Long id) {

        ResponseEntity<Singer> responseEntity = null;
        Singer singer = null;

        try{
            singer = singerService.getSingerById(id);
            responseEntity = new ResponseEntity<>(singer, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @PostMapping("/")
    public ResponseEntity<Singer> addSinger(@RequestBody Singer singerRequest) {
        Singer singer = singerService.addSinger(singerRequest);
        ResponseEntity<Singer> responseEntity = new ResponseEntity<>(singer, HttpStatus.CREATED);
        return responseEntity;
    }

    @PostMapping("/{id}/album")
    public ResponseEntity<Singer> createAndAddAlbumForSinger(@RequestBody Album album, @PathVariable("id") Long id) {
        Singer singer = null;
        ResponseEntity<Singer> responseEntity = null;
        try{
            singer = singerService.createAndAddAlbumForSinger(id, album);
            responseEntity = new ResponseEntity<>(singer, HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @DeleteMapping("/{id}/album/{albumId}")
    public ResponseEntity<Singer> removeAlbumForSinger(@PathVariable("id") Long singerId,
                                                       @PathVariable("albumId") Long albumId) {
        ResponseEntity<Singer> responseEntity = null;
        Singer singer = null;
        try{
            singer = singerService.removeAlbumForSinger(singerId, albumId);
            responseEntity = new ResponseEntity<>(singer, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @PostMapping("/{id}/instrument")
    public ResponseEntity<Singer> createAndAddInstrumentForSinger(@RequestBody Instrument instrument,
                                                                  @PathVariable("id") Long id) {
        ResponseEntity<Singer> responseEntity = null;
        Singer singer = null;
        try{
            singer = singerService.createAndAddInstrumentForSinger(id, instrument);
            responseEntity = new ResponseEntity<>(singer, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @DeleteMapping("/{singerId}/instrument/{instrumentId}")
    public ResponseEntity<Singer> removeInstrumentForSinger(@PathVariable("singerId") Long singerId,
                                                            @PathVariable("instrumentId") String instrumentId) {
        ResponseEntity<Singer> responseEntity = null;
        Singer singer = null;
        try{
            singer = singerService.removeInstrumentForSinger(singerId, instrumentId);
            responseEntity = new ResponseEntity<>(singer, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeSinger(@PathVariable("id") Long id) {
        ResponseEntity responseEntity = null;
        try {
            singerService.removeSinger(id);
            responseEntity = new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }
}
