package com.tc.activity.caseinfo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sdses.tool.UtilTc;
import com.sdses.tool.Values;
import com.tc.application.R;
import com.tc.util.CaseUtil;
import com.tc.util.DateUtil;
import com.tc.view.DateWheelDialogN;
import com.tc.view.FileListView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.tc.application.R.id.end;
import static com.tc.application.R.id.map;

//??????
public class BrBlActivity extends CaseBaseActivity {

    private static final String TAG = BrBlActivity.class.getSimpleName();
    private ImageView mImgBack;
    private TextView mTitleTx;
    private EditText mEdtNumber;
    private EditText mEdtOfficeName;
    private EditText mCheckPlace;
    private EditText mCheckerName;
    private EditText mCheckerOffice;
    private EditText mCheckerDuty;
    private EditText mProcess;
    private EditText mWorkerOne;
    private EditText mWorkerTwo;
    private EditText mEdtBr;
    private Button mBtnPreview;
    private Button mBtnUpload;
    private Button mBtnPrint;
    private EditText mBrName;
    private EditText mBrOffice;
    private EditText mBrDuty;

    public static final String BR_NAME = "BRBL";//??????
    private EditText mEdtObj;
    private EditText mEdtPlane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_br_bl);
        initView();
        initData();
        initFileList(BR_NAME);
    }

    private void initView() {
        mImgBack = (ImageView)findViewById(R.id.img_back);
        mTitleTx = (TextView)findViewById(R.id.tx_head_title);
        mTitleTx.setText("辨认笔录");
        mImgBack.setOnClickListener(mOnClicKListener);
        mFileListView = (FileListView)findViewById(R.id.file_listview);


        mEdtNumber = (EditText)findViewById(R.id.edt_number);
        mEdtOfficeName = (EditText)findViewById(R.id.edt_office_name);
        mEditStartTime = (EditText)findViewById(R.id.edt_start_time);
        mEdtEndTime = (EditText)findViewById(R.id.edt_end_time);
        mEdtEndTime.setOnClickListener(mOnClicKListener);
        mEditStartTime.setOnClickListener(mOnClicKListener);
        mCheckPlace = (EditText)findViewById(R.id.edt_place);

        mCheckerName = (EditText)findViewById(R.id.edt_checker_name);
        mCheckerOffice = (EditText)findViewById(R.id.edt_checker_office);
        mCheckerDuty = (EditText)findViewById(R.id.edt_checker_duty);

        mBrName = (EditText)findViewById(R.id.edt_br_name);
        mBrOffice = (EditText)findViewById(R.id.edt_br_office);
        mBrDuty = (EditText)findViewById(R.id.edt_br_duty);

        mEdtObj = (EditText)findViewById(R.id.edt_obj);
        mEdtPlane = (EditText)findViewById(R.id.edt_plan);
        mProcess= (EditText)findViewById(R.id.edt_process);

        mWorkerOne = (EditText)findViewById(R.id.edt_kanyan1);
        mWorkerTwo = (EditText)findViewById(R.id.edt_kan2);
        mEdtBr = (EditText)findViewById(R.id.edt_witness);

        mBtnPreview = (Button)findViewById(R.id.btn_preview);
        mBtnUpload = (Button)findViewById(R.id.btn_upload);
        mBtnPrint = (Button)findViewById(R.id.btn_print);
        mBtnPreview.setOnClickListener(mOnClicKListener);
        mBtnUpload.setOnClickListener(mOnClicKListener);
        mBtnPrint.setOnClickListener(mOnClicKListener);
    }

    private void initData() {
        Bundle extras = getIntent().getExtras();
        mName=extras.getString("name");
        Log.i(TAG,"name = "+mName);
        mEdtNumber.setText(mName);
    }

    private View.OnClickListener mOnClicKListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.img_back:
                    finish();
                    break;
                case R.id.edt_start_time:
                    DateWheelDialogN chooseDialog = new DateWheelDialogN(BrBlActivity.this, new DateWheelDialogN
                            .DateChooseInterface() {
                        @Override
                        public void getDateTime(String time, boolean longTimeChecked) {
                            mEditStartTime.setText(time);
                        }
                    });
                    chooseDialog.setDateDialogTitle("开始时间");
                    chooseDialog.showDateChooseDialog();
                    break;
                case R.id.edt_end_time:
                    DateWheelDialogN endDialog = new DateWheelDialogN(BrBlActivity.this, new DateWheelDialogN
                            .DateChooseInterface() {
                        @Override
                        public void getDateTime(String time, boolean longTimeChecked) {
                            mEdtEndTime.setText(time);
                        }
                    });
                    endDialog.setDateDialogTitle("结束时间");
                    endDialog.showDateChooseDialog();
                    break;
                case R.id.btn_preview:
                    previewDoc();
                    break;
                case R.id.btn_print:
                    printDoc();
                    break;
                case R.id.btn_upload:
                    uploadDoc();
                    break;
            }
        }

    };

    @Override
    protected void getFileName(boolean isPreview) {
        super.getFileName(isPreview);
        try{
            File file = new File( Values.PATH_BOOKMARK + BR_NAME);
            if(!file.exists()){
                file.mkdir();
            }
            if(isPreview){
                String fileName = Values.PATH_BOOKMARK+BR_NAME+"/"+mName+"_"+".doc";
                mPreFilePath = fileName;
            }else{
                if(TextUtils.isEmpty(mNewPath)){
                    String fileName = Values.PATH_BOOKMARK+BR_NAME+"/"+mName+"_"+ UtilTc.getCurrentTime()+".doc";
                    mNewPath = fileName;
                }
            }
        }catch (Exception e){
            Log.e(TAG,"getFileName ",e);
        }
    }

    @Override
    protected void doScan(boolean isPreview){
        File newFile ;
        if(isPreview){
            newFile = new File(mPreFilePath);
        }else{
            newFile = new File(mNewPath);
        }
        String startTime = mEditStartTime.getText().toString();
        String endTime = mEdtEndTime.getText().toString();
        if(!DateUtil.isDateRight(startTime,endTime)){
            Toast.makeText(getApplicationContext(),"结束时间要大于开始时间",Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String,String> map = new HashMap<>();
        map.put("$GAJ$",mEdtOfficeName.getText().toString());
        DateUtil.handleDateMap(startTime,endTime,map);
        map.put("$BRPLACE$",mCheckPlace.getText().toString());
        map.put("$BARNAME$",mCheckerName.getText().toString());
        map.put("$BAROFFICE$",mCheckerOffice.getText().toString());
        map.put("$BARDUTY$",mCheckerDuty.getText().toString());
        map.put("$BRRNAME$",mBrName.getText().toString());

        map.put("$BRROFFICE$",mBrOffice.getText().toString());
        map.put("$BRRDUTY$",mBrDuty.getText().toString());
        map.put("$BROBJ$",mEdtObj.getText().toString());
        map.put("$BRPLANE$",mEdtPlane.getText().toString());

        map.put("$PROCESS$",mProcess.getText().toString());
        map.put("$WORKER1$",mWorkerOne.getText().toString());
        map.put("$WORKER2$",mWorkerTwo.getText().toString());
        map.put("$BRNAME$", mEdtBr.getText().toString());
        CaseUtil.writeDoc("brbl.doc",newFile,map);
    }

    protected void uploadDoc() {
        super.uploadDoc();
//        startProcessDialog();
//        if(TextUtils.isEmpty(mNewPath)){
//            getFileName();
//            doScan();
//        }
//        String ftpPath = "xcbl-xz-brbl";
//        CaseUtil.startUploadFile(mNewPath,ftpPath,mName,mHandler);
    }

    @Override
    protected String geFtpPth() {
        return "xcbl-xz-brbl";
    }

    private void printDoc() {
        String startTime = mEditStartTime.getText().toString();
        String endTime = mEdtEndTime.getText().toString();
        if(!DateUtil.isDateRight(startTime,endTime)){
            Toast.makeText(getApplicationContext(),"结束时间要大于开始时间",Toast.LENGTH_SHORT).show();
            return;
        }
        getFileName(false);
        doScan(false);
        CaseUtil.doOpenWord(mNewPath,this);
    }

    private void previewDoc() {
        String startTime = mEditStartTime.getText().toString();
        String endTime = mEdtEndTime.getText().toString();
        if(!DateUtil.isDateRight(startTime,endTime)){
            Toast.makeText(getApplicationContext(),"结束时间要大于开始时间",Toast.LENGTH_SHORT).show();
            return;
        }
        getFileName(true);
        doScan(true);
        CaseUtil.doOpenWord(mPreFilePath,this);
    }
}
