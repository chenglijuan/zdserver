package com.lemi.msloan.dao;

import com.lemi.msloan.entity.Examine;
import com.lemi.msloan.request.ExamineRequest;

import java.util.List;

public interface ExamineDao extends BaseDao<Examine> {
    List<Examine> findAllExamine(ExamineRequest examineRequest);

    Integer findAllExamineCount(ExamineRequest examineRequest);

    Integer getByIdCard(ExamineRequest examineRequest);

    List<Examine> getExamineWillStart(ExamineRequest examineRequest);

    Integer getExamineWillStartCount(ExamineRequest examineRequest);

    List<Examine> getExamineWillStop(ExamineRequest examineRequest);

    Integer getExamineWillStopCount(ExamineRequest examineRequest);

    Examine getBeanByIdCard(ExamineRequest examineRequest);
}
