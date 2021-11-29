package org.wsu.cs4850.foundationsofai;

import org.wsu.cs4850.foundationsofai.action.*;
import org.wsu.cs4850.foundationsofai.core.State;
import org.wsu.cs4850.foundationsofai.logic.Clause;
import org.wsu.cs4850.foundationsofai.logic.Gamma;
import org.wsu.cs4850.foundationsofai.logic.Literal;
import org.wsu.cs4850.foundationsofai.logic.relation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.wsu.cs4850.foundationsofai.action.Actions.UNSTACK;

public class App {

    public static void main(String[] args) {

        Block A = new Block("A");
        Block B = new Block("B");
        Block C = new Block("C");

        // Start state:
        // T(Clear(A), S1)
        // T(On(A, B), S1)
        // T(On(B, C), S1)
        // T(Table(C), S1)
        State S1 = new State(List.of(
            new Table(C),
            new On(B,C),
            new On(A,B),
            new Clear(A)
        ));

        // Goal state:
        // T(Table(A), t)  Goal(t)
        State goal = new State(List.of(
            new Table(A)
        ));

        // Green Method:
        // 1. Set up initial database
        List<Clause> initialDatabase = List.of(
            new Clause(
                new T(new Table(C), 1)),
            new Clause(
                new T(new On(B,C), 1)),
            new Clause(
                new T(new On(A,B), 1)),
            new Clause(
                new T(new Clear(A), 1))
        );


        // 2. Add gamma clause, which is FIBQ
        //   * {¬Goal (Do(a,S1)), Ans(a)}
        //      - T(Table(A), t)  Goal(t)
        Clause gamma = new Gamma(
            new T(new Table(A), -1).negated(), new Ans(null));

        // 3. Replace gamma clause with its deduction
        //    * {¬T(Table(A),Do(a,S1)), Ans(a)}

        // 4. Pick an action
        //    * How?
        //      - Try them all

        // 5. Use the description (primary effects) of the action to replace the consequent with its antecedent
        //    * The antecedent describes the previous state
        //      - In our case (we've determined U to be the action to perform),
        //        this means the antecedent - T(On(A,y),S1) ∧ T(Clear(A),S1) - describes the state S1
        //    * {¬T(On(A,y),S1), ¬T(Clear(A),S1), Ans(U(A,y))}
//        List<Clause> resolutions = checkAction(UNSTACK, gamma, initialDatabase);

        // 6. Find proper substitution for remaining variables
        //    * {¬T(On(A,B),S1), ¬T(Clear(A),S1), Ans(U(A,B))}

        // 7. Use linear/unit resolution
        //    * The initial database contains the complements for ¬T(On(A,y),S1) and ¬T(Clear(A),S1)
        //    * Final answer: {Ans(U(A,y))}
//        List<Clause> answers = resolutions.stream()
//            .map(resolution -> resolution.resolve(initialDatabase))
//            .collect(Collectors.toList());

        for (Actions action : Actions.values()) {
            List<Clause> resolutions = checkAction(action, gamma, initialDatabase);

            List<Clause> answers = resolutions.stream()
                .map(resolution -> resolution.resolve(initialDatabase))
                .collect(Collectors.toList());

            System.out.println(action + ": " + answers.size());
        }

    }

    // Algorithm, since every database 'truth' will need to be tested, once a plan is determined that will satisfy one truth but not the others,
    // find another action to satisfy the others. Determine whether it goes before or after.
    // If the new action axioms allow the current state to be unchanged, it goes before, and vice versa
    // Still start from the table blocks
//
//    private static Action getPlan(State goal, State initialState) {
//        // Try each action ->
//        // Use axiom to shrink database; Ex: database contains ¬T(Table(A), so find an axiom for T(Table(u),s)
//
//
//    }

    private static List<Clause> checkAction(Actions action, Clause delta, List<Clause> initialDatabase) {

        switch (action) {
            case UNSTACK:
                return Unstack.getPreconditions(initialDatabase, 1);
            case PICK_UP:
                return PickUp.getPreconditions(initialDatabase, 1);
            case MOVE:
                return Move.getPreconditions(initialDatabase, 1);
            case PUT_DOWN:
                return PutDown.getPreconditions(initialDatabase, 1);
            case STACK:
                return Stack.getPreconditions(initialDatabase, 1);
            case NOOP:
            default:
                return new ArrayList<>();
        }
    }
}
