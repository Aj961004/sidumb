package com.sidummy.sidumb.controller;

import com.sidummy.sidumb.model.dto.*;
import com.sidummy.sidumb.model.entity.CatPhotos;
import com.sidummy.sidumb.model.entity.Sports;
import com.sidummy.sidumb.repository.CatRepository;
import com.sidummy.sidumb.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cat")
public class CatController {

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private StorageService storageService;

    @PostMapping("/save")
    public DefaultResponse<CatDataDto> saveCat(@RequestBody CatDataDto catDataDto){
        CatPhotos catPhotos = convertDtoToEntity(catDataDto);
        DefaultResponse<CatDataDto> response = new DefaultResponse<>();
        catRepository.save(catPhotos);
        response.setMessages("Data berhasil disimpan");
        response.setData(catDataDto);

        return response;
    }

    @DeleteMapping("/delete")
    public DefaultResponse<CatDataDto> delToon(@RequestBody CatDataDto catDataDto){
        CatPhotos catPhotos = convertDtoToEntity(catDataDto);
        DefaultResponse<CatDataDto> response = new DefaultResponse<>();
        Optional<CatPhotos> optional = catRepository.findById(catDataDto.getIdCat());
        if (optional.isPresent()){
            catRepository.delete(catPhotos);
            response.setMessages("Data sudah terhapus.");
        } else {
            response.setMessages("Data tidak ditemukan.");
        }
        return response;
    }

    @GetMapping("/breedList")
    public List<CatBreedsDto> getListBreeds(){
        List<CatBreedsDto> list = new ArrayList<>();
        for (CatPhotos m : catRepository.findAll()){
            list.add(convertEntityToDtoBreeds(m));
        }

        return list;
    }

    @GetMapping("/catArtistList")
    public List<CatArtistDto> getListArtist(){
        List<CatArtistDto> list = new ArrayList<>();
        for (CatPhotos m : catRepository.findAll()){
            list.add(convertEntityToDtoArtist(m));
        }

        return list;
    }

    @PutMapping("/update")
    public DefaultResponse<CatPhotos> upCat(@RequestBody CatPhotos catPhotos){
        DefaultResponse<CatPhotos> response = new DefaultResponse<>();
        Optional<CatPhotos> optional = catRepository.findById(catPhotos.getIdCat());
        if (optional.isPresent()){
            storageService.updateCat(catPhotos);
            response.setMessages("Data Berhasil Diupdate");
            response.setData(catPhotos);
        } else {
            response.setMessages("Data Tidak Berhasil DIupdate");
            response.setData(catPhotos);
        }

        return response;
    }

    @GetMapping("/byname/{catName}")
    public DefaultResponse getByCatName(@PathVariable String catName) {
        DefaultResponse df = new DefaultResponse();
        Optional<CatPhotos> optional = catRepository.findByCatName(catName);
        if (optional.isPresent()) {
            df.setMessages("Data Ditemukan.");
            df.setData(convertEntityToDtoArtist(optional.get()));
        } else {
            df.setMessages("Data Tidak Ada.");
        }
        return df;

    }

    @GetMapping("/byname/{breeds}")
    public DefaultResponse getByCatBreeds(@PathVariable String breeds) {
        DefaultResponse df = new DefaultResponse();
        Optional<CatPhotos> optional = catRepository.findByBreeds(breeds);
        if (optional.isPresent()) {
            df.setMessages("Data Ditemukan.");
            df.setData(convertEntityToDtoBreeds(optional.get()));
        } else {
            df.setMessages("Data Tidak Ada.");
        }
        return df;

    }


    public CatPhotos convertDtoToEntity(CatDataDto dto){
        CatPhotos en = new CatPhotos();
        en.setBreeds(dto.getBreeds());
        en.setUrl(dto.getUrl());
        en.setCatName(dto.getCatName());
        en.setIdCat(dto.getIdCat());

        return en;
    }

    public CatBreedsDto convertEntityToDtoBreeds(CatPhotos en){
        CatBreedsDto dto = new CatBreedsDto();
        dto.setBreeds(en.getBreeds());
        dto.setUrl(en.getUrl());

        return dto;
    }

    public CatArtistDto convertEntityToDtoArtist(CatPhotos en){
        CatArtistDto dto = new CatArtistDto();
        dto.setCatName(en.getCatName());
        dto.setUrl(en.getUrl());

        return dto;
    }
}
