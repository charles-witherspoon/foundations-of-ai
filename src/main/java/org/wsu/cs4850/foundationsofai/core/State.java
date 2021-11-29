package org.wsu.cs4850.foundationsofai.core;

import org.wsu.cs4850.foundationsofai.logic.relation.Relation;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class State {
    private Set<Relation> _properties;

    public State(Collection<Relation> relations) {
        _properties = new HashSet<>(relations);
    }

    public Set<Relation> getDatabase() {
        return _properties;
    }
}
