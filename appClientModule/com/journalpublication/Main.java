package com.journalpublication;

import com.journalpublication.service.JournalPubServiceImpl;

/**
 * Main class to load MJP desktop app
 * @author nouval
 *
 */
public class Main {
	public static void main(String[] args) {

		(new JournalPubViewMain(new JournalPubServiceImpl())).run();
	}
}