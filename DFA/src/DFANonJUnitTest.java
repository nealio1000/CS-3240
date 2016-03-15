/* DFANonJUnitTest.java */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * Standalone unit tests for the DFA class.
 * @author  Dr. Jody Paul
 * @version CSTheory (4)
 */
public final class DFANonJUnitTest {
    /** Utility class - no constructor. */
    private DFANonJUnitTest() { }

    /**
     * Simple truth assertion check.
     * Throws exception if value is not true.
     * @param value the boolean value to be checked
     */
    private static void assertTrue(final boolean value) {
        assert value;
    }

    /**
     * Simple untruth assertion check.
     * Throws exception if value is not true.
     * @param value the boolean value to be checked
     */
    private static void assertFalse(final boolean value) {
        assert !value;
    }

    /**
     * Simple equality assertion check.
     * Throws exception if values are not equal.
     * @param value1 first value
     * @param value2 second value
     */
    private static void assertEquals(final Object value1, final Object value2) {
        assert value1 == value2
                : "Failed equals: " + value1 + " with " + value2;
    }

    /**
     * Construct and test a string validator for
     * regular expression a*b.
     * Includes tests of state activity counter.
     */
    public static void astar_bDFATest() {
        Set<String> alphabet = new HashSet<String>();
        alphabet.add("a");
        alphabet.add("b");
        State q0 = new State("q0", "a*");
        State q1 = new State("q1", "a*b");
        State q2 = new State("q2", "TRAP");
        Set<State> states = new HashSet<State>();
        states.add(q0);
        states.add(q1);
        states.add(q2);
        State initial = q0;
        Set<State> accept = new HashSet<State>();
        accept.add(q1);
        Map<State, Map<String, State>> transitions
                = new HashMap<State, Map<String, State>>();
        Map<String, State> trans = new HashMap<String, State>();
        trans.put("a", q0);
        trans.put("b", q1);
        transitions.put(q0, trans);
        trans = new HashMap<String, State>();
        trans.put("a", q2);
        trans.put("b", q2);
        transitions.put(q1, trans);
        trans = new HashMap<String, State>();
        trans.put("a", q2);
        trans.put("b", q2);
        transitions.put(q2, trans);
        DFA dfa = new DFA(states, alphabet, transitions, initial, accept);
        assertTrue(dfa.accepts("b"));
        assertEquals(1L, q0.counter());
        assertEquals(1L, q1.counter());
        assertEquals(0L, q2.counter());
        assertTrue(dfa.accepts("aaaaab"));
        assertEquals(6L, q0.counter());
        assertEquals(1L, q1.counter());
        assertEquals(0L, q2.counter());
        assertFalse(dfa.accepts("ababab"));
        assertEquals(2L, q0.counter());
        assertEquals(1L, q1.counter());
        assertEquals(4L, q2.counter());
        assertFalse(dfa.accepts("aaaaabbbbb"));
        assertEquals(6L, q0.counter());
        assertEquals(1L, q1.counter());
        assertEquals(4L, q2.counter());
        assertFalse(dfa.accepts("bb"));
        assertEquals(1L, q0.counter());
        assertEquals(1L, q1.counter());
        assertEquals(1L, q2.counter());
    }

    /**
     * Construct and test a string validator for
     * regular expression a*c(ab)*cc*.
     *
     */
    public static void astar_c_abstar_c_cstarDFATest() {
        Set<String> alphabet = new HashSet<String>();
        alphabet.add("a");
        alphabet.add("b");
        alphabet.add("c");
        State q0 = new State("q0", null);
        State q1 = new State("q1", null);
        State q2 = new State("q2", null);
        State q3 = new State("q3", null);
        State q4 = new State("q4", "TRAP");
        Set<State> states = new HashSet<State>();
        states.add(q0);
        states.add(q1);
        states.add(q2);
        states.add(q3);
        states.add(q4);
        State initial = q0;
        Set<State> accept = new HashSet<State>();
        accept.add(q2);
        Map<State, Map<String, State>> transitions
                = new HashMap<State, Map<String, State>>();
        Map<String, State> trans = new HashMap<String, State>();
        trans.put("a", q0);
        trans.put("b", q4);
        trans.put("c", q1);
        transitions.put(q0, trans);
        trans = new HashMap<String, State>();
        trans.put("a", q3);
        trans.put("b", q4);
        trans.put("c", q2);
        transitions.put(q1, trans);
        trans = new HashMap<String, State>();
        trans.put("a", q4);
        trans.put("b", q4);
        trans.put("c", q2);
        transitions.put(q2, trans);
        trans = new HashMap<String, State>();
        trans.put("a", q4);
        trans.put("b", q1);
        trans.put("c", q4);
        transitions.put(q3, trans);
        trans = new HashMap<String, State>();
        trans.put("a", q4);
        trans.put("b", q4);
        trans.put("c", q4);
        transitions.put(q4, trans);
        DFA regx = new DFA(states, alphabet, transitions, initial, accept);
        assertTrue(regx.accepts("cc"));
        assertTrue(regx.accepts("acabc"));
        assertTrue(regx.accepts("accc"));
        assertTrue(regx.accepts("aaaccc"));
        assertTrue(regx.accepts("cabababcc"));
        assertFalse(regx.accepts("accabc"));
        assertFalse(regx.accepts("aaccabc"));
    }

    /**
     * Construct and test a simple assignment statement checker.
     * All variables are a single lower-case letter.
     * Expression operators are +, -, *, and /.
     * Assignment operator is =.
     * Blanks are not in the alphabet.
     * The assignment statement must end with ; (a semi-colon).
     */
    public static void assignmentStatementDFATest() {
        Set<String> variables = new HashSet<String>();
        for (char c = 'a'; c <= 'z'; c++) {
            variables.add(String.valueOf(c));
        }
        Set<String> operators = new HashSet<String>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        String assignment = "=";
        String terminator = ";";
        Set<String> alphabet = new HashSet<String>();
        alphabet.addAll(variables);
        alphabet.addAll(operators);
        alphabet.add(assignment);
        alphabet.add(terminator);

        State q0 = new State("q0", "S");
        State q1 = new State("q1", "A");
        State q2 = new State("q2", "B");
        State q3 = new State("q3", "E");
        State q4 = new State("q4", "");
        Set<State> states = new HashSet<State>();
        states.add(q0);
        states.add(q1);
        states.add(q2);
        states.add(q3);
        states.add(q4);
        State initial = q0;
        Set<State> accept = new HashSet<State>();
        accept.add(q4);
        Map<State, Map<String, State>> transitions
                = new HashMap<State, Map<String, State>>();
        Map<String, State> trans = new HashMap<String, State>();
        for (String v : variables) {
            trans.put(v, q1);
        }
        transitions.put(q0, trans);
        trans = new HashMap<String, State>();
        trans.put(assignment, q2);
        transitions.put(q1, trans);
        trans = new HashMap<String, State>();
        for (String v : variables) {
            trans.put(v, q3);
        }
        transitions.put(q2, trans);
        trans = new HashMap<String, State>();
        for (String v : operators) {
            trans.put(v, q2);
        }
        trans.put(terminator, q4);
        transitions.put(q3, trans);
        DFA asgn = new DFA(states, alphabet, transitions, initial, accept);
        assertTrue(asgn.accepts("x=y;"));
        assertTrue(asgn.accepts("z=x+y;"));
        assertTrue(asgn.accepts("y=x*z+x*y;"));
        assertFalse(asgn.accepts("x"));
        assertFalse(asgn.accepts(";"));
        assertFalse(asgn.accepts("y=x*z+x*y"));
    }

    /**
     * Driver for unit tests of DFA.
     * @param args ignored
     */
    public static void main(final String[] args) {
        astar_bDFATest();
        astar_c_abstar_c_cstarDFATest();
        assignmentStatementDFATest();
        System.err.println("All DFA unit tests passed.");
    }
}