package com.journalpublication;

import com.journalpublication.model.Journal;

public interface ApplicationListener {

	/**
	 * Trigger when user select particular journal for viewing
	 * @param journal
	 */
	void onJournalSelected(Journal journal);
	
	/**
	 * Triggered during application exit
	 */
	void onApplicationExit();
	
	/**
	 * Triggered when authentication against api service is required due to session invalidated, 
	 * logout, etc.
	 * @param sender
	 * @param username
	 * @param password
	 */
	void onAuthenticationIsRequired(Object sender, String username, String password);
	
	/**
	 * Triggered when any error occurred
	 * @param error
	 */
	void onError(Object error);
}
