package org.wsu.cs4850.foundationsofai.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Clause {

    protected List<Literal> _literals;

    public Clause(List<Literal> literals) {
        _literals = literals;
    }

    public Clause(Literal... literals) {
        _literals = (literals == null)
            ? new ArrayList<>()
            : Arrays.asList(literals);
    }

    public List<Literal> getLiterals() {
        return _literals;
    }

    public Clause resolve(List<Clause> database) {
        if (_literals.isEmpty())
            return null;

        // Get all the literals from the database
        Set<Literal> databaseLiterals = database.stream()
            .flatMap(clause -> clause._literals.stream())
            .collect(Collectors.toSet());

        // Filter out resolved literals
        List<Literal> resolution = new ArrayList<>();
        _literals.forEach(literal -> {
            Literal complement = literal.negated();
            boolean databaseContainsComplement = databaseLiterals.contains(complement);
            if (!databaseContainsComplement)
                resolution.add(literal);
        });

        return new Clause(resolution);
    }
}
