package org.wsu.cs4850.foundationsofai.logic.relation;

import org.wsu.cs4850.foundationsofai.Block;

public class Table extends Relation{

    private final Block _block;

    public Table(Block block) {
        _isTrue = true;
        _block = block;
    }

    public Block getBlock() {
        return _block;
    }

    @Override
    public Table negated() {
        Table neg = new Table(_block);
        neg.negate();
        return neg;
    }
}
