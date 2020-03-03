package com.company.quiz.service.quiz;

import com.company.quiz.dto.quiz.ScoreDto;
import com.company.quiz.mapper.quiz.ScoreMapper;
import com.company.quiz.repository.quiz.ScoreRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"scoreCacheService"})
public class ScoreService {

    private ScoreRepository scoreRepository;
    private ScoreMapper scoreMapper;

    public ScoreService(ScoreRepository scoreRepository,
                        ScoreMapper scoreMapper) {
        this.scoreRepository = scoreRepository;
        this.scoreMapper = scoreMapper;
    }

    @CacheEvict
    @Transactional
    public ResponseEntity<?> scoring(List<ScoreDto> scoreDtoList) {
        scoreDtoList.forEach(scoreDto -> {
            if (scoreDto.getSubAnswerId() == null) {
                scoreRepository.save(scoreMapper.toScore(scoreDto, 0l));
            } else {
                scoreDto.getSubAnswerId().forEach(subAnswerId ->
                        scoreRepository.save(scoreMapper.toScore(scoreDto, subAnswerId))
                );
            }
        });
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
