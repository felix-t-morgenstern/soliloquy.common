package inaugural.soliloquy.common.test.persistentvaluetypehandlers;

import inaugural.soliloquy.common.persistentvaluetypehandlers.PersistentCoordinateHandler;
import inaugural.soliloquy.common.test.stubs.CoordinateFactoryStub;
import inaugural.soliloquy.common.test.stubs.CoordinateStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.common.infrastructure.PersistentValueTypeHandler;
import soliloquy.specs.common.factories.CoordinateFactory;
import soliloquy.specs.common.valueobjects.Coordinate;

import static org.junit.jupiter.api.Assertions.*;

class PersistentCoordinateHandlerTests {
    private final CoordinateFactory COORDINATE_FACTORY = new CoordinateFactoryStub();
    private final int X = 123;
    private final int Y = 456;
    private final String VALUES_STRING = "{\"x\":123,\"y\":456}";

    private PersistentCoordinateHandler _persistentCoordinateHandler;

    @BeforeEach
    void setUp() {
        _persistentCoordinateHandler = new PersistentCoordinateHandler(COORDINATE_FACTORY);
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(
                PersistentValueTypeHandler.class.getCanonicalName() + "<" +
                        Coordinate.class.getCanonicalName() + ">",
                _persistentCoordinateHandler.getInterfaceName());
    }

    @Test
    void testGetArchetype() {
        assertNotNull(_persistentCoordinateHandler.getArchetype());
    }

    @Test
    void testWrite() {
        Coordinate coordinate = new CoordinateStub();
        coordinate.setX(X);
        coordinate.setY(Y);

        assertEquals(VALUES_STRING, _persistentCoordinateHandler.write(coordinate));
    }

    @Test
    void testWriteNull() {
        assertEquals("NULL", _persistentCoordinateHandler.write(null));
    }

    @Test
    void testRead() {
        Coordinate coordinate = _persistentCoordinateHandler.read(VALUES_STRING);

        assertEquals(X, coordinate.getX());
        assertEquals(Y, coordinate.getY());
    }

    @Test
    void testReadNull() {
        assertNull(_persistentCoordinateHandler.read("NULL"));
    }

    @Test
    void testReadInvalidStrings() {
        assertThrows(IllegalArgumentException.class,
                () -> _persistentCoordinateHandler.read(null));
        assertThrows(IllegalArgumentException.class,
                () -> _persistentCoordinateHandler.read(""));
    }
}
