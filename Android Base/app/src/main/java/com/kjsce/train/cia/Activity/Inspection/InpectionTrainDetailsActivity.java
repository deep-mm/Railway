package com.kjsce.train.cia.Activity.Inspection;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaRecorder;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Adapter.CheckBoxAdapter;
import com.kjsce.train.cia.Adapter.ImageAdapter;
import com.kjsce.train.cia.Entity.Card.DetailedCard;
import com.kjsce.train.cia.Entity.Problem.CoachExteriorProblem;
import com.kjsce.train.cia.Entity.Problem.CoachInteriorAmenitiesProblem;
import com.kjsce.train.cia.Entity.Problem.CoachInteriorCleanProblem;
import com.kjsce.train.cia.Entity.Problem.ElectricalProblem;
import com.kjsce.train.cia.Entity.Problem.Problem;
import com.kjsce.train.cia.Entity.Problem.ToiletProblem;
import com.kjsce.train.cia.Entity.Problem.UndergearProblem;
import com.kjsce.train.cia.Entity.Problem.WindowProblem;
import com.kjsce.train.cia.R;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class InpectionTrainDetailsActivity extends AppCompatActivity {
    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    ImageButton camera,mic;
    static String path;
    private Button mRecordBtn;
    private TextView mRecordLabel;
    private MediaRecorder mRecorder;
    private String mFileName = null;
    private static final String LOG_TAG = "Record_log";
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    private String timeStamp;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 10;
    public int SELECT_PICTURE = 100;
    List<String> paths = new ArrayList<String>();
    ArrayList<String> images = new ArrayList<String>();
    ArrayList<String> audio = new ArrayList<String>();
    ArrayList<String> checkBoxList = new ArrayList<String>();
    ArrayList<Boolean> box = new ArrayList<Boolean>();
    ImageAdapter adapter = null;
    CheckBoxAdapter checkBoxAdapter = null;
    Boolean flag = false;
    TextView typeval,descriptionval;;
    RelativeLayout done,notdone;
    SharedData sd;
    List<Boolean> typeList;
    List<DetailedCard> detailedCards;
    DetailedCard detailedCard;
    int position;
    MaterialDialog materialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inpection_train_details);

            typeval=findViewById(R.id.typeval);
            descriptionval=findViewById(R.id.descriptionval);

            if(getIntent().hasExtra("type")) {
                typeval.setText(getIntent().getExtras().getString("type"));
                descriptionval.setText(getIntent().getExtras().getString("comment"));
                position = getIntent().getExtras().getInt("position");
            }

            camera = (ImageButton)findViewById(R.id.camera);
            mic = (ImageButton)findViewById(R.id.mic);
            sd = new SharedData(getApplicationContext());


            detailedCards = new ArrayList<DetailedCard>();

            if(check()) {
                detailedCard = detailedCards.get(position);
                paths = detailedCard.getImage();
                paths.addAll(detailedCard.getAudio());
                Problem p = detailedCard.getProblem();
                sd.setTypeList(p.getList());
                typeval.setText(detailedCard.getType());
                descriptionval.setText(detailedCard.getComment());
                checkBoxList = getTypes();
            }
            else{
                checkBoxList = getTypes();
                List<Boolean> type = new ArrayList<Boolean>();
                for(int i=0;i<checkBoxList.size();i++)
                    type.add(false);

                sd.setTypeList(type);
                detailedCards = new ArrayList<DetailedCard>();
            }



            done = (RelativeLayout) findViewById(R.id.done);
            notdone = (RelativeLayout) findViewById(R.id.notdone);

        final RecyclerView card = (RecyclerView)findViewById(R.id.imgurl);
        adapter = new ImageAdapter(paths,InpectionTrainDetailsActivity.this);
        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(getApplicationContext());
        card.setLayoutManager(mlayoutmanager);
        card.setAdapter(adapter);
        card.setItemAnimator(new DefaultItemAnimator());

        final RecyclerView check_box = (RecyclerView)findViewById(R.id.checkBox);
        checkBoxAdapter = new CheckBoxAdapter(checkBoxList,getApplicationContext());
        RecyclerView.LayoutManager mlayoutmanager1 = new LinearLayoutManager(getApplicationContext());
        check_box.setLayoutManager(mlayoutmanager1);
        check_box.setAdapter(checkBoxAdapter);
        check_box.setItemAnimator(new DefaultItemAnimator());

            camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPictureDialog();
                }
            });

        mProgress = new ProgressDialog(this);
        timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/" + timeStamp + ".3gp";

        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!flag){
                    mic.setBackground(getResources().getDrawable(R.drawable.recording_stop));
                    flag = true;
                    materialDialog = new MaterialDialog.Builder(InpectionTrainDetailsActivity.this)
                            .title("Recording")
                            .content("Speak Now")
                            .progress(true, 0)
                            .show();
                    startRecording();
                }

                else{
                    mic.setBackground(getResources().getDrawable(R.drawable.mic));
                    flag = false;
                    materialDialog.hide();
                    stopRecording();
                }
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i=0;i<paths.size();i++){
                    if(paths.get(i).contains("3gp"))
                        audio.add(paths.get(i));

                    else
                        images.add(paths.get(i));

                }
                typeList = sd.getTypeList();
                for (int i=0;i<checkBoxList.size();i++){
                    if(typeList.get(i)==null)
                        box.add(false);
                    else
                        box.add(typeList.get(i));
                }
                Problem problem = getProblem(box);
                problem.setList(box);
                DetailedCard detailedCard = new DetailedCard(problem,typeval.getText().toString(),images,descriptionval.getText().toString(),
                        false,audio);
                if(check()){
                    detailedCards.remove(position);
                    detailedCards.add(position,detailedCard);
                }
                else {
                    if(detailedCards==null)
                        detailedCards = new ArrayList<DetailedCard>();

                    System.out.println("mmmmm"+detailedCard);
                    detailedCards.add(detailedCard);
                }
                sd.setDetailedCards(detailedCards);
                Intent intent = new Intent(getApplicationContext(),InspectionBogeyReportActivity.class);
                startActivity(intent);
            }
        });

        notdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        }

        public ArrayList<String> getTypes(){

        ArrayList<String> type = new ArrayList<String>();
            switch(typeval.getText().toString()){

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


        public Problem getProblem(List<Boolean> types_checked){

            ArrayList<String> type = new ArrayList<String>();
            switch(typeval.getText().toString()){

                case "Toilets":
                    return (new ToiletProblem(types_checked.get(0),types_checked.get(1),types_checked.get(2),types_checked.get(3),types_checked.get(4)));

                case "Coach Interior Amenities":
                    return (new CoachInteriorAmenitiesProblem(types_checked.get(0),types_checked.get(1),types_checked.get(2),types_checked.get(3),types_checked.get(4)));

                case "Coach Interior Cleanliness":
                    return (new CoachInteriorCleanProblem(types_checked.get(0),types_checked.get(1),types_checked.get(2),types_checked.get(3),types_checked.get(4),types_checked.get(5)));

                case "Coach Exterior":
                    return (new CoachExteriorProblem(types_checked.get(0),types_checked.get(1),types_checked.get(2),types_checked.get(3)));

                case "Undergear":
                    return (new UndergearProblem());

                case "Electricals":
                    return (new ElectricalProblem());

                case "Windows":
                    return (new WindowProblem());

                default:
                    return null;

            }
        }

    public List<Boolean> getProblemClass(Problem p){

        ArrayList<String> type = new ArrayList<String>();
        List<Boolean> problems = new ArrayList<Boolean>();
        switch(typeval.getText().toString()){

            case "Toilets":
                ToiletProblem toiletProblem = (ToiletProblem)p;
                problems.add(toiletProblem.isFittings());
                problems.add(toiletProblem.isFloorDrainage());
                problems.add(toiletProblem.isCleanliness());
                problems.add(toiletProblem.isSmell());
                problems.add(toiletProblem.isBiotoilet());
                return problems;

            case "Coach Interior Amenities":
                CoachInteriorAmenitiesProblem coachInteriorAmenitiesProblem = (CoachInteriorAmenitiesProblem)p;
                problems.add(coachInteriorAmenitiesProblem.isFittings());
                problems.add(coachInteriorAmenitiesProblem.isWindowGlass());
                problems.add(coachInteriorAmenitiesProblem.isWindowShutter());
                problems.add(coachInteriorAmenitiesProblem.isBerth());
                problems.add(coachInteriorAmenitiesProblem.isWallLaminates());
                return problems;

            case "Coach Interior Cleanliness":
                CoachInteriorCleanProblem coachInteriorCleanProblem = (CoachInteriorCleanProblem)p;
                problems.add(coachInteriorCleanProblem.isGangwayCleanliness());
                problems.add(coachInteriorCleanProblem.isStickers());
                problems.add(coachInteriorCleanProblem.isSeatingCleanliness());
                problems.add(coachInteriorCleanProblem.isPests());
                problems.add(coachInteriorCleanProblem.isRodent());
                problems.add(coachInteriorCleanProblem.isBedrollQuality());
                return problems;

            case "Coach Exterior":
                CoachExteriorProblem coachExteriorProblem = (CoachExteriorProblem)p;
                problems.add(coachExteriorProblem.isPaintQuality());
                problems.add(coachExteriorProblem.isBogeyCleanliness());
                problems.add(coachExteriorProblem.isRoofCleanliness());
                problems.add(coachExteriorProblem.isWindowGlass());
                return problems;

            case "Undergear":
                break;

            case "Electricals":
                break;

            case "Windows":
                break;

            default:
                return null;

        }
        return null;
    }

        public boolean check(){

            try{
                detailedCards = sd.getDetailedCard();
                if(position<(detailedCards.size()))
                    return true;

                else
                    return false;
            }
            catch (Exception e){
                return false;
            }
        }

    private void startRecording() {
        requestAudioPermissions();
        timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/" + timeStamp + ".3gp";

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        paths.add(mFileName);
        adapter.notifyDataSetChanged();
        //uploadAudio();
    }

    private void showPictureDialog()
    {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        100);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
        else {
            // Permission has already been granted


        }
        RxPermissions.getInstance(this).request(Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
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
                    }else {
                            Toast.makeText(this, "Permission Required", Toast.LENGTH_LONG).show();
                        }
                });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null)
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                saveImage(bitmap);
                paths.add(filePath.toString());
                adapter.notifyDataSetChanged();
                //imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else if (requestCode == CAMERA && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            paths.add(filePath.toString());
            adapter.notifyDataSetChanged();
            //imageView.setImageBitmap(thumbnail);
            saveImage(thumbnail);
        }
    }

    public String saveImage(Bitmap thumbnail) {
        String timeStamp1 = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        ContextWrapper c = new ContextWrapper(getApplicationContext());
        File myDir=new File(Environment.getExternalStorageDirectory(),"/CIA/Inspection/Audio/Sent");
        // have the object build the directory structure, if needed.
        if (!myDir.exists()) {
            myDir.mkdirs();
        }

        try {
            String name= timeStamp1;
            paths.add(name);
            adapter.notifyDataSetChanged();
            File f = new File(myDir, name + ".jpg");
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


    private void requestAudioPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            //When permission is not granted by user, show them message why this permission is needed.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {
                Toast.makeText(this, "Please grant permissions to record audio", Toast.LENGTH_LONG).show();

                //Give user option to still opt-in the permissions
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_RECORD_AUDIO);

            } else {
                // Show user dialog to grant permission to record audio
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_RECORD_AUDIO);
            }
        }
        //If permission is granted, then go ahead recording audio
        else if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_GRANTED) {

            //Go ahead with recording audio now

        }
    }


    //Handling callback
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_RECORD_AUDIO: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay!
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permissions Denied to record audio", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    public void progressStart(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void progressStop(){
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void onBackPressed(){
        new MaterialDialog.Builder(InpectionTrainDetailsActivity.this)
                .title("Confirm")
                .content("All data will be lost, Confirm?")
                .positiveText("Yes")
                .negativeText("No")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        Intent i = new Intent(getApplicationContext(),InspectionBogeyReportActivity.class);
                        startActivity(i);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                    }
                })
                .show();

    }

    }

