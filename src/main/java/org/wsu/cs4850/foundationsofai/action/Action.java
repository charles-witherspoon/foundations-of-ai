package org.wsu.cs4850.foundationsofai.action;

import org.wsu.cs4850.foundationsofai.core.State;
import org.wsu.cs4850.foundationsofai.logic.Clause;

import java.util.List;

public interface Action {

    State apply();

    /**
     * Maps (an) action(s) and a state into the state that results from the action execution
     * @param actionBlock Actions to be executed sequentially
     * @param initialState The starting state
     * @return The state that results from the execution of the actions sequentially from the initial state
     */
    static State Do(Action[] actionBlock, State initialState) {
        return null;
    }

}
