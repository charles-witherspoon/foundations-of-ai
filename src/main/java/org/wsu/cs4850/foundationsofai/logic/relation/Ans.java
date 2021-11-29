package org.wsu.cs4850.foundationsofai.logic.relation;

import org.wsu.cs4850.foundationsofai.action.Action;
import org.wsu.cs4850.foundationsofai.logic.Literal;

public class Ans extends Literal {
    Action _action;

    public Ans(Action action) {
        _action = action;
    }

    public Action getAction() {
        return _action;
    }

    @Override
    public Ans negated() {
        Ans neg = new Ans(_action);
        neg.negate();
        return neg;
    }
}
