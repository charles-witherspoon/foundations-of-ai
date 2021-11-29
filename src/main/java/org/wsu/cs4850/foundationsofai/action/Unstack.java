package org.wsu.cs4850.foundationsofai.action;

import org.wsu.cs4850.foundationsofai.Block;
import org.wsu.cs4850.foundationsofai.core.State;
import org.wsu.cs4850.foundationsofai.logic.Clause;
import org.wsu.cs4850.foundationsofai.logic.Literal;
import org.wsu.cs4850.foundationsofai.logic.relation.Ans;
import org.wsu.cs4850.foundationsofai.logic.relation.Clear;
import org.wsu.cs4850.foundationsofai.logic.relation.On;
import org.wsu.cs4850.foundationsofai.logic.relation.T;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description:
 * T(On(x,y),s) ∧ T(Clear(x),s) => T(Table(x),Do(U(x,y),s)) ∧ T(Clear(y),Do(U(x,y),s))
 */
public class Unstack implements Action {

    private Block _upper;

    private Block _lower;

    public Unstack(Block upper, Block lower) {
        _upper = upper;
        _lower = lower;
    }

    @Override
    public State apply() {
        return null;
    }

    /**
     * Returns a list containing clauses that satisfy the preconditions for unstack;
     * Pre-conditions: T(On(x,y),s) ∧ T(Clear(x),s)
     * @param database Clauses containing facts about the state; assumes that the clauses are unit clauses
     * @return List of clauses containing possible (negated) preconditions; Empty if preconditions are unsatisfiable
     */
    public static List<Clause> getPreconditions(List<Clause> database, int state) {
        List<Clause> preConditions = new ArrayList<>();

        // Put all database facts (unit clauses) into a set of literals for quick membership test
        Set<Literal> stateDependentProperties = database.stream()
            .map(clause -> clause.getLiterals().get(0))
            .collect(Collectors.toSet());

        // T(Clear(x),s)
        List<Clear> clears = getClearRelations(stateDependentProperties);
        if (clears.isEmpty())
            return preConditions;

        // T(On(x,y),s)
        clears.forEach(clear -> {
            On on = getPossibleOn(clear, stateDependentProperties);
            if (on != null)
                preConditions.add(new Clause(
                    new T(on, state),
                    new T(clear, state),
                    new Ans(new Unstack(on.getUpper(), on.getLower()))
                ));
        });

        return preConditions;
    }

    private static List<Clear> getClearRelations(Set<Literal> sdp) {
        return sdp.stream()
            .filter(Unstack::literalContainsClear)
            .map(Unstack::getClearFromTLiteral)
            .collect(Collectors.toList());
    }

    private static boolean literalContainsClear(Literal literal) {
        return (literal instanceof T)
            && (((T) literal).getRelation() instanceof Clear);
    }

    private static Clear getClearFromTLiteral(Literal literal) {
        Clear source = (Clear) ((T) literal).getRelation();
        return new Clear(source.getBlock());
    }

    private static On getPossibleOn(Clear clear, Set<Literal> sdp) {
        return sdp.stream()
            .filter(l -> literalContainsPossibleOn(clear, l))
            .map(Unstack::getOnFromTLiteral)
            .findFirst()
            .orElse(null);
    }

    private static boolean literalContainsPossibleOn(Clear clear, Literal literal) {
        if (!(literal instanceof T))
            return false;

        T t = (T) literal;
        if (! (t.getRelation() instanceof On))
            return false;

        On on = (On) t.getRelation();

        return clear.getBlock().equals(on.getUpper());
    }

    private static On getOnFromTLiteral(Literal literal) {
        On source = (On) ((T) literal).getRelation();
        return new On(source.getUpper(), source.getLower());
    }
}
