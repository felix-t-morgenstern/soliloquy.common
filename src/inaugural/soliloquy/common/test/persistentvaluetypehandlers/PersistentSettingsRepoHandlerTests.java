package inaugural.soliloquy.common.test.persistentvaluetypehandlers;

import inaugural.soliloquy.common.persistentvaluetypehandlers.PersistentSettingsRepoHandler;
import inaugural.soliloquy.common.test.stubs.PersistentValuesHandlerStub;
import inaugural.soliloquy.common.test.stubs.SettingsRepoStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.common.infrastructure.*;

import static org.junit.jupiter.api.Assertions.*;

class PersistentSettingsRepoHandlerTests {
    private final SettingsRepo SETTINGS_REPO = new SettingsRepoStub();
    private final PersistentValuesHandler PERSISTENT_VALUES_HANDLER =
            new PersistentValuesHandlerStub();

    private final String VALUES_STRING =
            "[{\"id\":\"setting1Id\",\"serializedValue\":\"setting1Value\"}," +
                    "{\"id\":\"setting2Id\",\"serializedValue\":\"123123\"}]";

    private PersistentValueTypeHandler<SettingsRepo> _persistentSettingsRepoHandler;

    @BeforeEach
    void setUp() {
        _persistentSettingsRepoHandler =
                new PersistentSettingsRepoHandler(PERSISTENT_VALUES_HANDLER, SETTINGS_REPO);
    }

    @Test
    void testGetInterface() {
        assertEquals(PersistentValueTypeHandler.class.getCanonicalName() + "<" +
                SettingsRepo.class.getCanonicalName() + ">",
                _persistentSettingsRepoHandler.getInterfaceName());
    }

    @Test
    void testWrite() {
        assertEquals(VALUES_STRING, _persistentSettingsRepoHandler.write(SETTINGS_REPO));
    }

    @Test
    void testWriteWithInvalidParameters() {
        assertThrows(IllegalArgumentException.class,
                () -> _persistentSettingsRepoHandler.write(null));
    }

    @Test
    void testRead() {
        SettingsRepo settingsRepo = _persistentSettingsRepoHandler.read(VALUES_STRING);

        assertSame(SETTINGS_REPO, settingsRepo);

        Collection<Setting> settings = SETTINGS_REPO.getAllUngrouped();
        assertEquals(2, settings.size());
        for(Setting setting : settings) {
            switch(setting.getName()) {
                case SettingsRepoStub.SETTING_1_NAME:
                    assertEquals(SettingsRepoStub.SETTING_1_VALUE, setting.getValue());
                    break;
                case SettingsRepoStub.SETTING_2_NAME:
                    assertEquals(SettingsRepoStub.SETTING_2_VALUE, setting.getValue());
                    break;
                default:
                    fail("Invalid setting name");
            }
        }
    }

    @Test
    void testReadForNonexistentSetting() {
        assertThrows(IllegalArgumentException.class,
                () -> _persistentSettingsRepoHandler.read(
                        "[{\"id\":\"InvalidName\",\"valueString\":\"setting1Value\"}," +
                        "{\"id\":\"setting2Name\",\"valueString\":\"123123\"}]"));
    }

    @Test
    void testReadWithInvalidParams() {
        assertThrows(IllegalArgumentException.class,
                () -> _persistentSettingsRepoHandler.read(null));
        assertThrows(IllegalArgumentException.class,
                () -> _persistentSettingsRepoHandler.read(""));
    }
}
