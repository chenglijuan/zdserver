package com.lemi.msloan.dao.impl;

import com.lemi.msloan.dao.ExamineDao;
import com.lemi.msloan.entity.Examine;
import org.springframework.stereotype.Repository;

@Repository(value = "examineDao")
public class ExamineDaoImpl extends BaseDaoImpl<Examine> implements ExamineDao {
}
