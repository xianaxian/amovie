package com.ecjtu.amovie.api.service;

import com.ecjtu.amovie.api.repository.ScoreRepository;
import org.springframework.stereotype.Service;

/**
 * @author xianaxian  2019-08-29 16:31
 */
@Service
public class ScoreService {
    private final ScoreRepository scoreRepository;

    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    /**
     * 给电影评分
     * @param mId 电影id
     * @param uId 用户id
     * @param score 评分
     * @return 返回是否成功
     */
    public int insertScore(int mId, int uId, int score){
        if (scoreRepository.isExist(mId, uId)!=0){
            throw  new RuntimeException("重复打分");
        }
        return scoreRepository.insertOne(mId,uId,score);
    }


}
