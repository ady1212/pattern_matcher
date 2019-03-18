package hu.elte.patternmatcher.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Holds a transition table constructed after reading an lr1 transition file. Rules are extracted as string arrays through the method getTransition()
 */

public class LrParser {
    
    private static final Logger logger = LoggerFactory.getLogger(LrParser.class);

    private Set<String> terminalSymbols;
    private Set<String> nonTerminalSymbols;
    private String startSymbol;
    private List<ProductionRule> productionRules;
    private int numOfStates;
    private Map<Integer, Map<String, Action>> transitionRules;

    public LrParser(File lr1File) throws Exception {

        this.terminalSymbols = new HashSet<String>();
        this.nonTerminalSymbols = new HashSet<String>();
        this.startSymbol = null;
        this.productionRules = new ArrayList<ProductionRule>();
        this.numOfStates = 0;
        this.transitionRules = new HashMap<Integer, Map<String, Action>>();

        parseFile(lr1File);
    }

    private void parseFile(File lr1File) throws Exception {
        FileInputStream inputSteam = new FileInputStream(lr1File);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputSteam));

        int remainLines = 0;
        LR1Struct stage = LR1Struct.BOF;
        while (reader.ready() && stage != LR1Struct.EOF) {
            String line = reader.readLine();
            String[] split = line.split("\\s");
            logger.info("Read in line: " + line);

            if (remainLines == 0) {
                stage = LR1Struct.values()[stage.ordinal() + 1];
                logger.info("Stage changes to " + stage.toString());

                // Deal with special cases, or read new remainLines
                if (stage == LR1Struct.StartSymbol)
                    this.startSymbol = split[0];
                else if (stage == LR1Struct.NumOfStates)
                    this.numOfStates = Integer.parseInt(split[0]);
                else
                    remainLines = Integer.parseInt(split[0]);
                continue;
            }
            remainLines--;

            switch (stage) {
            case TerminalSymbols:
                this.terminalSymbols.add(split[0]);
                break;
            case NonTerminalSymbols:
                this.nonTerminalSymbols.add(split[0]);
                break;
            case ProductionRules:
                this.productionRules.add(new ProductionRule(split));
                break;
            case TransitionRules:
                this.parseTransition(split);
                break;
            case BOF:
            case StartSymbol:
            case NumOfStates:
            case EOF:
            default:
                break;
            }
        }
        logger.info("LR1 Table constructed with:\n" + this.terminalSymbols.size() + " terminal symbols\n" + this.nonTerminalSymbols.size()
                + " non terminal symbols\n" + this.startSymbol + " as the start symbol\n" + this.productionRules.size() + " production rules\n"
                + this.numOfStates + " number of states\n" + this.transitionRules.size() + " transition rules");

        reader.close();
    }

    private void parseTransition(String[] split) {
        assert split.length == 4 : "A LR1 transition must have 4 components";

        // NOTE: split[0] was being passed as a key for transitionRules without parsing it first
        logger.info("Parsing: " + split[0] + " " + split[1] + " " + split[2] + " " + split[3]);

        // Find all rules for the given state, if nothing, create and add a new Map
        int state = Integer.parseInt(split[0]);
        Map<String, Action> rulesForState = transitionRules.get(state);
        if (rulesForState == null) {
            rulesForState = new HashMap<String, Action>();
            this.transitionRules.put(state, rulesForState);
        }

        Action action = null;
        if (split[2].equals("reduce")) {
            ProductionRule productionRule = this.productionRules.get(Integer.parseInt(split[3]));
            action = new ActionReduce(productionRule);
        } else if (split[2].equals("shift")) {
            action = new ActionShift(Integer.parseInt(split[3]));
        } else {
            logger.warn("Reach unknown action type");
        }
        rulesForState.put(split[1], action);
        logger.info("TransitionRule added: " + state + " " + split[1] + " -> " + action.toString());
    }

    /**
     * Get the next transition action of the given state and tokenKind
     * 
     * @param state
     *            The current state of the parser
     * @param Token
     *            The lookahead token kind
     * @return An Action that is either shift or reduce
     */
    public Action actionFor(int state, String tokenKind) {
        Action action = null;
        Map<String, Action> rulesForState = this.transitionRules.get(state);
        if (rulesForState == null) {
            logger.info("NULL Transition Rule map returned for state: " + state);
            return null;
        }
        action = rulesForState.get(tokenKind);
        return action;
    }

    public String getStartSymbol() {
        return startSymbol;
    }

    public int getNumOfStates() {
        return numOfStates;
    }

    public boolean isTerminalSymbol(String symbol) {
        return this.terminalSymbols.contains(symbol);
    }

    public boolean isNonTerminalSymbol(String symbol) {
        return this.nonTerminalSymbols.contains(symbol);
    }
}
