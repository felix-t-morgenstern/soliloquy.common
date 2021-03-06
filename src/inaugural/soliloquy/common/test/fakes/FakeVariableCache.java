package inaugural.soliloquy.common.test.fakes;

import soliloquy.specs.common.infrastructure.*;

public class FakeVariableCache implements VariableCache {
    private final Map<String,Object> PERSISTENT_VARIABLES = new FakeMap<>();

    public final static String VARIABLE_1_NAME = "variable1";
    public final static Integer VARIABLE_1_VALUE = 456456;

    public final static String VARIABLE_2_NAME = "variable2";
    public final static String VARIABLE_2_VALUE = "variable2value";

    private final Map<String,Object> P_VARS = new FakeMap<>();

    public FakeVariableCache() {
        PERSISTENT_VARIABLES.put(VARIABLE_1_NAME, VARIABLE_1_VALUE);
        PERSISTENT_VARIABLES.put(VARIABLE_2_NAME, VARIABLE_2_VALUE);
    }

    @Override
    public <T> void setVariable(String name, T value) throws IllegalArgumentException {
        P_VARS.put(name, value);
    }

    @Override
    public boolean remove(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return P_VARS.size();
    }

    @Override
    public List<String> namesRepresentation() {
        if (!P_VARS.isEmpty()) {
            List<String> representation = new FakeList<>();
            P_VARS.forEach((k,v) -> representation.add(k));
            return representation;
        } else {
            List<String> representation = new FakeList<>();
            PERSISTENT_VARIABLES.forEach((k,v) -> representation.add(k));
            return representation;
        }
    }

    @Override
    public Map<String,Object> variablesRepresentation() {
        if (!P_VARS.isEmpty()) {
            return P_VARS;
        } else {
            return PERSISTENT_VARIABLES;
        }
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getVariable(String name) {
        if (!P_VARS.isEmpty()) {
            return (T) P_VARS.get(name);
        } else {
            return (T) PERSISTENT_VARIABLES.get(name);
        }
    }

    @Override
    public String getInterfaceName() {
        return VariableCache.class.getCanonicalName();
    }

    @Override
    public VariableCache makeClone() {
        return null;
    }

    @Override
    public int hashCode() {
        return this.getClass().getCanonicalName().hashCode();
    }
}
