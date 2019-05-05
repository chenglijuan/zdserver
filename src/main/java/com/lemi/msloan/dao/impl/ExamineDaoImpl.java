package com.lemi.msloan.dao.impl;

import com.lemi.msloan.dao.ExamineDao;
import com.lemi.msloan.entity.Examine;
import com.lemi.msloan.request.ExamineRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "examineDao")
public class ExamineDaoImpl extends BaseDaoImpl<Examine> implements ExamineDao {
    @Override
    public List<Examine> findAllExamine(ExamineRequest examineRequest) {
        return sqlSessionTemplate.selectList("tbl_examine.findAllExamine",examineRequest);
    }

    @Override
    public Integer findAllExamineCount(ExamineRequest examineRequest) {
        return sqlSessionTemplate.selectOne("tbl_examine.findAllExamineCount",examineRequest);
    }

    @Override
    public Integer getByIdCard(ExamineRequest examineRequest) {
        return sqlSessionTemplate.selectOne("tbl_examine.getByIdCard",examineRequest);
    }

    @Override
    public List<Examine> getExamineWillStart(ExamineRequest examineRequest) {
        return sqlSessionTemplate.selectList("tbl_examine.getExamineWillStart",examineRequest);
    }

    @Override
    public Integer getExamineWillStartCount(ExamineRequest examineRequest) {
        return sqlSessionTemplate.selectOne("tbl_examine.getExamineWillStartCount",examineRequest);
    }

    @Override
    public List<Examine> getExamineWillStop(ExamineRequest examineRequest) {
        return sqlSessionTemplate.selectList("tbl_examine.getExamineWillStop",examineRequest);
    }

    @Override
    public Integer getExamineWillStopCount(ExamineRequest examineRequest) {
        return sqlSessionTemplate.selectOne("tbl_examine.getExamineWillStopCount",examineRequest);
    }

    @Override
    public Examine getBeanByIdCard(ExamineRequest examineRequest) {
        return sqlSessionTemplate.selectOne("tbl_examine.getBeanByIdCard",examineRequest);
    }

    @Override
    public List<Examine> findAgainExamine(ExamineRequest examineRequest) {
        return sqlSessionTemplate.selectList("tbl_examine.findAgainExamine",examineRequest);
    }

    @Override
    public Integer findAgainExamineCount(ExamineRequest examineRequest) {
        return sqlSessionTemplate.selectOne("tbl_examine.findAgainExamineCount",examineRequest);
    }

    @Override
    public List<Examine> findUndeterminedExamine(ExamineRequest examineRequest) {
        return sqlSessionTemplate.selectList("tbl_examine.findUndeterminedExamine",examineRequest);
    }

    @Override
    public Integer findUndeterminedExamineCount(ExamineRequest examineRequest) {
        return sqlSessionTemplate.selectOne("tbl_examine.findUndeterminedExamineCount",examineRequest);
    }
}
