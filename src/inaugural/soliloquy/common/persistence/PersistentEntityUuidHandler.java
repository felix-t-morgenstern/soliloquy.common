package inaugural.soliloquy.common.persistence;

import inaugural.soliloquy.tools.persistence.PersistentTypeHandler;
import soliloquy.specs.common.factories.EntityUuidFactory;
import soliloquy.specs.common.persistence.PersistentValueTypeHandler;
import soliloquy.specs.common.valueobjects.EntityUuid;

public class PersistentEntityUuidHandler extends PersistentTypeHandler<EntityUuid> {
    private final EntityUuidFactory ENTITY_UUID_FACTORY;
    private final EntityUuid ARCHETYPE;

    public PersistentEntityUuidHandler(EntityUuidFactory entityUuidFactory) {
        ENTITY_UUID_FACTORY = entityUuidFactory;
        ARCHETYPE = entityUuidFactory.createFromLongs(0,0);
    }

    @Override
    public EntityUuid getArchetype() {
        return ARCHETYPE;
    }

    @Override
    public EntityUuid read(String serializedValue) throws IllegalArgumentException {
        if (serializedValue == null || serializedValue.equals("")) {
            return null;
        } else {
            return ENTITY_UUID_FACTORY.createFromString(serializedValue);
        }
    }

    @Override
    public String write(EntityUuid entityUuid) {
        if (entityUuid == null) {
            return "";
        } else {
            return entityUuid.toString();
        }
    }

    @Override
    public String toString() {
        return PersistentValueTypeHandler.class.getCanonicalName() + "<" +
                EntityUuid.class.getCanonicalName() + ">";
    }

    @Override
    public int hashCode() {
        return (PersistentValueTypeHandler.class.getCanonicalName() + "<" +
                EntityUuid.class.getCanonicalName() + ">").hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PersistentEntityUuidHandler && obj.hashCode() == hashCode();
    }
}
