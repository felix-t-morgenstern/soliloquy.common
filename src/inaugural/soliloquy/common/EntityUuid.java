package inaugural.soliloquy.common;

import java.util.UUID;

import soliloquy.common.specs.IEntityUuid;

public class EntityUuid implements IEntityUuid {
	private UUID _uuid;

	public EntityUuid(long mostSignificantBits, long leastSignificantBits){
		_uuid = new UUID(mostSignificantBits, leastSignificantBits);
	}

	public EntityUuid(String uuid) {
		_uuid = UUID.fromString(uuid);
	}

	public EntityUuid(){
		_uuid = UUID.randomUUID();
	}

	@Override
	public long getMostSignificantBits() {
		return _uuid.getMostSignificantBits();
	}

	@Override
	public long getLeastSignificantBits() {
		return _uuid.getLeastSignificantBits();
	}
	
	public String toString() {
		return _uuid.toString();
	}

	@Override
	public boolean equals(Object comparand) {
		return comparand != null 
				&& _uuid.getMostSignificantBits() == ((IEntityUuid)comparand).getMostSignificantBits() 
				&& _uuid.getLeastSignificantBits() == ((IEntityUuid)comparand).getLeastSignificantBits();
	}
}
