package com.snakesandladders.classes.objects;

import com.snakesandladders.interfaces.ITransportable;

public abstract class ATransportationMethod implements ITransportable {
    protected int startPosition;
    protected int endPosition;

    public ATransportationMethod(int startPosition, int endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public int getStartPosition() {
        return this.startPosition;
    }

    public int getEndPosition() {
        return this.endPosition;
    }

}
