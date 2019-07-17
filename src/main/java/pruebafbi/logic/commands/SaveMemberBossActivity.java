package pruebafbi.logic.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebafbi.entities.Member;
import pruebafbi.entities.MemberBossActivity;
import pruebafbi.exceptions.LogicException;
import pruebafbi.logic.Command;
import pruebafbi.repositories.MemberBossActivityRepository;

@Service
public class SaveMemberBossActivity extends Command<MemberBossActivity> {

    private MemberBossActivity _memberBossActivity;
    private MemberBossActivity _response;

    @Autowired
    private MemberBossActivityRepository memberBossActivityRepository;

    @Override
    public void initCommand(Object... args) {
        _memberBossActivity = (MemberBossActivity) args[0];
    }

    @Override
    public void execute() throws LogicException {
        try {
            _response = memberBossActivityRepository.save(_memberBossActivity);
        }catch (Exception e)
        {
            throw new LogicException("MEMBER_BOSS_ACTIVITY_NOT_SAVED");
        }
    }

    @Override
    public MemberBossActivity getResults() {
        return _response;
    }
}
