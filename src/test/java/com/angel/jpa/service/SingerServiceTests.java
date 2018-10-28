package com.angel.jpa.service;

import com.angel.jpa.domain.Album;
import com.angel.jpa.domain.Singer;
import com.angel.jpa.repository.SingerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SingerServiceTests {

    @InjectMocks
    private SingerService singerService;

    @Mock
    private SingerRepository singerRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddSinger() {
//        Given
        Singer singerRequest = new Singer("John", "Doe");
        Singer singer = null;

//        When
        when(singerRepository.save(singerRequest)).thenReturn(singerRequest);
        singer = singerService.addSinger(singerRequest);

//        Then
        verify(singerRepository).save(singerRequest);
        assertThat(singer, is(singerRequest));
    }

    @Test
    public void testGetSingerById() {
//        Given
        Long singerId = 1L;
        Optional<Singer> singerOptional = Optional.of(new Singer("John", "Mon"));
        Singer singer = null;

//        When
        try {
            when(singerRepository.findById(singerId)).thenReturn(singerOptional);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            singer = singerService.getSingerById(singerId);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Then
        try {
            verify(singerRepository).findById(singerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(singer, is(singerOptional.get()));
    }

    @Test
    public void testCreateAndAddAlbumForSinger() {
//        Given
        Long singerId = 1L;
        Album albumRequest = new Album("Test album");
        Optional<Singer> singerOptional = Optional.of(new Singer("John", "Mon"));
        singerOptional.get().setId(singerId);

        Singer returnedSinger = new Singer("John", "Mon");
        returnedSinger.setId(singerId);
        returnedSinger.addAlbum(albumRequest);
        Singer singer = null;

//        When
        when(singerRepository.findById(singerId)).thenReturn(singerOptional);
        when(singerRepository.save(singerOptional.get())).thenReturn(returnedSinger);
        try {
            singer = singerService.createAndAddAlbumForSinger(singerId, albumRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Then
        verify(singerRepository).findById(singerId);
        verify(singerRepository).save(singerOptional.get());
        assertThat(singer, is(returnedSinger));
    }

    @Test
    public void testRemoveAlbumForSinger() {
//        Given
        Long singerId = 1L;
        Long albumId = 1L;

        Album album = new Album("Test Album");
        album.setId(albumId);

        Singer singerWithAlbums = new Singer("John", "Doe");
        album.setSinger(singerWithAlbums);
        singerWithAlbums.addAlbum(album);
        Optional<Singer> optionalSinger = Optional.of(singerWithAlbums);

        Singer returnedSinger = new Singer("John", "Doe");
        returnedSinger.setId(singerId);
        Singer singer = null;

//        When
        when(singerRepository.findById(singerId)).thenReturn(optionalSinger);
        when(singerRepository.save(optionalSinger.get())).thenReturn(returnedSinger);

        try {
            singer = singerService.removeAlbumForSinger(singerId, albumId);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Then
        verify(singerRepository).findById(singerId);
        verify(singerRepository).save(optionalSinger.get());
        assertThat(singer, is(returnedSinger));
    }

    @Test
    public void testRemoveSinger() {
//        Given
        Long singerId = 1L;
        Singer singer = new Singer("John", "J");
        singer.setId(singerId);
        Album album = new Album("Nice Album");
        album.setSinger(singer);
        singer.addAlbum(album);
        Optional<Singer> optionalSinger = Optional.of(singer);

//        When
        when(singerRepository.findById(singerId)).thenReturn(optionalSinger);
        try {
            singerService.removeSinger(singerId);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Then
        verify(singerRepository).findById(singerId);
        assertThat(optionalSinger.get().getInstruments().size(), is(0));
    }
}
