package storage.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

public class JacksonJsonParser<T> implements Storage<T>{
	
	private static final Charset ENCODING = StandardCharsets.UTF_8;
	
	private File dbFile;
	private Charset dbEncoding;
	
	public JacksonJsonParser(File dbFile) throws IOException {
		this(dbFile, ENCODING);
	}
	
	public JacksonJsonParser(File dbFile, Charset encoding) throws IOException {
		this.dbFile = dbFile;
		this.dbEncoding = encoding;
		dbFile.createNewFile();
	}

	@Override
	public List<T> load() throws IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		List<T> myObjects;
		try {
			myObjects = Arrays.asList(mapper.readValue(dbFile, new TypeReference<List<T>>() {}));
		}catch (MismatchedInputException e) {
			myObjects = null;
		}
		
		return myObjects;
	}

	@Override
	public void save(List<T> data) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(dbFile, data);
	}

}
