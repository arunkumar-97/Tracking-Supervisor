package com.jesperapps.tracksupervisor.api.service;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FireBaseInitializerService {
	
	
	@Autowired
	FirebaseApp firebaseApp;
	
	public Firestore getFirebase() {
		return FirestoreClient.getFirestore();
	}
	
	public FirebaseMessaging getMessaging() {
		return FirebaseMessaging.getInstance(this.firebaseApp);
	}
}
