package com.journalpublication;

import com.journalpublication.model.Credential;
import com.journalpublication.model.Journal;
import com.journalpublication.service.JournalPubService;
import com.journalpublication.view.JournalPubFrame;
import com.journalpublication.view.LoginDialog;

public class JournalPubViewMain implements ApplicationListener {
	
	private JournalPubFrame frame;
	private JournalPubService service;
	
	public JournalPubViewMain(JournalPubService service) {
		this.service = service;
		this.frame = new JournalPubFrame(this);
	}
	
	public void run() {

		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	
            	// shows main frame
            	frame.setVisible(true);

            	// perform authentication/login if session is not authenticated
            	if (!service.isAuthenticated()) {
            		showLoginDialog();
            	}
            	
            	// load subscribed journals
            	if (service.isAuthenticated()) {
            		frame.reloadJournalList(service.fetchSubscriptions());
            	}            	
            }
        });		
	}

	private void showLoginDialog() {
		(new LoginDialog(frame, this)).setVisible(true);
	}

	@Override
	public void onJournalSelected(Journal journal) {
		
		this.service.viewJournal(journal);

//		this.frame.showMessage("Journal '" + journal.getSubject() + "' [" + journal.getFilename() + "] is selected");		
	}

	@Override
	public void onApplicationExit() {
		if (this.frame != null) {
			this.frame.close();
		}
	}

	@Override
	public void onAuthenticationIsRequired(Object sender, String username, String password) {
		Credential creds = this.service.authenticate(username, password);

		if (creds != null) {
			((LoginDialog)sender).close();
		} else {
			((LoginDialog)sender).error();
		}		
	}

	@Override
	public void onError(Object error) {
		// TODO Auto-generated method stub
		
	}	
}