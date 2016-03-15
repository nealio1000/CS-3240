/* DFA.java */
/**
 * DFA program with attributes of a DFA that include
 * a set of all states, a set containing the alphabet,
 * a Map that links each state with its transition function,
 * a starting state, a set of states that holds all of the
 * accept states, and a state that holds the current state.
 * @author Neal Friedman, Stephen Chavez
 * @version CSTheory (1)
 */
import java.util.Map;
import java.util.Set;
public class DFA{

    private Set<State> states;
    private Set<String> alphabet;
    private Map<State, Map<String, State>> transition;
    private State start;
    private Set<State> accept;
    private State current;
    /**
     * Constructs a state with the specified name and label.
     * The activity counter is set to zero.
     *
     * @param states the set of states of this DFA
       @param alphabet the alphabet of this DFA
       @param transition the transition function of this DFA
       @param start the start state of this DFA
       @param accept the set of accept states of this DFA
     */

    public DFA(Set<State> states, Set<String> alphabet,
               Map<State, Map<String, State>> transition,
               State start, Set<State> accept) {
        this.states = states;
        this.alphabet = alphabet;
        this.transition = transition;
        this.start = start;
        this.accept = accept;
    }

    /** Determines whether given string is accepted or rejected by this DFA.
    * Each state's activity counter is initialized to zero, then incremented by
    *one each time the state is entered. Returns true if the string is in the
    * language recognized by this DFA; false otherwise
     *
     * @param input The string being tested
     * @return true The string is in the language, false otherwise.
    */
    public boolean accepts(String input) {
        current = start;
        for(State tmp : states) // initialize all state counters to 0
            tmp.reset();
        current.increment(); //increment once
        if(input.equals(null) || input.equals("") && acceptStates().contains(current))  //Reached end of string
            return acceptStates().contains(current);

        while(!input.equals("")) {
            //Has not reached end of string but does have transitions
            current= nextState(current, input); // walk one state
            if(current==null)
                return false;
            current.increment();
            input = input.substring(1);
        }
        return acceptStates().contains(current);
    }

    /** Retrieves the set of accept states.
     * @return The set of accept states
     **/
    public Set<State> acceptStates() {
        return accept;
    }

    /** Retrieves the alphabet of this DFA.
    * @return the alphabet
     */
    public Set<String> alphabet() {
        return alphabet;
    }

    /** Retrieves the initial state of this DFA, if any.
    * @return the initial state; null if none
     */
    public State initialState() {
        if (start == null)
            return null;
        else
            return start;
    }

    /** Lookup transition for specified state and input.
     * @param source The state that is checked for a transition
     * @param input The string being tested
    * @return the destination state; null if none
     */
    private State nextState(State source, String input) {
        String tmp = input.substring(0,1);
        return transition.get(source).get(tmp);
    }

    /** Retrieves the set of all states.
    * @return the states
     */
    public Set<State> states() {
        return states;
    }

    /** Retrieves the transition function of this DFA.
    * @return the transition function
     */
    public Map<State, Map<String, State>> transitionFunction() {
        return transition;
    }
}
