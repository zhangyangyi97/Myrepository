package com.softeem.service;

import com.softeem.entity.Result;
import com.softeem.mapper.ResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/8/3 9:00
 * 项目名称 testmanager
 */
@Service
public class ResultService {

    @Autowired
    private ResultMapper resultMapper;

    public int getAllFinishPapers(Result result){
        return resultMapper.addResults(result);
    }
}
