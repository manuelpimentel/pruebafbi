package pruebafbi.logic.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebafbi.entities.Rank;
import pruebafbi.exceptions.LogicException;
import pruebafbi.logic.Command;
import pruebafbi.repositories.RankRepository;

import java.util.Optional;

@Service
public class GetRankById extends Command<Rank> {

    private Rank _rank;
    private Rank _response;

    @Autowired
    private RankRepository rankRepository;

    @Override
    public void initCommand(Object... args) {
        _rank = (Rank) args[0];
    }

    @Override
    public void execute() throws LogicException {
        final Optional<Rank> optional = rankRepository.findById(_rank.getId());
        if (!optional.isPresent())
        {
            throw new LogicException("RANK_NOT_FOUND");
        }
        _response = optional.get();
    }

    @Override
    public Rank getResults() {
        return _response;
    }
}
