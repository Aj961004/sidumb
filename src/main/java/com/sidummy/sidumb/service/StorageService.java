package com.sidummy.sidumb.service;

import com.sidummy.sidumb.model.entity.CatPhotos;
import com.sidummy.sidumb.model.entity.Sports;
import com.sidummy.sidumb.model.entity.Webtoon;
import com.sidummy.sidumb.repository.CatRepository;
import com.sidummy.sidumb.repository.SportsRepository;
import com.sidummy.sidumb.repository.WebtoonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private WebtoonRepository webtoonRepository;

    @Autowired
    private SportsRepository sportsRepository;

    public CatPhotos updateCat(CatPhotos catPhotos){
        CatPhotos catPhotos1 = catRepository.findById(catPhotos.getIdCat()).get();
        catPhotos1.setCatName(catPhotos.getCatName());
        catPhotos1.setBreeds(catPhotos.getBreeds());
        catPhotos1.setUrl(catPhotos.getUrl());
        return catRepository.save(catPhotos1);
    }

    public Webtoon updateToon(Webtoon webtoon){
        Webtoon webtoon1 = webtoonRepository.findByWebtoonName(webtoon.getWebtoonName()).get();
        webtoon1.setAuthor(webtoon.getAuthor());
        webtoon1.setGenre(webtoon.getGenre());
        webtoon1.setWebtoonName(webtoon.getWebtoonName());
        webtoon1.setStatus(webtoon.getStatus());
        webtoon1.setOriginCountry(webtoon.getOriginCountry());
        webtoon1.setPlatform(webtoon.getPlatform());
        return webtoonRepository.save(webtoon1);
    }

    public Sports updateSports(Sports sports){
        Sports sports1 = sportsRepository.findBySportsName(sports.getSportsName()).get();
        sports1.setSportsName(sports.getSportsName());
        sports1.setMatchDuration(sports.getMatchDuration());
        sports1.setPlayersInOneTeam(sports.getPlayersInOneTeam());
        return sportsRepository.save(sports1);
    }
}
