package pruebafbi.logic.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebafbi.entities.*;
import pruebafbi.exceptions.LogicException;
import pruebafbi.logic.Command;
import pruebafbi.repositories.MemberRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetSpecialBosses extends Command<List<Member>> {

    private List<Member> _memberList;
    private List<Member> _response;

    @Autowired
    private FindAllMembers findAllMembers;

    @Override
    public void initCommand(Object... args) {
        _memberList = new ArrayList<>();
        _response = new ArrayList<>();
    }

    @Override
    public void execute() throws LogicException {
        findAllMembers.initCommand();
        findAllMembers.execute();
        _memberList = findAllMembers.getResults();

        for (Member m : _memberList)
        {
            if (   validateStatusAndRank(m) &&
                    m.getBossActivityList().stream()
                            .filter( mm -> mm.getActual() ).collect(Collectors.toList()).size() >= 50
            ){
                _response.add(m);
            }
        }

    }

    @Override
    public List<Member> getResults() {
        return _response;
    }

    private final Boolean validateStatusAndRank(Member member) throws LogicException {
        final Optional<MemberStatusLog> optionalMSL = member.getMemberStatusLogList().stream()
                .sorted( Comparator.comparing( MemberStatusLog::getDate ).reversed() )
                .findFirst();
        if (!optionalMSL.isPresent())
        {
            throw new LogicException("INVALID_STATUS");
        }
        final Optional<MemberRankLog> optionalMRL = member.getMemberRankLogList().stream()
                .sorted( Comparator.comparing( MemberRankLog::getDate ).reversed() )
                .findFirst();
        if (!optionalMRL.isPresent())
        {
            throw new LogicException("INVALID_RANK");
        }

        return (optionalMRL.get().getRank().getName().equalsIgnoreCase(Rank.rank.Boss.getRank()) &&
                optionalMSL.get().getStatus().getName().equalsIgnoreCase(Status.status.Active.getStatus()));
    }
}
