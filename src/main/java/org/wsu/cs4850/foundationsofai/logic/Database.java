package org.wsu.cs4850.foundationsofai.logic;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private final List<Clause> _initialDatabase;

    private final Gamma _gamma;



    public Database(List<Clause> initialDatabase, Gamma gamma) {
        _initialDatabase = new ArrayList<>(initialDatabase);
        _gamma = gamma;
    }

    public void addToDatabase(Clause clause) {

    }
}
