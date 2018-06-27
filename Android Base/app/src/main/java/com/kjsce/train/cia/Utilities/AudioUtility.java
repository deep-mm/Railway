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
import com.kjsce.train.cia.Listener.AddAudioListener;
import com.kjsce.train.cia.Listener.AddSingleAudioUploadListener;
import com.kjsce.train.cia.Listener.GetAudioListener;
import com.kjsce.train.cia.Listener.GetSingleAudioListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AudioUtility {
    private StorageReference mStorage;
    private int counter;

    public void uploadSingleAudio(String audio, String bogeyNo, final AddSingleAudioUploadListener addSingleAudioUploadListener) {
        int n = audio.length();
        String timeStamp = audio.substring((n - 19), (n - 4));

        mStorage = FirebaseStorage.getInstance().getReference();
        StorageReference audioStorageReference = mStorage.child("Audio").child(bogeyNo).child(timeStamp + ".3gp");
        Uri uri = Uri.fromFile(new File(audio));

        audioStorageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Uri downloadUri = taskSnapshot.getDownloadUrl();
                String newAudio = downloadUri.toString();
                addSingleAudioUploadListener.onCompleteTask(newAudio);

            }
        });
    }

    public void uploadAudio(final List<String> audioL, final String bogeyNumber, final AddAudioListener addAudioListener) {

        final List<String> audioS = new ArrayList<String>();
        if(audioL != null) {
            for (int i = 0; i < audioL.size(); i++) {
                try {
                    uploadSingleAudio(audioL.get(i), bogeyNumber, new AddSingleAudioUploadListener() {
                        @Override
                        public void onCompleteTask(String audio) {
                            audioS.add(audio);
                            if (audioS.size() == audioL.size())
                                addAudioListener.onCompleteTask(audioS);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else
            addAudioListener.onCompleteTask(null);
    }

    public void retrieveSingleAudio(final String audioS, final GetSingleAudioListener getSingleAudioListener) throws IOException {
        FirebaseStorage storage = FirebaseStorage.getInstance();

        final String fileName;

        StorageReference httpsReference = storage.getReferenceFromUrl(audioS);
        fileName = httpsReference.getName();

        File mainDir = Environment.getExternalStorageDirectory();
        final File mainFile = new File(mainDir, "/CIA/Inspection/Audios");

        File localFile = File.createTempFile(fileName, ".3gp", mainFile);

        httpsReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                String audioL = mainFile + "/" + fileName;
                getSingleAudioListener.onCompleteTask(audioL);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                getSingleAudioListener.onCompleteTask(null);
            }
        });
    }

    public void retrieveAudio(final List<String> audioS, final GetAudioListener getAudioListener) throws IOException {
        final ArrayList<String> audioL = new ArrayList<String>();
        counter = 0;
        if (audioS != null) {
            for (int i = 0; i < audioS.size(); i++) {
                try {
                    retrieveSingleAudio(audioS.get(i), new GetSingleAudioListener() {
                        @Override
                        public void onCompleteTask(String audio) {
                            counter++;
                            audioL.add(audio);
                            if (counter == audioS.size()) {
                                getAudioListener.onCompleteTask(audioL);
                            }
                        }
                    });
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else
            getAudioListener.onCompleteTask(null);
    }
}
