package storage.managers;

import java.io.IOException;
import java.util.List;

public interface Manager<T> {
	void add(T newObject) throws IllegalArgumentException;
	
	T get(long objectId) throws IllegalArgumentException;
	List<T> getAll();
	
	void update(long objectId, T updatedObject) throws IllegalArgumentException;
	void delete(long objectId) throws IllegalArgumentException;
	
	void load() throws IOException;
	void save() throws IOException;
}