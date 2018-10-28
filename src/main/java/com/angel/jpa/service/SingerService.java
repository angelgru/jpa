package com.angel.jpa.service;

import com.angel.jpa.domain.Album;
import com.angel.jpa.domain.Instrument;
import com.angel.jpa.domain.Singer;
import com.angel.jpa.repository.SingerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SingerService {

    private final SingerRepository singerRepository;

    @Autowired
    public SingerService(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    public Singer addSinger(Singer singer) {
        return singerRepository.save(singer);
    }

    public Singer getSingerById(Long id) throws Exception {
        return singerRepository.findById(id).orElseThrow(() -> new Exception("Singer not found"));
    }

    public Singer createAndAddAlbumForSinger(Long id, Album albumRequest) throws Exception {
        Singer singer = singerRepository.findById(id).orElseThrow(() -> new Exception("Singer not found"));
        Album album = new Album(albumRequest.getTitle());
        singer.addAlbum(album);
        return singerRepository.save(singer);
    }

    public Singer removeAlbumForSinger(Long singerId, Long albumId) throws Exception {
        Singer singer = singerRepository.findById(singerId).orElseThrow(() -> new Exception("Singer not found"));
        singer.getAlbums().stream()
                .forEach((Album album) -> {
                    if(album.getId().equals(albumId)){
                        singer.removeAlbum(album);
                    }
                });
        return singerRepository.save(singer);
    }

    public Singer createAndAddInstrumentForSinger(Long id, Instrument instrument) throws Exception {
        Singer singer = singerRepository.findById(id).orElseThrow(() -> new Exception("Singer not found"));
        log.error("Create instrument " + instrument.getInstrumentId());
        singer.addInstrument(instrument);
        log.error("INSTRUMENT " + singer.getInstruments().size());
        return singerRepository.save(singer);
    }

    public Singer removeInstrumentForSinger(Long singerId, String instrumentId) throws Exception {
        Singer singer = singerRepository.findById(singerId).orElseThrow(() -> new Exception("Singer not found!"));
        log.error("SINGER 5" + singer.getFirstName());
        singer.getInstruments()
                .forEach((Instrument ins) -> {
                    if(ins.getInstrumentId().equals(instrumentId)){
                        singer.removeInstrument(ins);
                    }
                });
        return singerRepository.save(singer);
    }

    public void removeSinger(Long id) throws Exception {
        Singer singer = singerRepository.findById(id).orElseThrow(() -> new Exception("Singer not found"));
        singer.remove();
        singerRepository.delete(singer);
    }
}
