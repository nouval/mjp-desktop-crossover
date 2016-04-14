package com.journalpublication;

import com.journalpublication.service.JournalPubServiceImpl;

public class Main {
	public static void main(String[] args) {

		(new JournalPubViewMain(new JournalPubServiceImpl())).run();
	}
}