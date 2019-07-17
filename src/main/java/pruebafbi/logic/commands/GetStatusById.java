package pruebafbi.logic.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebafbi.entities.Status;
import pruebafbi.exceptions.LogicException;
import pruebafbi.logic.Command;
import pruebafbi.repositories.StatusRepository;

import java.util.Optional;

@Service
public class GetStatusById extends Command<Status> {

    private Status _status;
    private Status _response;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public void initCommand(Object... args) {
        _status = (Status) args[0];
    }

    @Override
    public void execute() throws LogicException {
        final Optional<Status> optional = statusRepository.findById(_status.getId());
        if (!optional.isPresent())
        {
            throw new LogicException("STATUS_NOT_FOUND");
        }
        _response = optional.get();
    }

    @Override
    public Status getResults() {
        return _response;
    }
}
