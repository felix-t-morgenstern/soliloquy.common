package inaugural.soliloquy.common.test;

import inaugural.soliloquy.common.test.stubs.MapFactoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inaugural.soliloquy.common.PersistentVariableCacheImpl;
import inaugural.soliloquy.common.test.stubs.CollectionFactoryStub;
import soliloquy.specs.common.factories.CollectionFactory;
import soliloquy.specs.common.factories.MapFactory;
import soliloquy.specs.common.infrastructure.Map;
import soliloquy.specs.common.infrastructure.PersistentVariableCache;
import soliloquy.specs.common.infrastructure.ReadableCollection;
import soliloquy.specs.common.infrastructure.ReadableMap;

import static org.junit.jupiter.api.Assertions.*;

class PersistentVariableCacheImplTests {
	private PersistentVariableCache _persistentVariableCache;

	private final CollectionFactory COLLECTION_FACTORY = new CollectionFactoryStub();
	private final MapFactory MAP_FACTORY = new MapFactoryStub();
	
    @BeforeEach
	void setUp() {
    	// archetype not necessary for test suite
    	_persistentVariableCache = new PersistentVariableCacheImpl(COLLECTION_FACTORY, MAP_FACTORY);
    }
    
    @Test
	void testPutAndSize() {
		_persistentVariableCache.setVariable("variable1", "value1");
		_persistentVariableCache.setVariable("variable2", "value2");
		_persistentVariableCache.setVariable("variable3", "value3");

		assertEquals(3, _persistentVariableCache.size());
    }

	@Test
	void testPutAndGet() {
		assertNull(_persistentVariableCache.getVariable("variable1"));

		_persistentVariableCache.setVariable("variable1", "value1");

		assertEquals("value1", _persistentVariableCache.getVariable("variable1"));
	}

    @Test
	void testPutWithNullOrEmptyParams() {
		assertThrows(IllegalArgumentException.class,
				() -> _persistentVariableCache.setVariable(null, 0));
		assertThrows(IllegalArgumentException.class,
				() -> _persistentVariableCache.setVariable("", 0));
		assertThrows(IllegalArgumentException.class,
				() -> _persistentVariableCache.setVariable("variable", null));
    }

    @Test
	void testNamesRepresentation() {
		_persistentVariableCache.setVariable("variable1", "value1");
		_persistentVariableCache.setVariable("variable2", "value2");
		_persistentVariableCache.setVariable("variable3", "value3");

		ReadableCollection<String> namesRepresentation =
				_persistentVariableCache.namesRepresentation();

		assertNotNull(namesRepresentation);
		assertEquals(3, namesRepresentation.size());
		assertTrue(namesRepresentation.contains("variable1"));
		assertTrue(namesRepresentation.contains("variable2"));
		assertTrue(namesRepresentation.contains("variable3"));
	}

	@Test
	void testRemove() {
    	assertFalse(_persistentVariableCache.remove("variable1"));

		_persistentVariableCache.setVariable("variable1", "value1");

		assertTrue(_persistentVariableCache.remove("variable1"));
		assertFalse(_persistentVariableCache.remove("variable1"));
		assertEquals(0, _persistentVariableCache.size());
	}

	@Test
	void testClear() {
    	assertEquals(0, _persistentVariableCache.size());

		_persistentVariableCache.setVariable("variable1", "value1");
		_persistentVariableCache.setVariable("variable2", "value2");
		_persistentVariableCache.setVariable("variable3", "value3");

		assertEquals(3, _persistentVariableCache.size());

		_persistentVariableCache.clear();

		assertEquals(0, _persistentVariableCache.size());
	}

	@Test
	void testVariablesRepresentation() {
		_persistentVariableCache.setVariable("variable1", "value1");
		_persistentVariableCache.setVariable("variable2", "value2");
		_persistentVariableCache.setVariable("variable3", "value3");

		ReadableMap<String,Object> variablesRepresentation =
				_persistentVariableCache.variablesRepresentation();

		assertNotNull(variablesRepresentation);
		assertNotNull(variablesRepresentation.getFirstArchetype());
		assertNotNull(variablesRepresentation.getSecondArchetype());
		assertEquals(3, variablesRepresentation.size());
		assertEquals("value1", variablesRepresentation.get("variable1"));
		assertEquals("value2", variablesRepresentation.get("variable2"));
		assertEquals("value3", variablesRepresentation.get("variable3"));
		assertFalse(variablesRepresentation instanceof Map);
	}
}