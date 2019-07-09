package storage.json;

import core.Command;

public class StorageJsonLoadCommand implements Command{
	private StorageJson json;
	
	public StorageJsonLoadCommand(StorageJson json) {
		this.json = json;
	}
	
	@Override
	public void execute() throws Exception {
		json.load();
	}

}
