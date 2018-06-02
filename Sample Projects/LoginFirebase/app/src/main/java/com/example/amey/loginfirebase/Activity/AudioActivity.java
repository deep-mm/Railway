package com.example.amey.loginfirebase.Activity;

import android.app.ProgressDialog;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.amey.loginfirebase.Entity.Card.GeneralCard;
import com.example.amey.loginfirebase.Entity.Report.GeneralReport;
import com.example.amey.loginfirebase.Listener.AddAudioListener;
import com.example.amey.loginfirebase.Listener.AddGeneralReportListener;
import com.example.amey.loginfirebase.R;
import com.example.amey.loginfirebase.Utilities.Backend.AudioUtility;
import com.example.amey.loginfirebase.Utilities.Backend.GeneralReportUtility;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AudioActivity extends AppCompatActivity{
    private Button uploadAudio, retrieveAudio, recordAudio;
    private MediaRecorder mRecorder;
    private String mFileName = null;
    private static final String LOG_TAG = "Record_log";
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    private String timeStamp;
    private List<String> audio;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        audio = new ArrayList<String>();

        uploadAudio = (Button) findViewById(R.id.uploadAudio);
        retrieveAudio = (Button) findViewById(R.id.retrieveAudio);
        recordAudio = (Button)findViewById(R.id.recordAudio);
        mStorage = FirebaseStorage.getInstance().getReference();
        mProgress = new ProgressDialog(this);

        timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/" + timeStamp + ".3gp";

        recordAudio.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == event.ACTION_DOWN) {
                    startRecording();
                } else if (event.getAction() == event.ACTION_UP) {
                    stopRecording();
                }
                return false;
            }


        });
    }

        private void startRecording() {
            timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
            mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
            mFileName += "/"+timeStamp+".3gp";

            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setOutputFile(mFileName);
            audio.add(mFileName);

            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            try{
                mRecorder.prepare();
            }
            catch(IOException e){
                Log.e(LOG_TAG, "prepare() failed");
            }
            mRecorder.start();
        }

        private void stopRecording() {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;

        }

        public void uploading(View view)
        {
            AudioUtility audioUtility = new AudioUtility();
            audioUtility.uploadAudio(audio, new AddAudioListener() {
                @Override
                public void onCompleteTask(List<String> getAudio) {
                    for(int i=0;i<getAudio.size();i++)
                    System.out.println("getAudio:"+getAudio.get(i));
                }
            });

        }


    }



