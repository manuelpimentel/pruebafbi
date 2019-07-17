package pruebafbi.logic.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebafbi.entities.*;
import pruebafbi.exceptions.LogicException;
import pruebafbi.logic.Command;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisableMember extends Command<Member> {

    private Member _member;
    private Status _status;
    private Member _response;

    @Autowired
    private GetMemberById getMemberById;
    @Autowired
    private ChangeMemberStatus changeMemberStatus;
    @Autowired
    private FindOldestEquivalentBoss findOldestEquivalentBoss;
    @Autowired
    private SaveMemberBossActivity saveMemberBossActivity;
    @Autowired
    private SaveMember saveMember;
    @Autowired
    private UtilFunctions utilFunctions;
    @Autowired
    private GetStatusById getStatusById;

    @Override
    public void initCommand(Object... args) {
        _member = (Member) args[0];
        _status = (Status) args[1];
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void execute() throws LogicException {
        findMember();
        findStatus();
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
        findOldestEquivalentBoss.initCommand( _member );
        findOldestEquivalentBoss.execute();
        final Member boss = findOldestEquivalentBoss.getResults();

        final Optional<MemberBossActivity> optional = boss.getMemberActivityList()
                .stream().filter( ba -> ba.getActual()).findFirst();

        if (optional.isPresent())
        {
            final MemberBossActivity mba = optional.get();
            mba.setActual(false);
            saveMemberBossActivityCommand(mba);
        }

        for (MemberBossActivity mba : _member.getBossActivityList().stream()
                .filter( mba -> mba.getActual()).collect(Collectors.toList()))
        {
            mba.setActual(false);
            saveMemberBossActivityCommand(mba);
            if (mba.getMember().getId() != boss.getId()) {
                final MemberBossActivity memberBossActivity = EntityFactory.instanceMemberBossActivity();
                memberBossActivity.setActual(true);
                memberBossActivity.setDate(new Date());
                memberBossActivity.setMember(mba.getMember());
                memberBossActivity.setBoss(boss);
                boss.getBossActivityList().add(saveMemberBossActivityCommand(memberBossActivity));
                saveMemberCommand(boss);
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

    private final void findStatus() throws LogicException
    {
        getStatusById.initCommand(_status);
        getStatusById.execute();
        _status = getStatusById.getResults();
    }


}
