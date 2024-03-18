package com.forceslalala.mdsdiag.service;

import com.forceslalala.mdsdiag.entity.DiagRecord;
import com.forceslalala.mdsdiag.entity.vo.Result;
import com.forceslalala.mdsdiag.utils.UniqueIdGenerator;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DiagService
 * @Author: wangtao
 * @Date: 2024/3/11 19:05
 * @Describe:
 */

@Service
public class DiagService {

    @Resource
    private RedisTemplate<String, DiagRecord> redisTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Result<DiagRecord> add(DiagRecord diagRecord) {
        Result<DiagRecord> result = new Result<>();
        if (diagRecord.getId().isEmpty()) {
            diagRecord.setId(UniqueIdGenerator.generate());
        }
        redisTemplate.opsForValue().set(diagRecord.getId(), diagRecord);
        result.setResultSuccess("成功插入问诊记录");
        return result;
    }

    public Result<DiagRecord> addInMongo(DiagRecord diagRecord) {
        Result<DiagRecord> result = new Result<>();
        if (diagRecord.getId().isEmpty()) {
            diagRecord.setId(UniqueIdGenerator.generate());
        }
        mongoTemplate.insert(diagRecord, "diag_record");
        result.setResultSuccess("成功插入问诊记录");
        return result;
    }

    public PageInfo<DiagRecord> getDiagRecord(int pageNum, int pageSize, int doctorId) {
        Query query = new Query(Criteria.where("doctorId").is(doctorId));
        query.skip((long) (pageNum - 1) * pageSize);
        query.limit(pageSize);
        List<DiagRecord> list = mongoTemplate.find(query, DiagRecord.class);
        return new PageInfo<>(list);
    }

}
