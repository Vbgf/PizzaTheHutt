package ui.console;

import core.context.Context;

public abstract class BaseUI{

	protected static final int BACK = 0;
	
	protected Context context;
	
	public BaseUI(Context context) {
		this.context = context;
	}

	public abstract void show();

}
