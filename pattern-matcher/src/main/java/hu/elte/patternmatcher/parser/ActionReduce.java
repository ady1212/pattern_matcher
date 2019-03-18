package hu.elte.patternmatcher.parser;

public class ActionReduce extends Action {
    private ProductionRule productionRule;

    public ActionReduce(ProductionRule productionRule) {
        this.productionRule = productionRule;
    }

    public ProductionRule getProductionRule() {
        return this.productionRule;
    }

    @Override
    public int getInt() {
        // Returns the number of symbols to pop off the stack.
        return this.productionRule.getRighthand().length;
    }

    @Override
    public String toString() {
        return "<Action> Reduce by " + this.productionRule.toString();
    }
}
