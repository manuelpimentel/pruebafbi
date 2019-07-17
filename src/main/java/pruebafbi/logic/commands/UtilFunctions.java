package pruebafbi.logic.commands;

import org.springframework.stereotype.Service;
import pruebafbi.entities.*;

import java.util.Comparator;

@Service
public class UtilFunctions {

    public static final Rank getActualRank( Member member )
    {
        return member.getMemberRankLogList().stream()
                .sorted( Comparator.comparing( MemberRankLog::getDate ).reversed() )
                .findFirst().get().getRank();
    }

    public static final Status getActualStatus(Member member )
    {
        return member.getMemberStatusLogList().stream()
                .sorted( Comparator.comparing( MemberStatusLog::getDate ).reversed() )
                .findFirst().get().getStatus();
    }

}
