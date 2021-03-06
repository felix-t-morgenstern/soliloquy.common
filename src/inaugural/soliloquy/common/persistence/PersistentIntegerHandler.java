package inaugural.soliloquy.common.persistence;

import inaugural.soliloquy.tools.persistence.PersistentTypeHandler;

public class PersistentIntegerHandler extends PersistentTypeHandler<Integer> {

    @SuppressWarnings("ConstantConditions")
    @Override
    public Integer read(String serializedValue) throws IllegalArgumentException {
        return Integer.parseInt(serializedValue);
    }

    @Override
    public String write(Integer value) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }

    @Override
    public Integer getArchetype() {
        return 0;
    }

}
