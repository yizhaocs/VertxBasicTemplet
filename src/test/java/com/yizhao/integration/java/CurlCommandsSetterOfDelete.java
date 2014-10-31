package com.yizhao.integration.java;

public class CurlCommandsSetterOfDelete extends BehaviorOfCurlCommandsSetter{

	@Override
	public void execute(StatesOfClient state) {
		switch (state) {
		case STATE_DELETE:
			break;
		default:
			pmfc.printCurrentStateInfo();
		}
	}

}
