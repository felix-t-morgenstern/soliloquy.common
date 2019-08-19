package inaugural.soliloquy.common.test;

import inaugural.soliloquy.common.RegistryImpl;
import inaugural.soliloquy.common.test.stubs.HasIdAndNameStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.common.infrastructure.Registry;

import static org.junit.jupiter.api.Assertions.*;

class RegistryImplTests {
    private static HasIdAndNameStub ARCHETYPE = new HasIdAndNameStub("", "");
    private static String ID = "id";
    private static String NAME = "name";

    private Registry<HasIdAndNameStub> _registry;
    private HasIdAndNameStub _hasIdAndStub = new HasIdAndNameStub(ID, NAME);

    @BeforeEach
    void setUp() {
        _registry = new RegistryImpl<>(ARCHETYPE);
    }

    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class, () -> _registry = new RegistryImpl<>(null));
    }

    @Test
    void testArchetype() {
        assertSame(ARCHETYPE, _registry.getArchetype());
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(Registry.class.getCanonicalName() + "<" +
                HasIdAndNameStub.class.getCanonicalName() + ">",
                _registry.getInterfaceName());
    }

    @Test
    void testRegisterAndGet() {
        _registry.register(_hasIdAndStub);

        assertSame(_hasIdAndStub, _registry.get(ID));
    }

    @Test
    void testRegisterNullItem() {
        assertThrows(IllegalArgumentException.class, () -> _registry.register(null));
    }

    @Test
    void testRegisterWithNullId() {
        assertThrows(IllegalArgumentException.class,
                () -> _registry.register(new HasIdAndNameStub(null, NAME)));
    }

    @Test
    void testRegisterWithEmptyId() {
        assertThrows(IllegalArgumentException.class,
                () -> _registry.register(new HasIdAndNameStub("", NAME)));
    }

    @Test
    void testContains() {
        assertFalse(_registry.contains(ID));

        _registry.register(_hasIdAndStub);

        assertTrue(_registry.contains(ID));
    }

    @Test
    void testRemove() {
        assertFalse(_registry.contains(ID));

        _registry.register(_hasIdAndStub);

        assertTrue(_registry.remove(ID));
        assertFalse(_registry.remove(ID));
        assertFalse(_registry.contains(ID));
    }
}