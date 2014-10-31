package com.yizhao.integration.java;

public class CurlCommandsSetterOfPost extends BehaviorOfCurlCommandsSetter {

	@Override
	public void execute(StatesOfClient state) {
		switch (state) {
		case STATE_POST:
			break;
		default:
			pmfc.printCurrentStateInfo();
		}
	}
}
