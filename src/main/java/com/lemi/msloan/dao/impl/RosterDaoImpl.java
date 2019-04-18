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
    public List<Roster> selectRosterExamine(RosterRequest rosterRequest) {
        return sqlSessionTemplate.selectList("tbl_roster.selectRosterExamine",rosterRequest);
    }

    @Override
    public Integer selectRosterExamineCount(RosterRequest rosterRequest) {
        return sqlSessionTemplate.selectOne("tbl_roster.selectRosterExamineCount",rosterRequest);
    }

    @Override
    public List<Roster> selectStartWarning(RosterRequest rosterRequest) {
        return sqlSessionTemplate.selectList("tbl_roster.selectStartWarning",rosterRequest);
    }

    @Override
    public Integer selectStartWarningCount() {
        return sqlSessionTemplate.selectOne("tbl_roster.selectStartWarningCount");
    }

    @Override
    public List<Roster> selectEndWarning(RosterRequest rosterRequest) {
        return sqlSessionTemplate.selectList("tbl_roster.selectEndWarning",rosterRequest);
    }

    @Override
    public Integer selectEndWarningCount() {
        return sqlSessionTemplate.selectOne("tbl_roster.selectEndWarningCount");
    }

    @Override
    public List<Roster> selectExamine(RosterRequest rosterRequest) {
        return sqlSessionTemplate.selectList("tbl_roster.selectExamine",rosterRequest);
    }

    @Override
    public Integer selectExamineCount() {
        return sqlSessionTemplate.selectOne("tbl_roster.selectExamineCount");
    }

    @Override
    public List<Roster> selectUndetermined(RosterRequest rosterRequest) {
        return sqlSessionTemplate.selectList("tbl_roster.selectUndetermined",rosterRequest);
    }

    @Override
    public Integer selectUndeterminedCount() {
        return sqlSessionTemplate.selectOne("tbl_roster.selectUndeterminedCount");
    }
}
