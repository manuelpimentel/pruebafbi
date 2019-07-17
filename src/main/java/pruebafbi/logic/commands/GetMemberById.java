package pruebafbi.logic.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebafbi.entities.Member;
import pruebafbi.exceptions.LogicException;
import pruebafbi.logic.Command;
import pruebafbi.repositories.MemberRepository;

import java.util.Optional;

@Service
public class GetMemberById extends Command<Member> {

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
        final Optional<Member> optional = memberRepository.findById(_member.getId());
        if (!optional.isPresent())
        {
            throw new LogicException("MEMBER_NOT_FOUND");
        }
        _response = optional.get();
    }

    @Override
    public Member getResults() {
        return _response;
    }
}
