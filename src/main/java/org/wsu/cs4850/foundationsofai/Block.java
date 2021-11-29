package org.wsu.cs4850.foundationsofai;

public class Block {

    private final String _value;

    public Block(String value) {
        _value = value;
    }

    public String getValue() {
        return _value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Block))
            return false;

        return ((Block) o)._value.equals(this._value);
    }

    @Override
    public int hashCode() {
        return _value.hashCode();
    }
}
