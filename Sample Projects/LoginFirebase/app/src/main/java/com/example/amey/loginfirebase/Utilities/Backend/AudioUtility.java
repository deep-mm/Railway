package com.example.amey.loginfirebase.Utilities.Backend;

import android.app.ProgressDialog;
import android.net.Uri;

import com.example.amey.loginfirebase.Listener.GetAudioListener;
import com.example.amey.loginfirebase.Listener.AddAudioListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AudioUtility {

    private StorageReference mStorage;
    List<String> getAudio=new ArrayList<String>();
    int i;
    int counter=0;

    public void uploadAudio(final List<String> audio, final AddAudioListener listener)
    {
        String timeStamp;
        int n;

        mStorage = FirebaseStorage.getInstance().getReference();
        for(i=0;i<audio.size();i++) {

            n=audio.get(i).length();
            timeStamp=audio.get(i).substring((n-19),(n-4));
            StorageReference audioStorageReference = mStorage.child("Audio").child(timeStamp + ".3gp");
            Uri uri = Uri.fromFile(new File(audio.get(i)));
            audioStorageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    counter++;
                    Uri downloadUri=taskSnapshot.getDownloadUrl();
                    getAudio.add(downloadUri.toString());
                    System.out.println("added");
                    if(counter==(audio.size())){
                        System.out.println("true");
                        listener.onCompleteTask(getAudio);
                    }


                }
            });


        }

    }
    public void retrieveAudio(GetAudioListener listener)
    {

    }
}
