/**
 * Created by neal on 2/1/2015.
 */
import java.util.Map;
import java.util.Set;
public class DFA extends State {

    /**
     * Constructs a state with the specified name and label.
     * The activity counter is set to zero.
     *
     * @param name  the name for this state
     * @param label the label for this state
     */
    public DFA(String name, String label) {
        super(name, label);
    }

    public boolean accepts(String input){}

    public Set<State> acceptStates(){}

    public Set<String> alphabet(){

    }

    public State initialState(){

    }

    public State nextState(State source,
                           String input){

    }

    public Set<State> states(){}

    public Map<State,Map<String,State>> transitionFunction(){}

}
