package com.lemi.msloan.dao;

import com.lemi.msloan.entity.Roster;
import com.lemi.msloan.request.RosterRequest;

import java.util.List;

public interface RosterDao extends BaseDao<Roster> {
    Roster getByIdCard(RosterRequest rosterRequest);

    List<Roster> selectRoster(RosterRequest rosterRequest);

    Integer selectRosterCount(RosterRequest rosterRequest);

    Roster getByExamineId(RosterRequest rosterRequest);
}
