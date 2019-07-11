package ui.console;

import java.io.IOException;

import core.context.Context;

public abstract class UIBase{

	protected Context context;
	
	public UIBase(Context context) {
		this.context = context;
	}
	
	public abstract int show() throws IOException;

}
