package pruebafbi.logic.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebafbi.entities.MemberStatusLog;
import pruebafbi.exceptions.LogicException;
import pruebafbi.logic.Command;
import pruebafbi.repositories.MemberStatusLogRepository;

@Service
public class SaveMemberStatusLog extends Command<MemberStatusLog> {

    private MemberStatusLog _memberStatusLog;
    private MemberStatusLog _response;

    @Autowired
    private MemberStatusLogRepository memberStatusLogRepository;

    @Override
    public void initCommand(Object... args) {
        _memberStatusLog = (MemberStatusLog) args[0];
    }

    @Override
    public void execute() throws LogicException {
        try {
            _response = memberStatusLogRepository.save(_memberStatusLog);
        }catch (Exception e)
        {
            throw new LogicException("MEMBER_STATUS_LOG_NOT_SAVED");
        }
    }

    @Override
    public MemberStatusLog getResults() {
        return _response;
    }
}
