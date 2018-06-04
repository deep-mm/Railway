package com.kjsce.train.cia.Utilities.Backend;


import android.net.Uri;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kjsce.train.cia.Entity.BogeyEntity;
import com.kjsce.train.cia.Entity.Card.DetailedCard;
import com.kjsce.train.cia.Entity.Card.GeneralCard;
import com.kjsce.train.cia.Listeners.AddAudioListener;
import com.kjsce.train.cia.Listeners.AddBogeyAudioListener;
import com.kjsce.train.cia.Listeners.AddBogeyImageListener;
import com.kjsce.train.cia.Listeners.AddGeneralAudioListener;
import com.kjsce.train.cia.Listeners.AddGeneralImageListener;
import com.kjsce.train.cia.Listeners.AddImageListener;
import com.kjsce.train.cia.Listeners.AddSingleImageUploadListener;
import com.kjsce.train.cia.Listeners.GetAudioListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class ImageUtility {

    private StorageReference mStorage;
    List<GeneralCard> newGeneralCards = new ArrayList<GeneralCard>();
    /*
    int i;

    List<String> imageS=new ArrayList<String>();
    List<String> imageL=new ArrayList<String>();
    int counterU=0;
    int counterR=0;*/

   /*


    public void uploadImage(final List<String> imageL, final AddImageListener listener)
    {
        String timeStamp;
        int n;

        System.out.println("Image size: " + imageL.size());
        mStorage = FirebaseStorage.getInstance().getReference();
        for(i=0;i<imageL.size();i++) {

            final int counterU = i;
            n = imageL.get(i).length();
            timeStamp = imageL.get(i).substring((n-19),(n-4));
            StorageReference imageStorageReference = mStorage.child("Image").child(timeStamp + ".jpg");
            Uri uri = Uri.fromFile(new File(imageL.get(i)));
            imageStorageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri downloadUri=taskSnapshot.getDownloadUrl();
                    imageS.add(downloadUri.toString());

                    if(counterU==(imageL.size()-1)){

                        List<String> images = new ArrayList<String>();
                        for(int i=0;i<imageS.size();i++){
                            images.add(imageS.get(i));
                        }
                        imageS.clear();

                        listener.onCompleteTask(images);
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
    }*/

    //Final
    public void uploadSingleImage(String image, final AddSingleImageUploadListener listener) throws IOException {

        int n = image.length();
        String timeStamp = image.substring((n-19),(n-4));

        mStorage = FirebaseStorage.getInstance().getReference();
        StorageReference imageStorageReference = mStorage.child("Image").child(timeStamp + ".jpg");
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

    //Final
    public void uploadImage(final List<String> imageL, final AddImageListener listener){

        final List<String> imageS = new ArrayList<String>();
        for(int i=0;i<imageL.size();i++){
            try {
                uploadSingleImage(imageL.get(i), new AddSingleImageUploadListener() {
                    @Override
                    public void onCompleteTask(String image) {
                        imageS.add(image);
                        if(imageS.size() == imageL.size())
                            listener.onCompleteTask(imageS);
                    }
                });
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    //Final
    public void uploadGeneralImage(final List<GeneralCard> generalCardList, final AddGeneralImageListener listener){
        System.out.println("Called");
        for(int i=0;i<generalCardList.size();i++){
            final int counterI = i;
            uploadImage(generalCardList.get(i).getImage(), new AddImageListener() {
                @Override
                public void onCompleteTask(List<String> imageS) {

                    GeneralCard generalCard = new GeneralCard();
                    generalCard.copy(generalCardList.get(counterI));
                    generalCard.setImage(imageS);
                    generalCard.setAudio(generalCardList.get(counterI).getAudio());

                    newGeneralCards.add(generalCard);
                    if(counterI == generalCardList.size()-1){
                        listener.onCompleteTask(newGeneralCards);
                    }
                }
            });
        }
    }

    //Final
    public void uploadBogeyImage(final List<BogeyEntity> bogeyEntities, final AddBogeyImageListener listener){
        final List<BogeyEntity> newBogeyEntityList = new ArrayList<BogeyEntity>();

        for(int i=0;i<bogeyEntities.size();i++){
            newBogeyEntityList.add(new BogeyEntity().copy(bogeyEntities.get(i)));
        }

        for(int i=0;i<bogeyEntities.size();i++){
            final List<DetailedCard> detailedCards = bogeyEntities.get(i).getDetailedCard();
            for(int j=0;j<detailedCards.size();j++){
                final int counterI = i;
                final int counterJ = j;
                System.out.println("i: " + i + "j: " + j + " size: " + detailedCards.get(j).getImage().size());
                uploadImage(detailedCards.get(j).getImage(), new AddImageListener() {
                    @Override
                    public void onCompleteTask(List<String> imageS) {

                        newBogeyEntityList.get(counterI).getDetailedCard().get(counterJ).setImage(imageS);

                        if(counterI == bogeyEntities.size()-1 && counterJ == detailedCards.size()-1){
                            listener.onCompleteTask(newBogeyEntityList);
                        }
                    }
                });
            }
        }
    }


}
