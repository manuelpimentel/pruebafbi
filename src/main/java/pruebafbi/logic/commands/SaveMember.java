package pruebafbi.logic.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebafbi.entities.Member;
import pruebafbi.exceptions.LogicException;
import pruebafbi.logic.Command;
import pruebafbi.repositories.MemberRepository;

@Service
public class SaveMember extends Command<Member> {

    private Member _member;
    private Member _response;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void initCommand(Object... args) {
        _member = (Member) args[0];
    }

    @Override
    public void execute() throws LogicException {
        try {
            _response = memberRepository.saveAndFlush(_member);
        }catch (Exception e)
        {
            throw new LogicException("MEMBER_NOT_SAVED");
        }
    }

    @Override
    public Member getResults() {
        return _response;
    }
}
