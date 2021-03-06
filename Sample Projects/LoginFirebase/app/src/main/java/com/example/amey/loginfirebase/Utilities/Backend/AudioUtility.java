package com.example.amey.loginfirebase.Utilities.Backend;

import android.net.Uri;
import com.example.amey.loginfirebase.Entity.BogeyEntity;
import com.example.amey.loginfirebase.Entity.Card.DetailedCard;
import com.example.amey.loginfirebase.Entity.Card.GeneralCard;
import com.example.amey.loginfirebase.Listener.AddAudioListener;
import com.example.amey.loginfirebase.Listener.AddBogeyAudioListener;
import com.example.amey.loginfirebase.Listener.AddGeneralAudioListener;
import com.example.amey.loginfirebase.Listener.AddSingleAudioUploadListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AudioUtility {

    private StorageReference mStorage;
    List<GeneralCard> newGeneralCards = new ArrayList<GeneralCard>();

    /*public void uploadGeneralAudio(final List<GeneralCard> generalCardList, final AddGeneralAudioListener listener){

        for(int i=0;i<generalCardList.size();i++){
            final int counterI = i;
            uploadAudio(generalCardList.get(i).getAudio(), new AddAudioListener() {
                @Override
                public void onCompleteTask(List<String> audioS) {
                    List<GeneralCard> newGeneralCards = new ArrayList<GeneralCard>();
                    GeneralCard generalCard = generalCardList.get(counterI);
                    generalCard.setAudio(audioS);
                    newGeneralCards.add(generalCard);

                    if(counterI == generalCardList.size()-1){
                        listener.onCompleteTask(newGeneralCards);
                    }
                }
            });
        }
    }

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
                        newBogeyEntityList.add(newBogeyEntity);
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
    }*/

    //Final
    public void uploadSingleAudio(String audio, final AddSingleAudioUploadListener listener) throws IOException {

        int n = audio.length();
        String timeStamp = audio.substring((n-19),(n-4));

        mStorage = FirebaseStorage.getInstance().getReference();
        StorageReference audioStorageReference = mStorage.child("Audio").child(timeStamp + ".3gp");
        Uri uri = Uri.fromFile(new File(audio));

        audioStorageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Uri downloadUri=taskSnapshot.getDownloadUrl();
                String newAudio = downloadUri.toString();
                listener.onCompleteTask(newAudio);

            }
        });
    }

    //Final
    public void uploadAudio(final List<String> audioL, final AddAudioListener listener){

        final List<String> audioS = new ArrayList<String>();
        for(int i=0;i<audioL.size();i++){
            try {
                uploadSingleAudio(audioL.get(i), new AddSingleAudioUploadListener() {
                    @Override
                    public void onCompleteTask(String audio) {
                        audioS.add(audio);
                        if(audioS.size() == audioL.size())
                            listener.onCompleteTask(audioS);
                    }
                });
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    //Final
    public void uploadGeneralAudio(final List<GeneralCard> generalCardList, final AddGeneralAudioListener listener){
        System.out.println("Called");
        for(int i=0;i<generalCardList.size();i++){
            final int counterI = i;
            uploadAudio(generalCardList.get(i).getAudio(), new AddAudioListener() {
                @Override
                public void onCompleteTask(List<String> audioS) {

                    GeneralCard generalCard = new GeneralCard();
                    generalCard.copy(generalCardList.get(counterI));
                    generalCard.setImage(generalCardList.get(counterI).getImage());
                    generalCard.setAudio(audioS);

                    newGeneralCards.add(generalCard);
                    if(counterI == generalCardList.size()-1){
                        listener.onCompleteTask(newGeneralCards);
                    }
                }
            });
        }
    }

    //Final
    public void uploadBogeyAudio(final List<BogeyEntity> bogeyEntities, final AddBogeyAudioListener listener){
        final List<BogeyEntity> newBogeyEntityList = new ArrayList<BogeyEntity>();

        for(int i=0;i<bogeyEntities.size();i++){
            newBogeyEntityList.add(new BogeyEntity().copy(bogeyEntities.get(i)));
        }

        for(int i=0;i<bogeyEntities.size();i++){
            final List<DetailedCard> detailedCards = bogeyEntities.get(i).getDetailedCard();
            for(int j=0;j<detailedCards.size();j++){
                final int counterI = i;
                final int counterJ = j;
                System.out.println("i: " + i + "j: " + j + " size: " + detailedCards.get(j).getAudio().size());
                uploadAudio(detailedCards.get(j).getAudio(), new AddAudioListener() {
                    @Override
                    public void onCompleteTask(List<String> audioS) {

                        newBogeyEntityList.get(counterI).getDetailedCard().get(counterJ).setAudio(audioS);

                        if(counterI == bogeyEntities.size()-1 && counterJ == detailedCards.size()-1){
                            listener.onCompleteTask(newBogeyEntityList);
                        }
                    }
                });
            }
        }
    }
}
