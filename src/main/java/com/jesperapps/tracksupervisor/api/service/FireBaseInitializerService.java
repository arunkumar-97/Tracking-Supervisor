package com.jesperapps.tracksupervisor.api.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FireBaseInitializerService {
	
	private void initDb() throws IOException {
		InputStream serviceAccount =this.getClass().getClassLoader().getResourceAsStream
				  ("./tracking-application-288112-firebase-adminsdk-ioqnq-9d9b829620.json");

				FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .setDatabaseUrl("https://tracking-application-288112-default-rtdb.firebaseio.com")
				  .build();
					
				
				if(FirebaseApp.getApps().isEmpty()) {
					FirebaseApp.initializeApp(options);

				}
			
				
				
				
	}
	public Firestore getFirebase() {
		return FirestoreClient.getFirestore();
	}
}
