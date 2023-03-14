package com.app;

import controller.Controller;

public class Main {
	
	public static void main(String[] args) {
		start();
	}
	
	public static void start() {
		Controller cont = new Controller();
		cont.appStart();
	}

}
