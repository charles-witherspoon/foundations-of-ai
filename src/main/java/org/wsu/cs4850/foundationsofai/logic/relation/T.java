package org.wsu.cs4850.foundationsofai.logic.relation;

import org.wsu.cs4850.foundationsofai.logic.Literal;

public class T extends Literal {
    Relation _relation;
    int _state;

    public T(Relation relation, int state) {
        _relation = relation;
        _state = state;
    }

    public Relation getRelation() {
        return _relation;
    }

    public int getState() {
        return _state;
    }

    @Override
    public T negated() {
        T neg = new T(_relation, _state);
        neg.negate();
        return neg;
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof T))
            return false;

        T other = (T) o;

        return other._state == this._state
            && other._relation.equals(this._relation);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + _state;
        return _relation == null
            ? hash
            : 31 * hash + _relation.hashCode();
    }
}
