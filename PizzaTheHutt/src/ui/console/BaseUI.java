package ui.console;

import core.context.Context;

public abstract class BaseUI{

	protected Context context;
	
	public BaseUI(Context context) {
		this.context = context;
	}
	
	public abstract int show();

}
