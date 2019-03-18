package hu.elte.patternmatcher.parser;

public class ProductionRule {
    private String lefthand;
    private String[] righthand;

    public ProductionRule(String[] rule) {
        this.lefthand = rule[0];
        this.righthand = new String[rule.length - 1];
        for (int i = 1; i < rule.length; i++) {
            this.righthand[i - 1] = rule[i];
        }
    }

    public String getLefthand() {
        return this.lefthand;
    }

    public String[] getRighthand() {
        return this.righthand;
    }

    @Override
    public String toString() {
        String string = "<ProductionRule> " + this.lefthand + " ->";
        for (String r : this.righthand) {
            string = string + " " + r;
        }
        return string;
    }
}
