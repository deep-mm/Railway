package com.kjsce.train.cia.Activity;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kjsce.train.cia.Adapter.CardDetailsAdapter;
import com.kjsce.train.cia.Entity.Card.DetailedCard;
import com.kjsce.train.cia.Entity.Problem.CoachExteriorProblem;
import com.kjsce.train.cia.Entity.Problem.CoachInteriorAmenitiesProblem;
import com.kjsce.train.cia.Entity.Problem.CoachInteriorCleanProblem;
import com.kjsce.train.cia.Entity.Problem.ToiletProblem;
import com.kjsce.train.cia.R;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import cafe.adriel.androidaudiorecorder.AndroidAudioRecorder;
import cafe.adriel.androidaudiorecorder.model.AudioChannel;
import cafe.adriel.androidaudiorecorder.model.AudioSampleRate;
import cafe.adriel.androidaudiorecorder.model.AudioSource;

public class CardDetails extends AppCompatActivity {

    private ImageView cameraButton, micButton, sendButton;
    private EditText textBox;
    private ImageButton backButton;
    private SharedData sharedData;
    private Spinner subTypeSpinner;
    private Helper helper;
    private CardDetailsAdapter cardDetailsAdapter;
    private ArrayList<DetailedCard> detailedCards;
    private String audioFilePath, imageFilePath, text, subTypeSelected, subType;
    private Uri filePath;
    private Boolean flag;
    private final int PICK_IMAGE_REQUEST = 10;
    private final int CAMERA = 100;
    private ArrayList<String> subTypes;
    private MaterialDialog materialDialog;
    private Boolean flag_subType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        initialize();

        if(!checkPermission()){
            new MaterialDialog.Builder(CardDetails.this)
                    .title("Give Access")
                    .content("You must give the app permissions to record audio and camera")
                    .positiveText("OK")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            checkPermission();
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                        }
                    })
                    .show();
        }

        final RecyclerView details = (RecyclerView) findViewById(R.id.details);
        cardDetailsAdapter = new CardDetailsAdapter(detailedCards, CardDetails.this);
        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(getApplicationContext());
        details.setLayoutManager(mlayoutmanager);
        details.setAdapter(cardDetailsAdapter);
        details.setItemAnimator(new DefaultItemAnimator());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera();
            }
        });

        micButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                record();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        textBox.addTextChangedListener(filterTextWatcher);

        subTypes = getTypes(sharedData.getType());
        subTypes.add(0,"Select a subtype");
        subTypes.add("Other");
        final ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                this,R.layout.support_simple_spinner_dropdown_item,subTypes){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinner_item);
        subTypeSpinner.setAdapter(spinnerArrayAdapter1);

        subTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);

                if(position > 0){
                    subTypeSelected = selectedItemText;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(getIntent().hasExtra("flag")){
            flag_subType = getIntent().getExtras().getBoolean("flag");
        }

        if(!flag_subType){
            subType = getIntent().getExtras().getString("subType");
            subTypes.clear();
            subTypes.add(subType);
            subTypeSpinner.setEnabled(false);
        }
    }

    private TextWatcher filterTextWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // DO THE CALCULATIONS HERE AND SHOW THE RESULT AS PER YOUR CALCULATIONS
            if (!s.toString().isEmpty()) {
                micButton.setVisibility(View.GONE);
                cameraButton.setVisibility(View.GONE);
                sendButton.setVisibility(View.VISIBLE);
            } else {
                micButton.setVisibility(View.VISIBLE);
                cameraButton.setVisibility(View.VISIBLE);
                sendButton.setVisibility(View.GONE);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void initialize() {
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        cameraButton = (ImageView) findViewById(R.id.camera_button);
        micButton = (ImageView) findViewById(R.id.mic_button);
        sendButton = (ImageView) findViewById(R.id.send_button);
        textBox = (EditText) findViewById(R.id.textBox);
        backButton = (ImageButton) findViewById(R.id.back_button);
        detailedCards = new ArrayList<DetailedCard>();
        subTypes = new ArrayList<String>();
        subTypeSpinner = (Spinner) findViewById(R.id.sub_type_spinner);
        subTypeSelected = "";
    }

    public int getPosition(String type){
        int i;
        for(i=0;i<subTypes.size();i++){
            if(subTypes.get(i).equalsIgnoreCase(type))
                return i;
        }
        return 0;
    }

    public void record() {
        String timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        audioFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        audioFilePath += "/" + timeStamp + ".3gp";
        int color = getResources().getColor(R.color.colorPrimaryDark);
        int requestCode = 0;
        AndroidAudioRecorder.with(this)
                // Required
                .setFilePath(audioFilePath)
                .setColor(color)
                .setRequestCode(requestCode)

                // Optional
                .setSource(AudioSource.MIC)
                .setChannel(AudioChannel.STEREO)
                .setSampleRate(AudioSampleRate.HZ_48000)
                .setAutoStart(true)
                .setKeepDisplayOn(true)

                // Start recording
                .record();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                // Great! User has recorded and saved the audio file
                getInputName();
                System.out.println("File Path: "+audioFilePath);
                //Create an object with audio file path in it
                DetailedCard detailedCard = new DetailedCard();
                detailedCard.setComment(audioFilePath);
                detailedCards.add(detailedCard);
                cardDetailsAdapter.notifyDataSetChanged();

            } else if (resultCode == RESULT_CANCELED) {
                // Oops! User has canceled the recording
            }
        }

        else if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null)
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageFilePath = saveImage(bitmap);
                getInputName();
                System.out.println("File Path: "+imageFilePath);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else if (requestCode == CAMERA && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imageFilePath = saveImage(thumbnail);
            getInputName();
            System.out.println("File Path: "+imageFilePath);
        }
    }

    public void getInputName() {

        new MaterialDialog.Builder(this)
                .title("Description")
                .content("Enter description for the Audio/Image")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("Enter Text Here", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        text = input.toString();
                    }
                }).show();
    }

    public ArrayList<String> getTypes(String problem){

        ArrayList<String> type = new ArrayList<String>();
        switch(problem){

            case "Toilets":
                return (new ToiletProblem().getTypes());

            case "Coach Interior Amenities":
                return (new CoachInteriorAmenitiesProblem().getTypes());

            case "Coach Interior Cleanliness":
                return (new CoachInteriorCleanProblem().getTypes());

            case "Coach Exterior":
                return (new CoachExteriorProblem().getTypes());

            default:
                return type;

        }
    }

    public void camera(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                chooseImage();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);

    }

    public String saveImage(Bitmap thumbnail) {
        String timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
        ContextWrapper c = new ContextWrapper(getApplicationContext());

        try {
            File f = new File(imageFilePath, timeStamp + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);

            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());
            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public boolean checkPermission() {

        flag = true;
        RxPermissions.getInstance(this).request(Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(granted -> {
            if (granted) { // Always true pre-M
                //Granted
            } else {
                flag = false;
            }
        });

        RxPermissions.getInstance(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(granted -> {
            if (granted) { // Always true pre-M
                //Granted
            } else {
                flag = false;
            }
        });

        RxPermissions.getInstance(this).request(Manifest.permission.CAMERA).subscribe(granted -> {
            if (granted) { // Always true pre-M
                //Granted
            } else {
                flag = false;
            }
        });

        RxPermissions.getInstance(this).request(Manifest.permission.RECORD_AUDIO).subscribe(granted -> {
            if (granted) { // Always true pre-M
                //Granted
            } else {
                flag = false;
            }
        });

        return flag;
    }

    public void onProgressStart(){
        materialDialog = new MaterialDialog.Builder(CardDetails.this)
                .title("Syncing Data")
                .content("Please Wait")
                .progress(true, 0)
                .show();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void onProgressStop(){
        materialDialog.hide();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
