package com.lemi.msloan.dao.impl;

import com.lemi.msloan.dao.RosterDao;
import com.lemi.msloan.entity.Roster;
import com.lemi.msloan.request.RosterRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "rosterDao")
public class RosterDaoImpl extends BaseDaoImpl<Roster> implements RosterDao {
    @Override
    public Roster getByIdCard(RosterRequest rosterRequest) {
        return sqlSessionTemplate.selectOne("tbl_roster.getByIdCard",rosterRequest);
    }

    @Override
    public List<Roster> selectRoster(RosterRequest rosterRequest) {
        return sqlSessionTemplate.selectList("tbl_roster.selectRoster",rosterRequest);
    }

    @Override
    public Integer selectRosterCount(RosterRequest rosterRequest) {
        return sqlSessionTemplate.selectOne("tbl_roster.selectRosterCount",rosterRequest);
    }

    @Override
    public Roster getByExamineId(RosterRequest rosterRequest) {
        return sqlSessionTemplate.selectOne("tbl_roster.getByExamineId",rosterRequest);
    }
}
