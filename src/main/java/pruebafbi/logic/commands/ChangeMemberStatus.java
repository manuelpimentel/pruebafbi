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
public class ChangeMemberStatus extends Command<Member> {

    private Member _member;
    private Status _status;
    private Member _response;

    @Autowired
    private GetStatusByName getStatusByName;
    @Autowired
    private SaveMember saveMemberCommand;
    @Autowired
    private SaveMemberStatusLog saveMemberStatusLog;


    @Override
    public void initCommand(Object... args) {
        _member = (Member) args[0];
        _status = (Status) args[1];
    }

    public void execute() throws LogicException {
        workStatus();
        saveMember();
    }

    public Member getResults() {
        return _response;
    }

    private final void workStatus() throws LogicException
    {
        getStatusByName.initCommand(_status);
        getStatusByName.execute();
        _status = getStatusByName.getResults();

        final Optional<MemberStatusLog> optionalMSL = _member.getMemberStatusLogList().stream()
                .sorted( Comparator.comparing( MemberStatusLog::getDate ).reversed() )
                .findFirst();
        if (optionalMSL.get().getStatus().getId() == _status.getId())
        {
            throw new LogicException("SAME_STATUS_AS_CURRENT");
        }else if (optionalMSL.get().getStatus().getName().equalsIgnoreCase(Status.status.Dead.getStatus()))
        {
            throw new LogicException("MEMBER_IS_DEAD");
        }


        final MemberStatusLog memberStatusLog = EntityFactory.instanceMemberStatusLog();
        memberStatusLog.setStatus(_status);
        memberStatusLog.setMember(_member);
        memberStatusLog.setDate(new Date());
        _member.getMemberStatusLogList().add(saveMemberStatusCommand(memberStatusLog));
    }

    private final void saveMember() throws LogicException {
        saveMemberCommand.initCommand(_member);
        saveMemberCommand.execute();
        _response = saveMemberCommand.getResults();
    }

    private final MemberStatusLog saveMemberStatusCommand(MemberStatusLog memberStatusLog) throws LogicException {
        saveMemberStatusLog.initCommand(memberStatusLog);
        saveMemberStatusLog.execute();
        return saveMemberStatusLog.getResults();
    }
}
