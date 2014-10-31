package com.yizhao.integration.java;

public class AssertionCheckerOfDelete implements BehaviorOfAssertionChecker {

	@Override
	public void execute(StatesOfClient state) {
		switch (state) {
		case STATE_DELETE:
			break;
		default:
		}
	}
}
