package com.example.amey.loginfirebase.Utilities.Backend;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.example.amey.loginfirebase.Entity.BogeyEntity;
import com.example.amey.loginfirebase.Entity.Card.DetailedCard;
import com.example.amey.loginfirebase.Listener.AddBogeyAudioListener;
import com.example.amey.loginfirebase.Listener.GetAudioListener;
import com.example.amey.loginfirebase.Listener.AddAudioListener;
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

public class AudioUtility {

    private StorageReference mStorage;
    List<String> audioS=new ArrayList<String>();
    List<String> audioL=new ArrayList<String>();
    int i;
    int counterU=0;
    int counterR=0;

    public void uploadBogeyAudio(final List<BogeyEntity> bogeyEntities, final AddBogeyAudioListener listener){
        final List<BogeyEntity> newBogeyEntityList = new ArrayList<BogeyEntity>();
        for(int i=0;i<bogeyEntities.size();i++){
            final List<DetailedCard> detailedCards = bogeyEntities.get(i).getDetailedCard();
            for(int j=0;j<detailedCards.size();j++){
                final int counterI = i;
                final int counterJ = j;
                uploadAudio(detailedCards.get(j).getAudio(), new AddAudioListener() {
                    @Override
                    public void onCompleteTask(List<String> audioS) {
                        BogeyEntity newBogeyEntity = new BogeyEntity();
                        newBogeyEntity = bogeyEntities.get(counterI);
                        newBogeyEntity.getDetailedCard().get(counterJ).setAudio(audioS);

                        if(counterI == bogeyEntities.size()-1 && counterJ == detailedCards.size()-1){
                            listener.onCompleteTask(newBogeyEntityList);
                        }
                    }
                });
            }
        }
    }


    public void uploadAudio(final List<String> audioL, final AddAudioListener listener)
    {
        String timeStamp;
        int n;

        mStorage = FirebaseStorage.getInstance().getReference();
        for(i=0;i<audioL.size();i++) {

            n=audioL.get(i).length();
            timeStamp=audioL.get(i).substring((n-19),(n-4));
            StorageReference audioStorageReference = mStorage.child("Audio").child(timeStamp + ".3gp");
            Uri uri = Uri.fromFile(new File(audioL.get(i)));
            audioStorageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    counterU++;
                    Uri downloadUri=taskSnapshot.getDownloadUrl();
                    audioS.add(downloadUri.toString());
                    System.out.println("added");
                    if(counterU==(audioL.size())){
                        System.out.println("true");
                        listener.onCompleteTask(audioS);
                    }
                }
            });
        }
    }

    public void retrieveAudio(final List<String> audioS,final GetAudioListener listener) throws IOException {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        for (int i = 0; i < audioS.size(); i++)
        {
            final String fileName;

            StorageReference httpsReference= storage.getReferenceFromUrl(audioS.get(i));
            fileName=httpsReference.getName();
            File mainDir=Environment.getExternalStorageDirectory();
            final File mainFile=new File(mainDir,"/CIA/Inspection/Audios");

            File localFile = File.createTempFile(fileName, ".3gp", mainFile);

            httpsReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    counterR++;
                    String mFileName=mainFile+"/"+fileName;
                    System.out.println(mFileName);
                    audioL.add(mFileName);
                    System.out.println("added");
                    if(counterR==(audioS.size())){
                        System.out.println("true");
                        listener.onCompleteTask(audioL);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });

        }
    }
}
