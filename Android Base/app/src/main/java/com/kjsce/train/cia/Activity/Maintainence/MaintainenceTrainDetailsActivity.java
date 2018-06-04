package com.kjsce.train.cia.Activity.Maintainence;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.MediaRecorder;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kjsce.train.cia.Activity.Inspection.InpectionTrainDetailsActivity;
import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Adapter.CheckBoxAdapter;
import com.kjsce.train.cia.Adapter.ImageAdapter;
import com.kjsce.train.cia.Adapter.ItemArrayAdapter;
import com.kjsce.train.cia.Adapter.MaintainenceImageAdapter;
import com.kjsce.train.cia.Entity.Card.DetailedCard;
import com.kjsce.train.cia.Entity.Item;
import com.kjsce.train.cia.Entity.Problem.CoachExteriorProblem;
import com.kjsce.train.cia.Entity.Problem.CoachInteriorAmenitiesProblem;
import com.kjsce.train.cia.Entity.Problem.CoachInteriorCleanProblem;
import com.kjsce.train.cia.Entity.Problem.Problem;
import com.kjsce.train.cia.Entity.Problem.ToiletProblem;
import com.kjsce.train.cia.R;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class MaintainenceTrainDetailsActivity extends AppCompatActivity {

    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    ImageButton camera,mic;
    RelativeLayout done,notdone;
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
    ArrayList<String> checkBoxList = new ArrayList<String>();
    MaintainenceImageAdapter adapter = null;
    CheckBoxAdapter checkBoxAdapter = null;
    Boolean flag = false;
    TextView typeval,descriptionval;
    SharedData sd;
    List<DetailedCard> detailedCard;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintainence_train_details);
        sd = new SharedData(getApplicationContext());


        typeval=findViewById(R.id.typeval);
        descriptionval=findViewById(R.id.descriptionval);

        if(getIntent().hasExtra("type")) {
            typeval.setText(getIntent().getExtras().getString("type"));
            descriptionval.setText(getIntent().getExtras().getString("description"));
            position = getIntent().getExtras().getInt("position");
        }



        camera = (ImageButton)findViewById(R.id.camera);
        mic = (ImageButton)findViewById(R.id.mic);
        done = (RelativeLayout)findViewById(R.id.done);
        notdone = (RelativeLayout)findViewById(R.id.notdone);
        checkBoxList.add("Fittings Not Ok");
        checkBoxList.add("Flood Drainage Not Ok");
        checkBoxList.add("Cleanliness Not Ok");
        checkBoxList.add("Smell Not Ok");
        checkBoxList.add("Bio Toilet Not Ok");

        detailedCard = sd.getDetailedCard();

        DetailedCard d = detailedCard.get(position);
        Problem p = null;
        if(d.getImage()!=null)
        {
            paths = d.getImage();
        }
        if(d.getAudio()!=null)
        {
            paths.addAll(d.getAudio());
        }

        if(d.getProblem()!=null)
        {
            p = d.getProblem();
        }

        if(p!=null)
        {
            sd.setTypeList(p.getList());
        }
        if(d.getComment()!=null)
        {
            descriptionval.setText(d.getComment());
        }






        typeval.setText(d.getType());

        checkBoxList = getTypes();


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        notdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        final RecyclerView card = (RecyclerView)findViewById(R.id.imgurl);
        adapter = new MaintainenceImageAdapter(paths,MaintainenceTrainDetailsActivity.this);
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


        mStorage = FirebaseStorage.getInstance().getReference();

        //mRecordLabel = (TextView) findViewById(R.id.recordLabel);
        //mRecordBtn = (Button) findViewById(R.id.recordBtn);
        mProgress = new ProgressDialog(this);
        timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/" + timeStamp + ".3gp";


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
    //Handling callback


}
