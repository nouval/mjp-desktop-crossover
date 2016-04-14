package com.journalpublication.service;

import java.util.ArrayList;

import com.journalpublication.model.Credential;
import com.journalpublication.model.Journal;

public interface JournalPubService {
	boolean isAuthenticated();
	Credential authenticate(String username, String password);
	ArrayList<Journal> fetchSubscriptions();
	void fetchSubscribeJournal(Journal journal);
	void viewJournal(Journal journal);
}
