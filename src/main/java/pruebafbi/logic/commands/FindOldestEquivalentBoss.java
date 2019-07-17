package pruebafbi.logic.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebafbi.entities.EntityFactory;
import pruebafbi.entities.Member;
import pruebafbi.entities.Rank;
import pruebafbi.exceptions.LogicException;
import pruebafbi.logic.Command;

import java.util.Comparator;
import java.util.Optional;

@Service
public class FindOldestEquivalentBoss extends Command<Member> {

    private Member _member;
    private Member _response;

    @Autowired
    private UtilFunctions utilFunctions;
    @Autowired
    private FindAllMembers findAllMembers;
    @Autowired
    private SaveMember saveMember;
    @Autowired
    private ChangeMemberRank changeMemberRank;

    @Override
    public void initCommand(Object... args) {
        _member = (Member) args[0];
    }

    @Override
    public void execute() throws LogicException
    {
        findAllMembers.initCommand();
        findAllMembers.execute();

        final Optional<Member> optionalMember = findAllMembers.getResults().stream()
                .sorted( Comparator.comparing( Member::getAdmissionDate ) )
                .filter( m -> _member.getId() != m.getId() )
                .filter( m -> utilFunctions.getActualRank(m) == utilFunctions.getActualRank(_member) )
                .findFirst();
        if (optionalMember.isPresent())
        {
            _response = optionalMember.get();
        }
        else {
            final Optional<Member> optionalMember1 = findAllMembers.getResults().stream()
                    .sorted( Comparator.comparing( Member::getAdmissionDate ).reversed() )
                    .filter( m -> _member.getId() != m.getId() )
                    .filter( m -> utilFunctions.getActualRank(m) != utilFunctions.getActualRank(_member) )
                    .findFirst();
            if (!optionalMember1.isPresent())
            {
                throw new LogicException("NO_OLDER_BOSS_AVAILABLE");
            }

            _response = updateMemberRank(optionalMember1.get());
        }
    }

    @Override
    public Member getResults() {
        return _response;
    }

    private final Member saveMemberCommand(Member member) throws LogicException
    {
        saveMember.initCommand(member);
        saveMember.execute();
        return saveMember.getResults();
    }

    private final Member updateMemberRank(Member member) throws LogicException {
        final Rank rank = EntityFactory.instanceRank();
        rank.setName(Rank.rank.Boss.getRank());
        changeMemberRank.initCommand(member, rank);
        changeMemberRank.execute();
        return changeMemberRank.getResults();
    }


}
