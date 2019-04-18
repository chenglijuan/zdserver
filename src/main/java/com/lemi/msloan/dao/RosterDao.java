package com.lemi.msloan.dao;

import com.lemi.msloan.entity.Roster;
import com.lemi.msloan.request.RosterRequest;

import java.util.List;

public interface RosterDao extends BaseDao<Roster> {
    Roster getByIdCard(RosterRequest rosterRequest);

    List<Roster> selectRoster(RosterRequest rosterRequest);

    Integer selectRosterCount(RosterRequest rosterRequest);

    List<Roster> selectRosterExamine(RosterRequest rosterRequest);

    Integer selectRosterExamineCount(RosterRequest rosterRequest);

    List<Roster> selectStartWarning(RosterRequest rosterRequest);

    Integer selectStartWarningCount();

    List<Roster> selectEndWarning(RosterRequest rosterRequest);

    Integer selectEndWarningCount();

    List<Roster> selectExamine(RosterRequest rosterRequest);

    Integer selectExamineCount();

    List<Roster> selectUndetermined(RosterRequest rosterRequest);

    Integer selectUndeterminedCount();
}
