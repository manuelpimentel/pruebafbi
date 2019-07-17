package pruebafbi.logic.commands;

import org.springframework.stereotype.Service;
import pruebafbi.entities.Member;
import pruebafbi.entities.MemberBossActivity;
import pruebafbi.entities.MemberStatusLog;
import pruebafbi.exceptions.LogicException;
import pruebafbi.logic.Command;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindOldSubordinates extends Command<List<Member>> {

    private Member _member;
    private List<Member> _response;

    @Override
    public void initCommand(Object... args) {
        _response = new ArrayList<>();
        _member = (Member) args[0];
    }

    @Override
    public void execute() throws LogicException {
        final Date date = _member.getMemberStatusLogList().stream()
                .sorted(Comparator.comparing(MemberStatusLog::getDate).reversed()).collect(Collectors.toList())
                .get(0).getDate();
        for (MemberBossActivity mba : _member.getBossActivityList().stream()
                .filter(ba -> ba.getDate().before(date))
                .collect(Collectors.toList())) {
            _response.add(mba.getMember());
        }
    }

    @Override
    public List<Member> getResults() {
        return _response;
    }
}
