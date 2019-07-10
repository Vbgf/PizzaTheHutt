package storage.json;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

public class JacksonJsonParser<T> implements Storage<T>{
	
	private File dbFile;
	private ObjectMapper mapper;
	private JavaType type;
	
	public JacksonJsonParser(Class<T> storedClass, File dbFile) throws IOException {
		this.dbFile = dbFile;
		this.mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		type =  mapper.getTypeFactory().constructCollectionType(List.class, storedClass);
		dbFile.createNewFile();
	}

	@Override
	public List<T> load() throws IOException {
		List<T> myObjects;
		
		try {
			myObjects = mapper.readValue(dbFile, type);
		}catch (MismatchedInputException e) {
			//this is reached when the file is empty
			myObjects = Collections.emptyList();
		}
		
		return myObjects;
	}

	@Override
	public void save(List<T> data) throws IOException {
		if(data == null) {
			throw new IllegalArgumentException();
		}
		
		if(data.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		if (!dbFile.exists()) {
			dbFile.createNewFile();
		}
		mapper.writeValue(dbFile, data);
	}

}
