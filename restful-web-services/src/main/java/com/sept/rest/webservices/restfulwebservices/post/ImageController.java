package com.sept.rest.webservices.restfulwebservices.post;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

public class ImageController {
	Storage storage;
	
	public ImageController(){
		Credentials credentials;
		try {
			credentials = GoogleCredentials.fromStream(new FileInputStream("sept-b15e60f11201.json"));
			storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId("sept-248413").build().getService();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR - Can not find credentials file");
		} catch (IOException e) {
		}	
	}
	
	public void uploadImage(String image, String id) {
		
		if (image.equals("") || image == null)
			throw new NullPointerException("No image sent in");
		byte [] data = Base64.getDecoder().decode(image.split(",")[1]);
		if (data.length >= 500000)
			throw new IllegalArgumentException("Image exceeds 500KB");
		
	    BlobId blobId = BlobId.of("sept-image-store", id);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/plain").build();
		Blob blob = storage.create(blobInfo, data);
	}
	
	public void deleteImage(String name) {
		BlobId blobId = BlobId.of("sept-image-store", name);
		boolean deleted = storage.delete(blobId);
		if (!deleted) {
			System.out.println("ERROR - Could not delete image");
		}
	}
}
