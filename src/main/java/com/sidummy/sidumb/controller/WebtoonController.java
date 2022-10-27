package com.sidummy.sidumb.controller;

import com.sidummy.sidumb.model.dto.DefaultResponse;
import com.sidummy.sidumb.model.dto.WebtoonDto;
import com.sidummy.sidumb.model.entity.Sports;
import com.sidummy.sidumb.model.entity.Webtoon;
import com.sidummy.sidumb.repository.WebtoonRepository;
import com.sidummy.sidumb.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/webtoon")
public class WebtoonController {

    @Autowired
    private WebtoonRepository webtoonRepository;

    @Autowired
    private StorageService storageService;

    @PostMapping("/save")
    public DefaultResponse<WebtoonDto> saveToon(@RequestBody WebtoonDto webtoonDto) {
        Webtoon webtoon = convertDtoToEntity(webtoonDto);
        DefaultResponse<WebtoonDto> response = new DefaultResponse<>();
        Optional<Webtoon> optional = webtoonRepository.findByWebtoonName(webtoonDto.getWebtoonName());
        if (optional.isPresent()){
            response.setMessages("Error, Data Sudah Tersedia Mohon di cek");
            response.setData(webtoonDto);
        } else {
            webtoonRepository.save(webtoon);
            response.setMessages("Data Berhasil Disimpan");
            response.setData(webtoonDto);
        }

        return response;
    }

    @DeleteMapping("/delete")
    public DefaultResponse<WebtoonDto> delToon(@RequestBody WebtoonDto webtoonDto){
        Webtoon webtoon = convertDtoToEntity(webtoonDto);
        DefaultResponse<WebtoonDto> response = new DefaultResponse<>();
        Optional<Webtoon> optional = webtoonRepository.findById(webtoonDto.getIdWebtoon());
        if (optional.isPresent()){
            webtoonRepository.delete(webtoon);
            response.setMessages("Data sudah terhapus.");
        } else {
            response.setMessages("Data tidak ditemukan.");
        }
        return response;
    }

    @GetMapping("/list")
    public List<WebtoonDto> getListWebtoon(){
        List<WebtoonDto> list = new ArrayList<>();
        for (Webtoon m : webtoonRepository.findAll()){
            list.add(convertEntityToDto(m));
        }

        return list;
    }

    @PutMapping("/update")
    public DefaultResponse<Webtoon> upToon(@RequestBody Webtoon webtoon){
        DefaultResponse<Webtoon> response = new DefaultResponse<>();
        Optional<Webtoon> optional = webtoonRepository.findByWebtoonName(webtoon.getWebtoonName());
        if (optional.isPresent()){
            storageService.updateToon(webtoon);
            response.setMessages("Data Berhasil Diupdate");
            response.setData(webtoon);
        } else {
            response.setMessages("Data Tidak Berhasil Diupdate");
            response.setData(webtoon);
        }

        return response;
    }

    @GetMapping("/byname/{webtoonName}")
    public DefaultResponse getByNameToon(@PathVariable String webtoonName) {
        DefaultResponse df = new DefaultResponse();
        Optional<Webtoon> optional = webtoonRepository.findByWebtoonName(webtoonName);
        if (optional.isPresent()) {
            df.setMessages("Data Ditemukan.");
            df.setData(convertEntityToDto(optional.get()));
        } else {
            df.setMessages("Data Tidak Ada.");
        }
        return df;

    }
//
//    @GetMapping("/byregion/{originCountry}")
//    public DefaultResponse getByCountry(@PathVariable String originCountry) {
//        DefaultResponse df = new DefaultResponse();
//        Optional<Webtoon> optional = webtoonRepository.findByOriginCountry(originCountry);
//        if (optional.isPresent()) {
//            df.setMessages("Data Ditemukan.");
//            df.setData(convertEntityToDto(optional.get()));
//        } else {
//            df.setMessages("Data Tidak Ada.");
//        }
//        return df;
//
//    }
//
//    @GetMapping("/byplatform/{platform}")
//    public DefaultResponse getByPlatform(@PathVariable String platform) {
//        DefaultResponse df = new DefaultResponse();
//        Optional<Webtoon> optional = webtoonRepository.findByPlatform(platform);
//        if (optional.isPresent()) {
//            df.setMessages("Data Ditemukan.");
//            df.setData(convertEntityToDto(optional.get()));
//        } else {
//            df.setMessages("Data Tidak Ada.");
//        }
//        return df;
//
//    }

    @GetMapping("/bystatus/{status}")
    public DefaultResponse getByStatus(@PathVariable String status) {
        DefaultResponse df = new DefaultResponse();
        Optional<Webtoon> optional = webtoonRepository.findByStatus(status);
        if (optional.isPresent()) {
            df.setMessages("Data Ditemukan.");
            df.setData(convertEntityToDto(optional.get()));
        } else {
            df.setMessages("Data Tidak Ada.");
        }
        return df;

    }




    public Webtoon convertDtoToEntity(WebtoonDto dto) {
        Webtoon webtoon = new Webtoon();
        webtoon.setIdWebtoon(dto.getIdWebtoon());
        webtoon.setAuthor(dto.getAuthor());
        webtoon.setPlatform(dto.getPlatform());
        webtoon.setWebtoonName(dto.getWebtoonName());
        webtoon.setStatus(dto.getStatus());
        webtoon.setGenre(dto.getGenre());
        webtoon.setOriginCountry(dto.getOriginCountry());

        return webtoon;
    }

    public WebtoonDto convertEntityToDto(Webtoon en){
        WebtoonDto dto = new WebtoonDto();
        dto.setAuthor(en.getAuthor());
        dto.setGenre(en.getGenre());
        dto.setWebtoonName(en.getWebtoonName());
        dto.setStatus(en.getStatus());
        dto.setPlatform(en.getPlatform());
        dto.setOriginCountry(en.getOriginCountry());

        return dto;
    }

}
