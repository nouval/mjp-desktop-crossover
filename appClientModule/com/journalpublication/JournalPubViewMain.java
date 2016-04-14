package com.journalpublication;

import com.journalpublication.model.Credential;
import com.journalpublication.service.JournalPubService;
import com.journalpublication.view.JournalPubFrame;
import com.journalpublication.view.LoginDialog;

public class JournalPubViewMain implements AuthenticationListener {
	
	private JournalPubFrame frame;
	private JournalPubService service;
	
	public JournalPubViewMain(JournalPubService service) {
		this.service = service;
	}
	
	public void run() {
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	// shows main frame
            	(frame = new JournalPubFrame()).setVisible(true);

            	// perform authentication/login if session is not authenticated
            	if (!service.isAuthenticated()) {
            		showLoginDialog();
            	}
            	
            	if (service.isAuthenticated()) {
                	// load subscribed journals
            		frame.reloadJournalList(service.fetchSubscriptions());
            	}            	
            }
        });		
	}

	private void showLoginDialog() {
		(new LoginDialog(frame, this)).setVisible(true);
	}

	@Override
	public void onUsernameAndPasswordEntered(Object sender, String username, String password) {

		Credential creds = this.service.authenticate(username, password);

		if (creds != null) {
			((LoginDialog)sender).close();
		} else {
			((LoginDialog)sender).error();			
		}		
	}	
}