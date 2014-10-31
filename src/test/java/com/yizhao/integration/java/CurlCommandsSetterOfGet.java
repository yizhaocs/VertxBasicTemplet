package com.yizhao.integration.java;



public class CurlCommandsSetterOfGet extends BehaviorOfCurlCommandsSetter{

	@Override
	public void execute(StatesOfClient state) {
		switch (state) {
		case STATE_GET:
			break;
		default:
			pmfc.printCurrentStateInfo();
		}
	}

}
