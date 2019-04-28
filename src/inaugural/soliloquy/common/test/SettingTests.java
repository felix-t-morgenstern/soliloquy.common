package inaugural.soliloquy.common.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inaugural.soliloquy.common.Setting;
import inaugural.soliloquy.common.test.stubs.GenericParamsSetStub;
import soliloquy.common.specs.IGenericParamsSet;
import soliloquy.common.specs.ISetting;

import static org.junit.jupiter.api.Assertions.*;

class SettingTests {
	private final String SETTING_ID = "SettingId";
	private final String SETTING_NAME_1 = "SettingName1";
	private final String SETTING_NAME_2 = "SettingName2";
	private final Integer SETTING_VALUE_1 = 123;
	private final Integer SETTING_VALUE_2 = 456;
	private final Integer SETTING_ARCHETYPE = 789;
	private final IGenericParamsSet _settingControlParams = new GenericParamsSetStub();
	
	private Setting<Integer> _setting;

    @BeforeEach
	void setUp() {
    	_setting = new Setting<>(SETTING_ID, SETTING_NAME_1, SETTING_VALUE_1,
				SETTING_ARCHETYPE, _settingControlParams);
    }
    
    @Test
	void testId() {
		assertEquals(_setting.id(), SETTING_ID);
    }

    @Test
	void testName() {
		assertEquals(_setting.getName(), SETTING_NAME_1);
    	_setting.setName(SETTING_NAME_2);
		assertEquals(_setting.getName(), SETTING_NAME_2);
    }

    @Test
	void testGetArchetype() {
		assertSame(_setting.getArchetype(), SETTING_ARCHETYPE);
    }

    @Test
	void testConstructorWithNullArchetype() {
    	assertThrows(IllegalArgumentException.class,
				() -> new Setting<>(SETTING_ID, SETTING_NAME_1, SETTING_VALUE_1, null,
						_settingControlParams));
    }

    @Test
	void testGetInterfaceName() {
		assertEquals(ISetting.class.getCanonicalName(), _setting.getInterfaceName());
    }

    @Test
	void testGetUnparameterizedInterfaceName() {
    	assertThrows(UnsupportedOperationException.class,
				() -> _setting.getUnparameterizedInterfaceName());
	}

    @Test
	void testGetValue() {
		assertSame(_setting.getValue(), SETTING_VALUE_1);
    }

    @Test
	void testSetValue() {
    	_setting.setValue(SETTING_VALUE_2);
		assertSame(_setting.getValue(), SETTING_VALUE_2);
    }

    @Test
	void testControlParams() {
		assertSame(_setting.controlParams(), _settingControlParams);
    }
}
