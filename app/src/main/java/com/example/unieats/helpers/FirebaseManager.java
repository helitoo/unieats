package com.example.unieats.helpers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseManager {

    private static FirebaseManager instance;
    private final FirebaseAuth auth;
    private final FirebaseFirestore db;
    private final FirebaseStorage storage;

    private FirebaseManager() {
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    public static synchronized FirebaseManager getInstance() {
        if (instance == null) {
            instance = new FirebaseManager();
        }
        return instance;
    }

    // --- AUTHENTICATION ---
    public FirebaseAuth getAuth() {
        return auth;
    }

    public FirebaseUser getCurrentUser() {
        return auth.getCurrentUser();
    }

    public boolean isLoggedIn() {
        return auth.getCurrentUser() != null;
    }

    public void logout() {
        auth.signOut();
    }

    // --- FIRESTORE COLLECTIONS ---
    public FirebaseFirestore getDb() {
        return db;
    }

    public CollectionReference getUsersRef() {
        return db.collection("users");
    }

    public CollectionReference getCategoriesRef() {
        return db.collection("categories");
    }

    public CollectionReference getRestaurantsRef() {
        return db.collection("restaurants");
    }

    public CollectionReference getMenusRef(String restaurantId) {
        return db.collection("restaurants").document(restaurantId).collection("menus");
    }

    public CollectionReference getReviewsRef(String restaurantId) {
        return db.collection("restaurants").document(restaurantId).collection("reviews");
    }

    public CollectionReference getSavedRestaurantsRef(String userId) {
        return db.collection("users").document(userId).collection("saved_restaurants");
    }

    public CollectionReference getContributionsRef() {
        return db.collection("contributions");
    }

    // --- FIREBASE STORAGE ---
    public FirebaseStorage getStorage() {
        return storage;
    }

    public StorageReference getStorageRef(String path) {
        return storage.getReference().child(path);
    }
}
