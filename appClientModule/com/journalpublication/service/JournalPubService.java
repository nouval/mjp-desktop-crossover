package com.journalpublication.service;

import java.util.ArrayList;

import com.journalpublication.model.Credential;
import com.journalpublication.model.Journal;

/**
 * Service contract for connecting to MJP's api
 * @author nouval
 *
 */
public interface JournalPubService {
	/**
	 * checks if login is completed and session is not invalidated
	 * @return
	 */
	boolean isAuthenticated();

	/**
	 * Validating username and password against api
	 * @param username
	 * @param password
	 * @return
	 */
	Credential authenticate(String username, String password);
	
	/**
	 * connect and returns list of subscribed journals. This method should load from local
	 * source should desktop app runs in offline mode.
	 * @return
	 */
	ArrayList<Journal> fetchSubscriptions();

	/**
	 * show selected journal, as image
	 * @param journal
	 */
	void viewJournal(Journal journal);
}
