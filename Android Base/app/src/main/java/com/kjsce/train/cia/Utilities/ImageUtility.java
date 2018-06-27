package com.kjsce.train.cia.Utilities;

import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kjsce.train.cia.Listener.AddImageListener;
import com.kjsce.train.cia.Listener.AddSingleImageUploadListener;
import com.kjsce.train.cia.Listener.GetImageListener;
import com.kjsce.train.cia.Listener.GetSingleImageListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageUtility
{
    private StorageReference mStorage;
    private int counter;

    public void uploadSingleImage(String image, String bogeyNumber, final AddSingleImageUploadListener listener) throws IOException {

        int n = image.length();
        String timeStamp = image.substring((n-19),(n-4));

        mStorage = FirebaseStorage.getInstance().getReference();
        StorageReference imageStorageReference = mStorage.child("Image").child(bogeyNumber).child(timeStamp + ".jpg");
        Uri uri = Uri.fromFile(new File(image));

        imageStorageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Uri downloadUri=taskSnapshot.getDownloadUrl();
                String newImage = downloadUri.toString();
                listener.onCompleteTask(newImage);

            }
        });
    }

    public void uploadImage(final List<String> imageL, final String bogeyNumber, final AddImageListener listener){

        if(imageL != null) {
            final List<String> imageS = new ArrayList<String>();
            for (int i = 0; i < imageL.size(); i++) {
                try {
                    uploadSingleImage(imageL.get(i), bogeyNumber, new AddSingleImageUploadListener() {
                        @Override
                        public void onCompleteTask(String image) {
                            imageS.add(image);
                            if (imageS.size() == imageL.size())
                                listener.onCompleteTask(imageS);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else
            listener.onCompleteTask(null);
    }

    public void retrieveSingleImage(final String imageS, final GetSingleImageListener listener) throws IOException {
        FirebaseStorage storage = FirebaseStorage.getInstance();

        final String fileName;

        StorageReference httpsReference = storage.getReferenceFromUrl(imageS);
        fileName = httpsReference.getName();

        File mainDir= Environment.getExternalStorageDirectory();
        final File mainFile=new File(mainDir,"/CIA/Inspection/Images");

        File localFile = File.createTempFile(fileName, ".jpg", mainFile);

        httpsReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                String imageL = mainFile + "/" +fileName;
                listener.onCompleteTask(imageL);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onCompleteTask(null);
            }
        });
    }

    public void retrieveImage(final List<String> imageS, final GetImageListener listener) throws IOException {

        if(imageS != null) {
            final ArrayList<String> imageL = new ArrayList<String>();
            counter = 0;
            for (int i = 0; i < imageS.size(); i++) {
                try {
                    retrieveSingleImage(imageS.get(i), new GetSingleImageListener() {
                        @Override
                        public void onCompleteTask(String image) {
                            counter++;
                            imageL.add(image);
                            if (counter == imageS.size()) {
                                listener.onCompleteTask(imageL);
                            }
                        }
                    });
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else
            listener.onCompleteTask(null);
    }

    /*public void removeSingleImage(final String imageS,final String bogeyNumber,final RemoveSingleImageListener listener){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(bogeyNumber);
        storageReference.
    }*/
}
