package storage.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonParser<T> implements Storage<T> {

	private static final Charset ENCODING = StandardCharsets.UTF_8;
	
	private File dbFile;
	private Charset dbEncoding;
	private Gson gson;
	
	public JsonParser(File dbFile) throws IOException {
		this(dbFile, ENCODING);
	}
	
	public JsonParser(File dbFile, Charset encoding) throws IOException {
		this.dbFile = dbFile;
		this.dbEncoding = encoding;
		this.gson = new GsonBuilder().setPrettyPrinting().create();
		dbFile.createNewFile();
	}
	
	@Override
	public List<T> load() throws IOException {
		if (!dbFile.exists()) {
			throw new IOException();
		}

		FileInputStream inputStream = new FileInputStream(dbFile);
		InputStreamReader streamReader = new InputStreamReader(inputStream, dbEncoding);
		
		List<T> data = gson.fromJson(streamReader, new TypeToken<List<T>>(){}.getType());
		
		inputStream.close();
		streamReader.close();
		
		return data;
	}
	
	@Override
	public void save(List<T> data) throws IOException, IllegalArgumentException {
		if(data == null) {
			throw new IllegalArgumentException();
		}
		
		if(data.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		if (!dbFile.exists()) {
			dbFile.createNewFile();
		}

		FileOutputStream outputStream = new FileOutputStream(dbFile, false);
		OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream, dbEncoding);
		
		gson.toJson(data, streamWriter);
		
		streamWriter.close();
		outputStream.close();
	}
}
