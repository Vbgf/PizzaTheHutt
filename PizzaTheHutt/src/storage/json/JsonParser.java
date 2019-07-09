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

public class JsonParser<T> implements IFileStorage {

	private static final Charset ENCODING = StandardCharsets.UTF_8;
	
	private File dbFile;
	private Charset dbEncoding;
	private List<T> data;
	private Gson gson;
	
	public JsonParser(File dbFile, List<T> dataBind) {
		this(dbFile, dataBind, ENCODING);
	}
	
	public JsonParser(File dbFile, List<T> dataBind, Charset encoding) {
		this.dbFile = dbFile;
		this.dbEncoding = encoding;
		this.data = dataBind;
		this.gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(data.size());
	}
	
	@Override
	public void load() throws IOException {
		if (!dbFile.exists()) {
			dbFile.createNewFile();
			return;
		}

		FileInputStream inputStream = new FileInputStream(dbFile);
		InputStreamReader streamReader = new InputStreamReader(inputStream, dbEncoding);
		
		data = gson.fromJson(streamReader, new TypeToken<List<T>>(){}.getType());
		
		inputStream.close();
		streamReader.close();
	}
	
	@Override
	public void save() throws IOException {
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
