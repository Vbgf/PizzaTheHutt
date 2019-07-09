package storage.json;

import java.io.IOException;

public interface IFileStorage {
	public void load() throws IOException;
	public void save() throws IOException;
}
