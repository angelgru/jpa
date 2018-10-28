package com.angel.jpa.controller;

import com.angel.jpa.domain.Album;
import com.angel.jpa.domain.Instrument;
import com.angel.jpa.domain.Singer;
import com.angel.jpa.service.SingerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SingerControllerTests {

    @InjectMocks
    private SingerController singerController;

    @Mock
    private SingerService singerService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetSingerByIdMethod() {
//        Given
        Singer singer = new Singer("John", "Dean");
        singer.setId(1L);

//        When
        try {
            when(singerService.getSingerById(1L)).thenReturn(singer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResponseEntity<Singer> returnedValue = singerController.getSingerById(1L);

//        Then
        try {
            verify(singerService).getSingerById(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(returnedValue.getStatusCode(), is(HttpStatus.OK));
        assertThat(returnedValue.getBody().getId(), is(1L));
    }

    @Test
    public void testAddSinger() {
//        Given
        Singer singerRequest = new Singer("John", "Dean");
        Singer singer = new Singer("John", "Dean");
        singer.setId(1L);
        ResponseEntity<Singer> responseEntity = null;

//        When
        when(singerService.addSinger(singerRequest)).thenReturn(singer);
        responseEntity = singerController.addSinger(singerRequest);

//        Then
        verify(singerService).addSinger(singerRequest);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(responseEntity.getBody().getId(), is(1L));
    }

    @Test
    public void testCreateAndAddAlbumForSinger() {
//        Given
        Album albumRequest = new Album("Test album");
        Long singerId = 1L;
        Singer singer = new Singer("John", "Mon");
        singer.setId(1L);
        HashSet<Album> albums = new HashSet<>();
        albums.add(albumRequest);
        singer.setAlbums(albums);
        ResponseEntity<Singer> responseEntity = null;

//        When

        try {
            when(singerService.createAndAddAlbumForSinger(singerId, albumRequest)).thenReturn(singer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseEntity = singerController.createAndAddAlbumForSinger(albumRequest, 1L);


//        Then
        try {
            verify(singerService).createAndAddAlbumForSinger(singerId, albumRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(responseEntity.getBody(), is(singer));
    }

    @Test
    public void testRemoveAlbumForSinger() {
//        Given
        Long singerId = 1L;
        Long albumId = 1L;
        Singer singer = new Singer("John", "Don");
        singer.setId(singerId);
        ResponseEntity<Singer> responseEntity;

//        When
        try {
            when(singerService.removeAlbumForSinger(singerId, albumId)).thenReturn(singer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseEntity = singerController.removeAlbumForSinger(singerId, albumId);

//        Then
        try {
            verify(singerService).removeAlbumForSinger(singerId, albumId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody(), is(singer));
    }

    @Test
    public void testCreateAndAddInstrumentForSinger() {
//        Given
        Instrument instrumentRequest = new Instrument("Drums");
        Long singerId = 1L;
        Singer singer = new Singer("Mon", "Don");
        singer.setId(1L);
        ResponseEntity<Singer> responseEntity;

//        When
        try {
            when(singerService.createAndAddInstrumentForSinger(singerId, instrumentRequest)).thenReturn(singer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseEntity = singerController.createAndAddInstrumentForSinger(instrumentRequest, singerId);

//        Then
        try {
            verify(singerService).createAndAddInstrumentForSinger(singerId, instrumentRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(responseEntity.getBody(), is(singer));
    }

    @Test
    public void testRemoveInstrumentForSinger() {
//        Given
        Long singerId = 1L;
        String instrumentId = "Drums";
        Singer singer = new Singer("Hon", "Jon");
        singer.setId(1L);
        ResponseEntity<Singer> responseEntity = null;

//        When
        try {
            when(singerService.removeInstrumentForSinger(singerId, instrumentId)).thenReturn(singer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseEntity = singerController.removeInstrumentForSinger(singerId, instrumentId);

//        Then
        try {
            verify(singerService).removeInstrumentForSinger(singerId, instrumentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody(), is(singer));
    }

    @Test
    public void testRemoveSinger() {
//        Given
        Long singerId = 1L;
        ResponseEntity responseEntity = null;

//        When
        responseEntity = singerController.removeSinger(singerId);

//        Then
        try {
            verify(singerService).removeSinger(singerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }
}
