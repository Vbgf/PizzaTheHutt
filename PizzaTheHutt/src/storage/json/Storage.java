package storage.json;

import java.io.IOException;
import java.util.List;

public interface Storage<T> {
	public List<T> load() throws IOException;
	public void save(List<T> data) throws IOException;
}
