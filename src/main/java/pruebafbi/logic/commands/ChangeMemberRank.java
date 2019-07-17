package pruebafbi.logic.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebafbi.entities.*;
import pruebafbi.exceptions.LogicException;
import pruebafbi.logic.Command;

import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

@Service
public class ChangeMemberRank extends Command<Member> {


    private Member _member;
    private Rank _rank;
    private Member _response;

    @Autowired
    private GetRankById getRankById;
    @Autowired
    private SaveMember saveMemberCommand;

    @Override
    public void initCommand(Object... args) {
        _member = (Member) args[0];
        _rank = (Rank) args[1];
    }

    public void execute() throws LogicException {
        workRank();
        saveMember();
    }

    public Member getResults() {
        return _response;
    }

    private final void workRank() throws LogicException
    {
        final Optional<MemberRankLog> optionalMRL = _member.getMemberRankLogList().stream()
                .sorted( Comparator.comparing( MemberRankLog::getDate ).reversed() )
                .findFirst();
        if (optionalMRL.get().getRank().getId() == _rank.getId())
        {
            throw new LogicException("SAME_RANK_AS_CURRENT");
        }
        getRankById.initCommand(_rank);
        getRankById.execute();
        final Rank rank = getRankById.getResults();

        final MemberRankLog memberRankLog = EntityFactory.instanceMemberRankLog();
        memberRankLog.setRank(rank);
        memberRankLog.setMember(_member);
        memberRankLog.setDate(new Date());
        _member.getMemberRankLogList().add(memberRankLog);
    }

    private final void saveMember() throws LogicException {
        saveMemberCommand.initCommand(_member);
        saveMemberCommand.execute();
        _response = saveMemberCommand.getResults();
    }

}
