package com.sidummy.sidumb.controller;


import com.sidummy.sidumb.model.dto.DefaultResponse;
import com.sidummy.sidumb.model.dto.SportsDto;
import com.sidummy.sidumb.model.entity.Sports;
import com.sidummy.sidumb.repository.SportsRepository;
import com.sidummy.sidumb.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sports")
public class SportsController {

    @Autowired
    private SportsRepository sportsRepository;

    @Autowired
    private StorageService storageService;

    @PostMapping("/save")
    public DefaultResponse<SportsDto> saveSports(@RequestBody SportsDto sportsDto){
        Sports sports = convertDtoToEntity(sportsDto);
        DefaultResponse<SportsDto> response = new DefaultResponse<>();
        Optional<Sports> optional = sportsRepository.findBySportsName(sportsDto.getSportsName());
        if (optional.isPresent()){
            response.setMessages("Data sudah tersedia. Data tidak boleh double.");
            response.setData(sportsDto);
        } else {
            sportsRepository.save(sports);
            response.setMessages("Data berhasil ditambahkan.");
            response.setData(sportsDto);
        }

        return response;
    }

    @DeleteMapping("/delete")
    public DefaultResponse<SportsDto> delToon(@RequestBody SportsDto sportsDto){
        Sports sports = convertDtoToEntity(sportsDto);
        DefaultResponse<SportsDto> response = new DefaultResponse<>();
        Optional<Sports> optional = sportsRepository.findById(sportsDto.getIdSports());
        if (optional.isPresent()){
            sportsRepository.delete(sports);
            response.setMessages("Data sudah terhapus.");
        } else {
            response.setMessages("Data tidak ditemukan.");
        }
        return response;
    }

    @GetMapping("/list")
    public List<SportsDto> getListWebtoon(){
        List<SportsDto> list = new ArrayList<>();
        for (Sports m : sportsRepository.findAll()){
            list.add(convertEntityToDto(m));
        }

        return list;
    }

    @PutMapping("/update")
    public DefaultResponse<Sports> upToon(@RequestBody Sports sports){
        DefaultResponse<Sports> response = new DefaultResponse<>();
        Optional<Sports> optional = sportsRepository.findBySportsName(sports.getSportsName());
        if (optional.isPresent()){
            storageService.updateSports(sports);
            response.setMessages("Data Berhasil Diupdate");
            response.setData(sports);
        } else {
            response.setMessages("Data Tidak Berhasil Diupdate");
            response.setData(sports);
        }

        return response;
    }

    public Sports convertDtoToEntity(SportsDto dto){
        Sports sports = new Sports();
        sports.setIdSports(dto.getIdSports());
        sports.setSportsName(dto.getSportsName());
        sports.setPlayersInOneTeam(dto.getPlayersInOneTeam());
        sports.setMatchDuration(dto.getMatchDuration());
        return sports;
    }

    public SportsDto convertEntityToDto(Sports en){
        SportsDto dto = new SportsDto();
        dto.setIdSports(en.getIdSports());
        dto.setMatchDuration(en.getMatchDuration());
        dto.setSportsName(en.getSportsName());
        dto.setPlayersInOneTeam(en.getPlayersInOneTeam());
        return dto;
    }


}
