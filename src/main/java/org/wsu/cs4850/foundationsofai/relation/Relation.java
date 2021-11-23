package org.wsu.cs4850.foundationsofai.relation;

import org.wsu.cs4850.foundationsofai.core.State;

import java.util.Set;


public abstract class Relation {

    /**
     * Determines if a state contains necessary state-dependent properties
     * @param state State to check
     * @return TRUE if state is a fluent; FALSE otherwise
     */
    public abstract boolean checkIfFluent(State state);

    // TODO Before doing the below line, design the algorithm by creating a simulation on whiteboard or something
    // TODO set these to return functions (or suppliers) that will generate the relations
    protected Set<Relation> requiredStateDescriptors;

    protected Set<Relation> newStateDescriptors;
}
