package com.yizhao.integration.java;

public class AssertionCheckerOfGet implements BehaviorOfAssertionChecker {

	@Override
	public void execute(StatesOfClient state) {
		switch (state) {
		case STATE_GET:
			break;
		default:
		}
	}

}
