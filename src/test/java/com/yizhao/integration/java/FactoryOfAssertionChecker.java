package com.yizhao.integration.java;


public class FactoryOfAssertionChecker {
	public BehaviorOfAssertionChecker createChecker(StatesOfClient state) {
		BehaviorOfAssertionChecker f = null;
		if (state.toString().indexOf("UPLOAD") >= 0) {
			f = new AssertionCheckerOfPost();
		} else if (state.toString().indexOf("DOWNLOAD") >= 0) {
			f = new AssertionCheckerOfGet();
		}
		return f;
	}
}
