package org.wsu.cs4850.foundationsofai.logic;

import org.wsu.cs4850.foundationsofai.action.Action;

public class Gamma extends Clause {

    private Action _answer;

    public Gamma(Literal... literals) {
        super(literals);
    }
}
