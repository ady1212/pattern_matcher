package hu.elte.patternmatcher.parser;

public class ActionShift extends Action {
    private int toState;

    public ActionShift(int toState) {
        this.toState = toState;
    }

    public int getToState() {
        return this.toState;
    }

    @Override
    public int getInt() {
        return this.toState;
    }

    @Override
    public String toString() {
        return "<Action> Shift to " + String.valueOf(this.toState);
    }
}
