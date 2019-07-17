package pruebafbi.logic.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebafbi.entities.*;
import pruebafbi.exceptions.LogicException;
import pruebafbi.logic.Command;

import java.util.ArrayList;
import java.util.Date;

@Service
public class FileNewMember extends Command<Member> {

    private Member _member;
    private Member _boss;
    private Status _status;
    private Rank _rank;
    private Member _response;

    @Autowired
    private GetStatusById getStatusById;
    @Autowired
    private GetRankById getRankById;
    @Autowired
    private GetMemberById getMemberById;
    @Autowired
    private SaveMember saveMember;

    @Override
    public void initCommand(Object... args) {
        _member = (Member) args[0];
        _boss = (Member) args[1];
        _rank = (Rank) args[2];
        _status = (Status) args[3];
    }

    @Override
    public void execute() throws LogicException {
        findStatus();
        findRank();
        populateStatusLog();
        populateRankLog();
        populateBossLog();
        saveMemberCommand();
    }

    @Override
    public Member getResults() {
        return _response;
    }

    private final void findStatus() throws LogicException {
        getStatusById.initCommand(_status);
        getStatusById.execute();
        _status = getStatusById.getResults();
    }

    private final void findRank() throws LogicException
    {
        getRankById.initCommand(_rank);
        getRankById.execute();
        _rank = getRankById.getResults();
    }

    private final void populateStatusLog()
    {
        _member.setMemberStatusLogList( new ArrayList<>() );
        final MemberStatusLog memberStatusLog = EntityFactory.instanceMemberStatusLog();
        memberStatusLog.setStatus(_status);
        memberStatusLog.setMember(_member);
        memberStatusLog.setDate(new Date());
        _member.getMemberStatusLogList().add(memberStatusLog);
    }

    private final void populateRankLog()
    {
        _member.setMemberRankLogList(new ArrayList<>());
        final MemberRankLog memberRankLog =  EntityFactory.instanceMemberRankLog();
        memberRankLog.setRank(_rank);
        memberRankLog.setMember(_member);
        memberRankLog.setDate(new Date());
        _member.getMemberRankLogList().add(memberRankLog);
    }

    private final void populateBossLog() throws LogicException
    {
        getMemberById.initCommand(_member);
        getMemberById.execute();
        _boss = getMemberById.getResults();

        _member.setBossActivityList(new ArrayList<>());
        final MemberBossActivity memberBossActivity = EntityFactory.instanceMemberBossActivity();
        memberBossActivity.setActual(true);
        memberBossActivity.setBoss(_boss);
        memberBossActivity.setMember(_member);
        memberBossActivity.setDate(new Date());
        _member.getBossActivityList().add(memberBossActivity);
    }

    private final void saveMemberCommand() throws LogicException
    {
        saveMember.initCommand(_member);
        saveMember.execute();
        _response = saveMember.getResults();
    }
}
