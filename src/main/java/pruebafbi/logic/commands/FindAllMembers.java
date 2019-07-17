package pruebafbi.logic.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebafbi.entities.Member;
import pruebafbi.exceptions.LogicException;
import pruebafbi.logic.Command;
import pruebafbi.repositories.MemberRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindAllMembers extends Command<List<Member>> {

    private List<Member> _response;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void initCommand(Object... args) {
        _response = new ArrayList<>();
    }

    @Override
    public void execute() throws LogicException {
        _response = memberRepository.findAll();
    }

    @Override
    public List<Member> getResults() {
        return _response;
    }
}
