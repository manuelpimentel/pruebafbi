package pruebafbi.logic.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebafbi.entities.*;
import pruebafbi.exceptions.LogicException;
import pruebafbi.logic.Command;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnableMember extends Command<Member> {

    private Member _member;
    private Status _status;
    private Member _response;

    @Autowired
    private GetMemberById getMemberById;
    @Autowired
    private ChangeMemberStatus changeMemberStatus;
    @Autowired
    private ChangeMemberRank changeMemberRank;
    @Autowired
    private SaveMemberBossActivity saveMemberBossActivity;
    @Autowired
    private SaveMember saveMember;
    @Autowired
    private FindOldSubordinates findOldSubordinates;
    @Autowired
    private UtilFunctions utilFunctions;

    @Override
    public void initCommand(Object... args) {
        _member = (Member) args[0];
        _status = EntityFactory.instanceStatus();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void execute() throws LogicException {
        _status.setName(Status.status.Active.getStatus());
        findMember();
        if (utilFunctions.getActualRank(_member).getName().equalsIgnoreCase(Rank.rank.Boss.getRank())){
            updateSubordinates();
        }
        changeMemberStatusCommand();
        _response = saveMemberCommand(_member);
    }

    @Override
    public Member getResults() {
        return _response;
    }

    private final void findMember() throws LogicException
    {
        getMemberById.initCommand(_member);
        getMemberById.execute();
        _member = getMemberById.getResults();
    }

    private final void changeMemberStatusCommand() throws LogicException
    {
        changeMemberStatus.initCommand(_member, _status);
        changeMemberStatus.execute();
        _member = changeMemberStatus.getResults();
    }

    private final void updateSubordinates() throws LogicException
    {
        findOldSubordinates.initCommand(_member);
        findOldSubordinates.execute();
        final List<Member> memberList = findOldSubordinates.getResults();

        for ( Member m : memberList )
        {
            final Optional<MemberBossActivity> optionalMember = m.getBossActivityList()
                    .stream().filter( mba -> mba.getActual() ).findFirst();
            if (optionalMember.isPresent())
            {
                final MemberBossActivity mba = optionalMember.get();
                mba.setActual(false);
                saveMemberBossActivityCommand(mba);
            }

            if (m.getId() != _member.getId()) {
                final MemberBossActivity memberBossActivity = EntityFactory.instanceMemberBossActivity();
                memberBossActivity.setActual(true);
                memberBossActivity.setDate(new Date());
                memberBossActivity.setMember(m);
                memberBossActivity.setBoss(_member);
                _member.getBossActivityList().add(saveMemberBossActivityCommand(memberBossActivity));

                if (utilFunctions.getActualRank(m).getName().equalsIgnoreCase(Rank.rank.Boss.getRank()))
                {
                    updateMemberRank(m);
                }

            }
        }

    }

    private final MemberBossActivity saveMemberBossActivityCommand(MemberBossActivity memberBossActivity)
            throws LogicException {
        saveMemberBossActivity.initCommand(memberBossActivity);
        saveMemberBossActivity.execute();
        return saveMemberBossActivity.getResults();
    }

    private final Member saveMemberCommand(Member member) throws LogicException
    {
        saveMember.initCommand(member);
        saveMember.execute();
        return saveMember.getResults();
    }

    private final void updateMemberRank(Member member) throws LogicException
    {
        final Rank rank = EntityFactory.instanceRank();
        rank.setName(Rank.rank.Subordinated.getRank());
        changeMemberRank.initCommand(member, rank);
        changeMemberRank.execute();
    }


}
