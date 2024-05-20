package visitor;

import java.io.*;

public abstract class Visitor {
    Serializable place;

    public Visitor(Serializable place) {
        this.place = place;
    }

    public abstract void visit();
}
