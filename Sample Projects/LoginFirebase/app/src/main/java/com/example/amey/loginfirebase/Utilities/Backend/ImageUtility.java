package com.example.amey.loginfirebase.Utilities.Backend;

import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.example.amey.loginfirebase.Entity.BogeyEntity;
import com.example.amey.loginfirebase.Entity.Card.GeneralCard;
import com.example.amey.loginfirebase.Listener.AddBogeyImageListener;
import com.example.amey.loginfirebase.Listener.AddGeneralImageListener;
import com.example.amey.loginfirebase.Listener.GetImageListener;
import com.example.amey.loginfirebase.Listener.AddImageListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageUtility {

    private StorageReference mStorage;
    int i;
    List<String> imageS=new ArrayList<String>();
    List<String> imageL=new ArrayList<String>();
    int counterU=0;
    int counterR=0;

    public void uploadGeneralImage(final List<GeneralCard> generalCardList, final AddGeneralImageListener listener){

    }

    public void uploadBogeyImage(final List<BogeyEntity> bogeyEntities, final AddBogeyImageListener listener){

    }


    public void uploadImage(final List<String> imageL, final AddImageListener listener)
    {
        String timeStamp;
        int n;

        mStorage = FirebaseStorage.getInstance().getReference();
        for(i=0;i<imageL.size();i++) {

            n = imageL.get(i).length();
            timeStamp = imageL.get(i).substring((n-19),(n-4));
            StorageReference imageStorageReference = mStorage.child("Image").child(timeStamp + ".jpg");
            Uri uri = Uri.fromFile(new File(imageL.get(i)));
            imageStorageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    counterU++;
                    Uri downloadUri=taskSnapshot.getDownloadUrl();
                    imageS.add(downloadUri.toString());
                    System.out.println("added");
                    if(counterU==(imageL.size())){
                        System.out.println("true");
                        listener.onCompleteTask(imageS);
                    }
                }
            });
        }
    }

    public void retrieveImage(final List<String> imageS,final GetImageListener listener) throws IOException {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        for (int i = 0; i < imageS.size(); i++)
        {
            final String fileName;

            StorageReference httpsReference= storage.getReferenceFromUrl(imageS.get(i));
            fileName = httpsReference.getName();

            File mainDir= Environment.getExternalStorageDirectory();
            final File mainFile=new File(mainDir,"/CIA/Inspection/Images");

            File localFile = File.createTempFile(fileName, ".jpg", mainFile);

            httpsReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    counterR++;
                    String mFileName=mainFile+"/"+fileName;
                    System.out.println(mFileName);
                    imageL.add(mFileName);
                    System.out.println("added");
                    if(counterR==(imageS.size())){
                        System.out.println("true");
                        listener.onCompleteTask(imageL);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {

                }
            });

        }
    }
}
