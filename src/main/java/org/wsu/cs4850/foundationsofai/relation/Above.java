package org.wsu.cs4850.foundationsofai.relation;

import org.wsu.cs4850.foundationsofai.core.State;

public class Above extends Relation {
    @Override
    public boolean checkIfFluent(State state) {
        return false;
    }
}
