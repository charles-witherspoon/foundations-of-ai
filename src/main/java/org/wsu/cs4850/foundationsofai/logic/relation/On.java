package org.wsu.cs4850.foundationsofai.logic.relation;

import org.wsu.cs4850.foundationsofai.Block;

public class On extends Relation {

    private final Block _upper;
    private final Block _lower;

    public On(Block upper, Block lower) {
        _isTrue = true;
        _upper = upper;
        _lower = lower;
    }

    public Block getUpper() {
        return _upper;
    }

    public Block getLower() {
        return _lower;
    }

    @Override
    public On negated() {
        On neg = new On(_upper, _lower);
        neg.negate();
        return neg;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof On))
            return false;

        On other = (On) o;

        boolean upperIsVariable = this._upper == null || other._upper == null;
        boolean lowerIsVariable = this._lower == null || other._lower == null;

        return (upperIsVariable || other._upper.equals(this._upper))
            && (lowerIsVariable || other._lower.equals(this._lower));
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = _upper == null
            ? hash
            : 31 * hash + _upper.hashCode();
        hash = _lower == null
            ? hash
            : 31 * hash + _lower.hashCode();

        return hash;
    }
}
