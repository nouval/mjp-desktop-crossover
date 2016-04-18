package com.journalpublication.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.journalpublication.AppConfig;
import com.journalpublication.model.Credential;
import com.journalpublication.model.Journal;
import com.journalpublication.view.JournalReaderFrame;

public class JournalPubServiceImpl implements JournalPubService {

	private Credential creds;
	
	public JournalPubServiceImpl() {

		this.creds = new Credential();
	}

	@Override
	public boolean isAuthenticated() {
		boolean isTokenStillValid = false;
		
		try {
			String apiUrl = AppConfig.getInstance().getApiUrl() + "/api/validate";
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet get = new HttpGet(apiUrl);

			get.setHeader("X-AUTH-TOKEN", this.creds.getToken());

			HttpResponse response = client.execute(get);

			if (response.getStatusLine().getStatusCode() == 200) {
				isTokenStillValid = true;
			}
		} catch (HttpHostConnectException hhcEx) {
			
			// should be OK to continue as offline mode, IF subscribed journals IS downloaded			
			isTokenStillValid = AppConfig.getInstance().getListOfSubscribedJournals().size() != 0;
			
		} catch (IOException ioEx) {

			// ioEx.printStackTrace();
		}
		
		return isTokenStillValid;
	}

	@Override
	public Credential authenticate(String username, String password) {

		Credential _creds = null;

		try {
			String apiUrl = AppConfig.getInstance().getApiUrl() + "/api/login";
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(apiUrl);
			
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("username", username));
			urlParameters.add(new BasicNameValuePair("password", password));
			urlParameters.add(new BasicNameValuePair("apiKey", AppConfig.getInstance().getApiKey()));
			
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
		
			HttpResponse response = client.execute(post);
			
			if (response.getStatusLine().getStatusCode() == 200) {
				ObjectMapper mapper = new ObjectMapper();
				_creds = mapper.readValue(response.getEntity().getContent(), Credential.class);
				
				this.creds.setUsername(_creds.getUsername());
				this.creds.setToken(_creds.getToken());
				
				AppConfig.getInstance().setUsername(creds.getUsername());
				AppConfig.getInstance().setToken(creds.getToken());
			}
		} catch (IOException ioEx) {

			ioEx.printStackTrace();
		}

		return this.creds; 
	}

	@Override
	public ArrayList<Journal> fetchSubscriptions() {

		ArrayList<Journal> journals = null;

		try {
			String apiUrl = AppConfig.getInstance().getApiUrl() + "/api/subscriptions";
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet get = new HttpGet(apiUrl);

			get.setHeader("X-AUTH-TOKEN", this.creds.getToken());

			HttpResponse response = client.execute(get);
			
			if (response.getStatusLine().getStatusCode() == 200) {
				ObjectMapper mapper = new ObjectMapper();
				journals = mapper.readValue(response.getEntity().getContent(), mapper.getTypeFactory().constructCollectionType(
	                    List.class, Journal.class));
				
				AppConfig.getInstance().setListOfSubscribedJournals(journals);
			}
		} catch (HttpHostConnectException hhcEx) {
			
			// warns user offline mode
			
		} catch (IOException ioEx) {

			// ioEx.printStackTrace();
		}

		return journals;
	}

	@Override
	public void viewJournal(final Journal journal) {
		
		// open offline file, if not exists fetch one from api
		File file = new File(journal.getFilename());
		
		// fetch from api, if unavailable offline
		if (!file.exists()) {

			try {
				String apiUrl = AppConfig.getInstance().getApiUrl() + "/api/journal/" + journal.getId();
				HttpClient client = HttpClientBuilder.create().build();
				HttpGet get = new HttpGet(apiUrl);

				get.setHeader("X-AUTH-TOKEN", this.creds.getToken());

				HttpResponse response = client.execute(get);

				if (response.getStatusLine().getStatusCode() == 200) {
					ObjectMapper mapper = new ObjectMapper();
					Journal journalRemote = mapper.readValue(response.getEntity().getContent(), Journal.class);

					FileOutputStream fos = new FileOutputStream(file);
					fos.write(journalRemote.getContent());
					fos.flush();
					fos.close();					
				}
			} catch (HttpHostConnectException hhcEx) {
				
				// warns user offline mode
				file = null;
				
			} catch (IOException ioEx) {

				// ioEx.printStackTrace();
				file = null;
			}
		}
		
		if (file != null) {
			try {
				
				new JournalReaderFrame(journal.getFilename(), ImageIO.read(new File(file.getName())));

			} catch (IOException ex) {
				System.out.println("Error: " + ex.getMessage());

				ex.printStackTrace();
			} catch (Exception ex) {
				
				ex.printStackTrace();
			}
		}
	}
}
