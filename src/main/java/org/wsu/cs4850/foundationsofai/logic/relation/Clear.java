package org.wsu.cs4850.foundationsofai.logic.relation;

import org.wsu.cs4850.foundationsofai.Block;

public class Clear extends Relation {

    private final Block _block;

    public Clear(Block block) {
        _isTrue = true;
        _block = block;
    }

    public Block getBlock() {
        return _block;
    }

    @Override
    public Clear negated() {
        Clear neg = new Clear(_block);
        neg.negate();
        return neg;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Clear))
            return false;

        return ((Clear) o)._block.equals(this._block);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        return _block == null
            ? hash
            : 31 * hash + _block.hashCode();
    }
}
