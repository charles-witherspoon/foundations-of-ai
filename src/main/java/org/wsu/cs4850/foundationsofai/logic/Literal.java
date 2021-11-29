package org.wsu.cs4850.foundationsofai.logic;

public abstract class Literal {

    protected boolean _isTrue;

    public boolean isTrue() {
        return _isTrue;
    }

    public void negate() {
        _isTrue = !_isTrue;
    }

    public abstract Literal negated();

}
