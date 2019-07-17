package pruebafbi.logic;

import pruebafbi.exceptions.LogicException;

public abstract class Command<T> {

    public abstract void initCommand(Object... args);
    public abstract void execute() throws LogicException;
    public abstract T getResults();

}