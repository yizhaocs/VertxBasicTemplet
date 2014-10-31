package com.yizhao.integration.java;

public class AssertionCheckerOfPost implements BehaviorOfAssertionChecker {

	@Override
	public void execute(StatesOfClient state) {
		switch (state) {
		case STATE_POST:
			break;
		default:
		}
	}

}
