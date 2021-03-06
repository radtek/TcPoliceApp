package com.tc.activity.caseinfo;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sdses.bean.PoliceStateListBean;
import com.sdses.tool.UtilTc;
import com.sdses.tool.Values;
import com.tc.activity.SenceCheck;
import com.tc.app.TcApp;
import com.tc.application.R;
import com.tc.client.StringUtils;
import com.tc.util.CaseUtil;
import com.tc.util.CaseUtil2;
import com.tc.util.ConfirmDialog;
import com.tc.view.CustomProgressDialog;
import com.tc.view.DateWheelDialogN;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.util.StringUtil;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;

public class XckcBlActivity extends Activity {
    //采集项
    private EditText et_kyAjbh, et_kyKydw, et_kyZpbgdw, et_kyZpsj, et_kySy, et_kyBeginTime, et_kyEndTime, et_kyXcdd,
            et_kyXcbhqk, et_kyWd, et_kySd, et_kyFx, et_kyZhr, et_kyZhrDw, et_kyZhrZw, et_kyKyqk, et_kyHzt, et_kyZx,
            et_kyLx, et_kyLy, et_kyBlr, et_kyZtr, et_kyZxr, et_kyLxr, et_kyLyr, et_kyTime;

    EditText et_brqm1, et_brdw1, et_brzw1;
    EditText et_brqm2, et_brdw2, et_brzw2;
    EditText et_brqm3, et_brdw3, et_brzw3;
    EditText et_brqm4, et_brdw4, et_brzw4;
    EditText et_brqm5, et_brdw5, et_brzw5;
    EditText et_brqm6, et_brdw6, et_brzw6;

    EditText et_jzrqm1, et_jzrxb1, et_jzrsr1, et_jzrzz1;
    EditText et_jzrqm2, et_jzrxb2, et_jzrsr2, et_jzrzz2;
    TcApp ia;

    private RadioGroup rg_kyTq, rg_kyXctj, rg_kyGx;
    private ImageView btn_kcblReturn;

    private String newPath = "";
    //预览单独路径处理
    private String newPath1 = "";

    private String name = "";
    private String kyTq = "";
    private String kyXctj = "";
    private String kyGx = "";
    private RadioButton rb_qing, rb_yin, rb_yu, rb_wu, rb_xue, rb_bdxc, rb_ysxc, rb_zrg, rb_dg;
    private List<String> allList = new ArrayList<String>();
    private CommonAdapter2 mCommonAdapter2 = new CommonAdapter2(this);
    private ListView  docList;

    private final static int UPLOAD = 1;
    String errorMessage = "";
    private CustomProgressDialog progressDialog = null;
    private String EVIDENCE_NAME = "XCKYBL";

    // 进度框
    private void startProgressDialog(int type) {
        if (progressDialog == null) {
            progressDialog = CustomProgressDialog.createDialog(this);
            switch (type) {
                case UPLOAD:
                    progressDialog.setMessage("正在上传信息,请稍后");
                    break;
            }
        }
        progressDialog.show();
    }

    // 取消进度框
    private void stopProgressDialog() {
        if (progressDialog != null)
        {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    private void initWidgets() {
        //采集文字部分
        et_kyAjbh = (EditText) findViewById(R.id.et_kyAjbh);
        et_kyKydw = (EditText) findViewById(R.id.et_kyKydw);
        et_kyZpbgdw = (EditText) findViewById(R.id.et_kyZpbgdw);
        et_kyZpsj = (EditText) findViewById(R.id.et_kyZpsj);
        et_kySy = (EditText) findViewById(R.id.et_kySy);
        et_kyBeginTime = (EditText) findViewById(R.id.et_kyBeginTime);
        et_kyEndTime = (EditText) findViewById(R.id.et_kyEndTime);
        et_kyXcdd = (EditText) findViewById(R.id.et_kyXcdd);
        et_kyXcbhqk = (EditText) findViewById(R.id.et_kyXcbhqk);
        et_kyWd = (EditText) findViewById(R.id.et_kyWd);
        et_kySd = (EditText) findViewById(R.id.et_kySd);
        et_kyFx = (EditText) findViewById(R.id.et_kyFx);
        et_kyZhr = (EditText) findViewById(R.id.et_kyZhr);
        et_kyZhrDw = (EditText) findViewById(R.id.et_kyZhrDw);
        et_kyZhrZw = (EditText) findViewById(R.id.et_kyZhrZw);
        et_kyZx = (EditText) findViewById(R.id.et_kyZx);
        et_kyKyqk = (EditText) findViewById(R.id.et_kyKyqk);
        et_kyHzt = (EditText) findViewById(R.id.et_kyHzt);
        et_kyLx = (EditText) findViewById(R.id.et_kyLx);
        et_kyLy = (EditText) findViewById(R.id.et_kyLy);
        et_kyBlr = (EditText) findViewById(R.id.et_kyBlr);
        et_kyZtr = (EditText) findViewById(R.id.et_kyZtr);
        et_kyZxr = (EditText) findViewById(R.id.et_kyZxr);
        et_kyLxr = (EditText) findViewById(R.id.et_kyLxr);
        et_kyLyr = (EditText) findViewById(R.id.et_kyLyr);

        et_brqm1 = (EditText) findViewById(R.id.et_brqm1);
        et_brdw1 = (EditText) findViewById(R.id.et_brdw1);
        et_brzw1 = (EditText) findViewById(R.id.et_brzw1);

        et_brqm2 = (EditText) findViewById(R.id.et_brqm2);
        et_brdw2 = (EditText) findViewById(R.id.et_brdw2);
        et_brzw2 = (EditText) findViewById(R.id.et_brzw2);

        et_brqm3 = (EditText) findViewById(R.id.et_brqm3);
        et_brdw3 = (EditText) findViewById(R.id.et_brdw3);
        et_brzw3 = (EditText) findViewById(R.id.et_brzw3);

        et_brqm4 = (EditText) findViewById(R.id.et_brqm4);
        et_brdw4 = (EditText) findViewById(R.id.et_brdw4);
        et_brzw4 = (EditText) findViewById(R.id.et_brzw4);

        et_brqm5 = (EditText) findViewById(R.id.et_brqm5);
        et_brdw5 = (EditText) findViewById(R.id.et_brdw5);
        et_brzw5 = (EditText) findViewById(R.id.et_brzw5);

        et_brqm6 = (EditText) findViewById(R.id.et_brqm6);
        et_brdw6 = (EditText) findViewById(R.id.et_brdw6);
        et_brzw6 = (EditText) findViewById(R.id.et_brzw6);

        et_jzrqm1 = (EditText) findViewById(R.id.et_jzrqm1);
        et_jzrxb1 = (EditText) findViewById(R.id.et_jzrxb1);
        et_jzrsr1 = (EditText) findViewById(R.id.et_jzrsr1);
        et_jzrzz1 = (EditText) findViewById(R.id.et_jzrzz1);

        et_jzrqm2 = (EditText) findViewById(R.id.et_jzrqm2);
        et_jzrxb2 = (EditText) findViewById(R.id.et_jzrxb2);
        et_jzrsr2 = (EditText) findViewById(R.id.et_jzrsr2);
        et_jzrzz2 = (EditText) findViewById(R.id.et_jzrzz2);


        et_kyTime = (EditText) findViewById(R.id.et_kyTime);
        et_kyTime.setOnClickListener(new Onclick());

        et_jzrsr1.setOnClickListener(new Onclick());
        et_jzrsr2.setOnClickListener(new Onclick());

        //选择部分
        rg_kyTq = (RadioGroup) findViewById(R.id.rg_kyTq);
        rg_kyXctj = (RadioGroup) findViewById(R.id.rg_kyXctj);
        rg_kyGx = (RadioGroup) findViewById(R.id.rg_kyGx);
        et_kyBeginTime.setOnClickListener(new Onclick());
        et_kyEndTime.setOnClickListener(new Onclick());

        btn_kcblReturn = (ImageView) findViewById(R.id.btn_kcblReturn);
        btn_kcblReturn.setOnClickListener(new Onclick());
        name = getIntent().getStringExtra("name");
        et_kyAjbh.setText(name);
        et_kyZpsj.setOnClickListener(new Onclick());


        rb_qing = (RadioButton) findViewById(R.id.rb_qing);
        rb_yin = (RadioButton) findViewById(R.id.rb_yin);
        rb_yu = (RadioButton) findViewById(R.id.rb_yu);
        rb_wu = (RadioButton) findViewById(R.id.rb_wu);
        rb_xue = (RadioButton) findViewById(R.id.rb_xue);
        rb_bdxc = (RadioButton) findViewById(R.id.rb_bdxc);
        rb_ysxc = (RadioButton) findViewById(R.id.rb_ysxc);
        rb_zrg = (RadioButton) findViewById(R.id.rb_zrg);
        rb_dg = (RadioButton) findViewById(R.id.rb_dg);

        rg_kyTq.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == rb_yin.getId()) {
                    kyTq = "阴";
                } else if (checkedId == rb_qing.getId()) {
                    kyTq = "晴";
                } else if (checkedId == rb_yu.getId()) {
                    kyTq = "雨";
                } else if (checkedId == rb_wu.getId()) {
                    kyTq = "雾";
                } else if (checkedId == rb_xue.getId()) {
                    kyTq = "雪";
                }
            }
        });

        rg_kyXctj.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rb_bdxc.getId()) {
                    kyXctj = "变动现场";
                } else if (checkedId == rb_ysxc.getId()) {
                    kyXctj = "原始现场";
                }
            }
        });

        rg_kyGx.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rb_zrg.getId()) {
                    kyGx = "自然光";
                } else if (checkedId == rb_dg.getId()) {
                    kyGx = "灯光";
                }
            }
        });

        checkDoc();
        docList =(ListView)findViewById(R.id.xsaj_brbl_doc_list);
        docList.setAdapter(mCommonAdapter2);

    }

    class Onclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.et_kyBeginTime:
                    UtilTc.showLog("et_kyBeginTime");
                    DateWheelDialogN startDateChooseDialog = new DateWheelDialogN(XckcBlActivity.this, new DateWheelDialogN.DateChooseInterface() {
                        @Override
                        public void getDateTime(String time, boolean longTimeChecked) {
                            et_kyBeginTime.setText(time);

                        }
                    });
                    startDateChooseDialog.setDateDialogTitle("开始时间");
                    startDateChooseDialog.showDateChooseDialog();
                    break;
                case R.id.et_kyTime:
                    UtilTc.showLog("kyTime");
                    DateWheelDialogN kyTime = new DateWheelDialogN(XckcBlActivity.this, new DateWheelDialogN.DateChooseInterface() {
                        @Override
                        public void getDateTime(String time, boolean longTimeChecked) {
                            et_kyTime.setText(time);

                        }
                    });
                    kyTime.setDateDialogTitle("开始时间");
                    kyTime.showDateChooseDialog();
                    break;
                case R.id.et_jzrsr1:
                    UtilTc.showLog("et_kyBeginTime");
                    DateWheelDialogN jzrsr1 = new DateWheelDialogN(XckcBlActivity.this, new DateWheelDialogN.DateChooseInterface() {
                        @Override
                        public void getDateTime(String time, boolean longTimeChecked) {
                            et_jzrsr1.setText(time);

                        }
                    });
                    jzrsr1.setDateDialogTitle("开始时间");
                    jzrsr1.showDateChooseDialog();
                    break;
                case R.id.et_jzrsr2:
                    UtilTc.showLog("et_kyBeginTime");
                    DateWheelDialogN jzrsr2 = new DateWheelDialogN(XckcBlActivity.this, new DateWheelDialogN.DateChooseInterface() {
                        @Override
                        public void getDateTime(String time, boolean longTimeChecked) {
                            et_jzrsr2.setText(time);

                        }
                    });
                    jzrsr2.setDateDialogTitle("开始时间");
                    jzrsr2.showDateChooseDialog();
                    break;
                case R.id.et_kyEndTime:
                    DateWheelDialogN endDateChooseDialog = new DateWheelDialogN(XckcBlActivity.this, new DateWheelDialogN.DateChooseInterface() {
                        @Override
                        public void getDateTime(String time, boolean longTimeChecked) {
                            et_kyEndTime.setText(time);

                        }
                    });
                    endDateChooseDialog.setDateDialogTitle("结束时间");
                    endDateChooseDialog.showDateChooseDialog();
                    break;
                case R.id.et_kyZpsj:
                    DateWheelDialogN kyDateChooseDialog3 = new DateWheelDialogN(XckcBlActivity.this, new DateWheelDialogN.DateChooseInterface() {
                        @Override
                        public void getDateTime(String time, boolean longTimeChecked) {
                            et_kyZpsj.setText(time);

                        }
                    });
                    kyDateChooseDialog3.setDateDialogTitle("指派时间");
                    kyDateChooseDialog3.showDateChooseDialog();
                    break;
                case R.id.btn_kcblReturn:
                    finish();
                    break;
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_xckcbl);
        ia = (TcApp) TcApp.mContent;
        initWidgets();
    }

    //上传按钮
    public void BtnUploadBL(View view) {

        String startTime=et_kyBeginTime.getText().toString();
        String endTime=et_kyEndTime.getText().toString();
        if(endTime.compareTo(startTime)<=0)
        {
            Toast.makeText(getApplicationContext(),"结束时间要大于开始时间",Toast.LENGTH_SHORT).show();
            return;
        }
//        File fileStart = new File(Values.ALLFILES + "wtxt/XCKYBL/");
//        boolean flag = getFileName2(fileStart.listFiles(), name);
//
//        if (flag) {
//            //存在本地文件
//        } else {
//            try {
//                String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
//
//                File file = new File(sdcardPath + "/TC/wtxt/XCKYBL/");
//                if (!file.exists()) {
//                    file.mkdir();
//                }
//
//                String fileName = Values.PATH_BOOKMARK + "XCKYBL/" + name + "_" + UtilTc.getCurrentTime() + ".doc";
//                newPath = fileName;
//                InputStream inputStream = getAssets().open("xckybl.doc");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            doScan();
//        }

        try {
            String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();

            File file = new File(sdcardPath + "/TC/wtxt/XCKYBL/");
            if (!file.exists()) {
                file.mkdir();
            }
            if(StringUtils.isEmpty(newPath)) {
                String fileName = Values.PATH_BOOKMARK + "XCKYBL/" + name + "_" + UtilTc.getCurrentTime() + ".doc";
                newPath = fileName;
            }
            InputStream inputStream = getAssets().open("xckybl.doc");
        } catch (Exception e) {
            e.printStackTrace();
        }
        doScan(false);
        startProgressDialog(UPLOAD);
        new Thread(uploadRun).start();

        SendFile sf = new SendFile();
        sf.start();
    }

    private FTPClient myFtp;
    private PoliceStateListBean plb;
    String currentFilePaht = "";
    private String currentFile = "";
    private int fileCount = 0;
    private int mTotalSize = 0;

    public class SendFile extends Thread {
        private String currentPath = "";

        @Override
        public void run() {
            try {
                myFtp = new FTPClient();
                myFtp.connect("61.176.222.166", 21); // 连接
                myFtp.login("admin", "1234"); // 登录

                if (Values.dbjqList.size() > 0)
                    plb = Values.dbjqList.get(0);

                File fileStart = new File(Values.ALLFILES + "wtxt/XCKYBL/");
                getFileName(fileStart.listFiles(), name);

                //	myFtp.changeDirectory("wphoto");

                //	String path=Environment.getExternalStorageDirectory().getAbsolutePath()+"/temp.jpg";
                //	Log.e("path", "path"+path);

                for (int i = 0; i < bltxt.size(); i++) {
                    //判断上传到哪个文件夹
                    if (bltxt.get(i).endsWith(".doc")) {
                        myFtp.changeDirectory("../");
                        myFtp.changeDirectory("xcbl-xckybl");
                        currentPath = Values.PATH_xckybl;
                        currentFilePaht = "/xcbl-xckybl";
                    }

                    File file = new File(currentPath + bltxt.get(i));
                    fileCount = (int) file.length();

                    mTotalSize = fileCount;
                    currentFile = currentFilePaht + "/" + bltxt.get(i);
                    MyFTPDataTransferListener listener = new MyFTPDataTransferListener(bltxt.get(i));
                    myFtp.upload(file, listener); // 上传
                }
            } catch (Exception e) {
                e.printStackTrace();
                mHandler.sendEmptyMessage(Values.ERROR_UPLOAD);
            }
        }
    }

    private List<String> bltxt = new ArrayList<String>();

    private void getFileName(File[] files, String jqNum) {
        bltxt.clear();
        if (files != null)// nullPointer
        {
            for (File file : files) {
                if (file.isDirectory()) {
                    getFileName(file.listFiles(), jqNum);
                } else {
                    String fileName = file.getName();
                    if (fileName.contains(jqNum) && fileName.endsWith(".doc")) {
                        Log.e("e", "fileName" + fileName);
                        bltxt.add(fileName);
                    }
                }
            }
        }
    }

    private boolean getFileName2(File[] files, String jqNum) {
        boolean isFlag = false;
        if (files != null)// nullPointer
        {
            for (File file : files) {
                if (file.isDirectory()) {
                    getFileName2(file.listFiles(), jqNum);
                } else {
                    String fileName = file.getName();
                    if (fileName.contains(jqNum) && fileName.endsWith(".doc")) {
                        Log.e("e", "fileName" + fileName);
                        isFlag = true;

                    }
                }
            }
        }
        return isFlag;
    }

    private String mediaFormat = "";

    private class MyFTPDataTransferListener implements FTPDataTransferListener {
        String fileName = "";

        MyFTPDataTransferListener(String fileNameRet) {
            fileName = fileNameRet;
        }

        @Override
        public void aborted() {
            // TODO Auto-generated method stub
        }

        @Override
        public void completed() {// 上传成功
            // TODO Auto-generated method stub
            UtilTc.showLog("currentFile:" + currentFile);
            UtilTc.showLog("currentFile 后3位" + currentFile.substring(currentFile.length() - 3, currentFile.length()));
            mediaFormat = currentFile.substring(currentFile.length() - 3, currentFile.length());
            if (mediaFormat.equals("doc")) {
                mediaType = "文档";
            }
            new Thread(media).start();
            File file = new File(Values.PATH_xckybl + fileName);
            if (file.exists()) {
//                boolean isDel = file.delete();
            }
            Message msg;
            msg = Message.obtain();
            msg.what = Values.SUCCESS_UPLOAD;
            mHandler1.sendMessage(msg);
        }

        @Override
        public void failed() {// 上传失败
            // TODO Auto-generated method stub
            Message msg;
            msg = Message.obtain();
            msg.what = Values.ERROR_UPLOAD;
            mHandler1.sendMessage(msg);
        }

        @Override
        public void started() {// 上传开始
            // TODO Auto-generated method stub
            Message msg;
            msg = Message.obtain();
            msg.what = 2;
            mHandler1.sendMessage(msg);
        }

        @Override
        public void transferred(int length) {// 上传过程监听
            int progress = length;
            Message msg;
            msg = Message.obtain();
            msg.what = 1;
            msg.obj = progress;
            mHandler1.sendMessage(msg);
        }
    }

    Handler mHandler1 = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case Values.SUCCESS_UPLOAD:
                    UtilTc.showLog("文件上传成功");

                    stopProgressDialog();
                    //改变警情状态
                    break;
                case Values.ERROR_UPLOAD:
                    UtilTc.showLog("文件上传失败");
                    stopProgressDialog();
                    break;
            }
        }
    };

    //预览编辑
    public void BtneditBL(View view) {
        try {
            String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();

            File file = new File(sdcardPath + "/TC/wtxt/XCKYBL/");
            if (!file.exists()) {
                file.mkdir();
            }

            if(StringUtils.isEmpty(newPath1))
            {
                String fileName = Values.PATH_BOOKMARK + "XCKYBL/" + name + "_"    + "temp.doc";
                newPath1= fileName;
            }
            InputStream inputStream = getAssets().open("xckybl.doc");
        } catch (Exception e) {
            e.printStackTrace();
        }
        doScan(true);
        doOpenWord(true);
    }


    //打印笔录
    public void BtnPrintBL(View view) {
        try {
            String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();

            File file = new File(sdcardPath + "/TC/wtxt/XCKYBL/");
            if (!file.exists()) {
                file.mkdir();
            }
            if(StringUtils.isEmpty(newPath)) {
                String fileName = Values.PATH_BOOKMARK + "XCKYBL/" + name + "_" + UtilTc.getCurrentTime() + ".doc";
                newPath = fileName;
            }
            InputStream inputStream = getAssets().open("xckybl.doc");
        } catch (Exception e) {
            e.printStackTrace();
        }
        doScan(false);
        doOpenWord(false);
    }


    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case Values.SUCCESS_RECORDUPLOAD://

                    break;
                case Values.ERROR_CONNECT:
                    UtilTc.myToastForContent(getApplicationContext());
                    break;
                case Values.ERROR_OTHER:
                    UtilTc.myToast(getApplicationContext(), "" + errorMessage);
                    stopProgressDialog();
                    break;
                case Values.ERROR_NULLVALUEFROMSERVER:
                    UtilTc.showLog("服务器异常");
                    stopProgressDialog();
                    break;
                case Values.SUCCESS_FORRESULR:
                    UtilTc.myToast(getApplicationContext(), "" + errorMessage);
                    stopProgressDialog();
                    ia.sendHandleMsg(100, SenceCheck.waitingHandler);
                    break;
            }
        }
    };

    Runnable uploadRun = new Runnable() {
        @Override
        public void run() {
            String url_passenger = "http://61.176.222.166:8765/interface/xs/ADD_ZF_XS_XCKYBL.asp";
            HttpPost httpRequest = new HttpPost(url_passenger);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("A_ID", name));
            params.add(new BasicNameValuePair("XCKYDW", et_kyKydw.getText().toString()));
            params.add(new BasicNameValuePair("ZPBGDW", et_kyZpbgdw.getText().toString()));
            params.add(new BasicNameValuePair("ZPSJ", et_kyZpsj.getText().toString()));
            params.add(new BasicNameValuePair("KYSY", et_kySy.getText().toString()));
            params.add(new BasicNameValuePair("XCKYKSSJ", et_kyBeginTime.getText().toString()));
            params.add(new BasicNameValuePair("XCKYJSSJ", et_kyEndTime.getText().toString()));


            params.add(new BasicNameValuePair("XCDD", et_kyXcdd.getText().toString()));
            params.add(new BasicNameValuePair("XCBHQK", et_kyXcbhqk.getText().toString()));
            params.add(new BasicNameValuePair("TQ", kyTq));
            params.add(new BasicNameValuePair("WD", et_kyWd.getText().toString()));
            params.add(new BasicNameValuePair("SD", et_kySd.getText().toString()));
            params.add(new BasicNameValuePair("FX", et_kyFx.getText().toString()));
            params.add(new BasicNameValuePair("KGQXCTJ", kyXctj));
            params.add(new BasicNameValuePair("XCKYGX", kyGx));
            params.add(new BasicNameValuePair("XCKYZHR", et_kyZhr.getText().toString()));
            params.add(new BasicNameValuePair("XCKYZHRDW", et_kyZhrDw.getText().toString()));
            params.add(new BasicNameValuePair("XCKYZHRZW", et_kyZhrZw.getText().toString()));
            params.add(new BasicNameValuePair("XCKYQK", et_kyKyqk.getText().toString()));
            params.add(new BasicNameValuePair("XCKYHZT", et_kyHzt.getText().toString()));
            params.add(new BasicNameValuePair("XCKYZX", et_kyZx.getText().toString()));

            params.add(new BasicNameValuePair("XCKYLX", et_kyLx.getText().toString()));
            params.add(new BasicNameValuePair("XCKYLY", et_kyLy.getText().toString()));

            params.add(new BasicNameValuePair("BLR", et_kyBlr.getText().toString()));
            params.add(new BasicNameValuePair("ZTR", et_kyZtr.getText().toString()));
            params.add(new BasicNameValuePair("ZXR", et_kyZxr.getText().toString()));
            params.add(new BasicNameValuePair("LXR", et_kyLxr.getText().toString()));
            params.add(new BasicNameValuePair("LYR", et_kyLyr.getText().toString()));
            params.add(new BasicNameValuePair("KYSJ", et_kyTime.getText().toString()));
            Log.e("e", "params 是" + params);
            try {
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");
                httpRequest.setEntity(formEntity);
                //取得HTTP response
                HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
                Log.e("code", "code" + httpResponse.getStatusLine().getStatusCode());
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    String strResult = EntityUtils.toString(httpResponse.getEntity());
                    Log.e("e", "传回来的值是：" + strResult);
                    if (strResult == null || strResult.equals("")) {
                        mHandler.sendEmptyMessage(Values.ERROR_NULLVALUEFROMSERVER);
                        return;
                    }
                    //json 解析
                    JSONTokener jsonParser = new JSONTokener(strResult);
                    JSONObject person = (JSONObject) jsonParser.nextValue();
                    String code = person.getString("error code");
                    //{ "error code":0, "data":{ "message":"", "result":"盗抢车辆", "car":{ "hphm":"辽A12345", "hpzl":"蓝牌", "csys":"黑色", "fdjh":"888888", "cjhm":"987654321" } } }
                    if (code.trim().equals("0")) {
                        //    jsResult=person.getJSONObject("data");
                        JSONObject jb = person.getJSONObject("data");
                        errorMessage = jb.getString("message");
                        mHandler.sendEmptyMessage(Values.SUCCESS_FORRESULR);
                    } else if (code.trim().equals("10003")) {
                        JSONObject jb = person.getJSONObject("data");
                        errorMessage = jb.getString("message");
                        mHandler.sendEmptyMessage(Values.ERROR_OTHER);
                    } else if (code.trim().equals("10001")) {
                        JSONObject jb = person.getJSONObject("data");
                        errorMessage = jb.getString("message");
                        mHandler.sendEmptyMessage(Values.ERROR_OTHER);
                    }
                } else {
                    //   mHandler.sendEmptyMessage(Values.ERROR_CONNECT);
                }
            } catch (Exception e) {
                e.printStackTrace();
                //  mHandler.sendEmptyMessage(Values.ERROR_CONNECT);
            }
        }
    };


    private void doScan(boolean isPreview) {
        String startTime=et_kyBeginTime.getText().toString();
        String endTime=et_kyEndTime.getText().toString();
        if(endTime.compareTo(startTime)<=0)
        {
            Toast.makeText(getApplicationContext(),"结束时间要大于开始时间",Toast.LENGTH_SHORT).show();
            return;
        }
        //获取模板文件
//        File demoFile=new File(demoPath);
        //创建生成的文件
        File newFile;
        if(isPreview)
          newFile = new File(newPath1);
        else
            newFile = new File(newPath);

        if(newFile.exists())
            newFile.delete();


        Map<String, String> map = new HashMap<String, String>();
        map.put("$XCKYDW$", et_kyKydw.getText().toString());
        map.put("$BGDW$", et_kyZpbgdw.getText().toString());

        try{
            map.put("$NIAN1$", et_kyZpsj.getText().toString().substring(0,4));
            map.put("$YUE1$", et_kyZpsj.getText().toString().substring(5,7));
            map.put("$RI1$", et_kyZpsj.getText().toString().substring(8,10));
            map.put("$SHI1$", et_kyZpsj.getText().toString().substring(11,13));
            map.put("$FEN1$", et_kyZpsj.getText().toString().substring(14,16));
        }catch(Exception e){
            map.put("$NIAN1$", "");
            map.put("$YUE1$", "");
            map.put("$RI1$", "");
            map.put("$SHI1$", "");
            map.put("$FEN1$", "");
        }



        map.put("$KYSY$", et_kySy.getText().toString());

//        map.put("$XCKYKSSJ$",
//                et_kyBeginTime.getText().toString().substring(0,4)+"年"
//                        +et_kyBeginTime.getText().toString().substring(5,7)+"月"
//                        +et_kyBeginTime.getText().toString().substring(8,10)+"日"
//                        +et_kyBeginTime.getText().toString().substring(11,13)+"时"
//                        +et_kyBeginTime.getText().toString().substring(14,16)+"分"
//        );
//        map.put("$XCKYJSSJ$",
//                et_kyEndTime.getText().toString().substring(0,4)+"年"
//                        +et_kyEndTime.getText().toString().substring(5,7)+"月"
//                        +et_kyEndTime.getText().toString().substring(8,10)+"日"
//                        +et_kyEndTime.getText().toString().substring(11,13)+"时"
//                        +et_kyEndTime.getText().toString().substring(14,16)+"分"
//        );

        try {
            map.put("$NIAN2$", et_kyBeginTime.getText().toString().substring(0, 4));
            map.put("$YUE2$", et_kyBeginTime.getText().toString().substring(5, 7));
            map.put("$RI2$", et_kyBeginTime.getText().toString().substring(8, 10));
            map.put("$SHI2$", et_kyBeginTime.getText().toString().substring(11, 13));
            map.put("$FEN2$", et_kyBeginTime.getText().toString().substring(14, 16));
        }catch(Exception e){
            map.put("$NIAN3$", "");
            map.put("$YUE3$", "");
            map.put("$RI3$", "");
            map.put("$SHI3$", "");
            map.put("$FEN3$", "");
        }
        try{
            map.put("$NIAN3$", et_kyEndTime.getText().toString().substring(0,4));
            map.put("$YUE3$", et_kyEndTime.getText().toString().substring(5,7));
            map.put("$RI3$", et_kyEndTime.getText().toString().substring(8,10));
            map.put("$SHI3$", et_kyEndTime.getText().toString().substring(11,13));
            map.put("$FEN3$", et_kyEndTime.getText().toString().substring(14,16));
        }catch(Exception e){
            map.put("$NIAN3$", "");
            map.put("$YUE3$", "");
            map.put("$RI3$", "");
            map.put("$SHI3$", "");
            map.put("$FEN3$", "");
        }

        map.put("$XCDD$", et_kyXcdd.getText().toString());
        map.put("$SCBHQK$", et_kyXcbhqk.getText().toString());
        map.put("$TQ$", kyTq);
        map.put("$WD$", et_kyWd.getText().toString());
        map.put("$SD$", et_kySd.getText().toString());
        map.put("$FX$", et_kyFx.getText().toString());
        map.put("$XCTJ$", kyXctj);
        map.put("$GX$", kyGx);
        map.put("$ZHR$", et_kyZhr.getText().toString());

        map.put("$DW$", et_kyZhrDw.getText().toString());
        map.put("$ZW$", et_kyZhrZw.getText().toString());
        map.put("$XCKYQK$", et_kyKyqk.getText().toString());
        map.put("$ZTNUM$", et_kyHzt.getText().toString());

        map.put("$ZXNUM$", et_kyZx.getText().toString());
        map.put("$LX$", et_kyLx.getText().toString());
        map.put("$LYTIME$", et_kyLy.getText().toString());

        map.put("$BLR$", et_kyBlr.getText().toString());
        map.put("$ZTR$", et_kyZtr.getText().toString());
        map.put("$ZXR$", et_kyZxr.getText().toString());
        map.put("$LXR$", et_kyLxr.getText().toString());
        map.put("$LYR$", et_kyLyr.getText().toString());

        map.put("$BRQM1$", et_brqm1.getText().toString());
        map.put("$BRDW1$", et_brdw1.getText().toString());
        map.put("$BRZW1$", et_brzw1.getText().toString());

        map.put("$BRQM2$", et_brqm2.getText().toString());
        map.put("$BRDW2$", et_brdw2.getText().toString());
        map.put("$BRZW2$", et_brzw2.getText().toString());

        map.put("$BRQM3$", et_brqm3.getText().toString());
        map.put("$BRDW3$", et_brdw3.getText().toString());
        map.put("$BRZW3$", et_brzw3.getText().toString());

        map.put("$BRQM4$", et_brqm4.getText().toString());
        map.put("$BRDW4$", et_brdw4.getText().toString());
        map.put("$BRZW4$", et_brzw4.getText().toString());

        map.put("$BRQM5$", et_brqm5.getText().toString());
        map.put("$BRDW5$", et_brdw5.getText().toString());
        map.put("$BRZW5$", et_brzw5.getText().toString());

        map.put("$BRQM6$", et_brqm6.getText().toString());
        map.put("$BRDW6$", et_brdw6.getText().toString());
        map.put("$BRZW6$", et_brzw6.getText().toString());


        map.put("$JZRQM1$", et_jzrqm1.getText().toString());
        map.put("$JZRSEX1$", et_jzrxb1.getText().toString());
        map.put("$JZRSR1$", et_jzrsr1.getText().toString());
        map.put("$JZRZZ1$", et_jzrzz1.getText().toString());

        map.put("$JZRQM2$", et_jzrqm2.getText().toString());
        map.put("$JZRSEX2$", et_jzrxb2.getText().toString());
        map.put("$JZRSR2$", et_jzrsr2.getText().toString());
        map.put("$JZRZZ2$", et_jzrzz2.getText().toString());

        writeDoc(newFile, map);
    }

    /**
     * 调用手机中安装的可打开word的软件
     */
    private void doOpenWord(boolean isPreview) {
        String startTime=et_kyBeginTime.getText().toString();
        String endTime=et_kyEndTime.getText().toString();
        if(endTime.compareTo(startTime)<=0)
        {
            Toast.makeText(getApplicationContext(),"结束时间要大于开始时间",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        String fileMimeType = "application/msword";
        File file ;
        if(isPreview){
            file = new File(newPath1);
        }else{
            file = new File(newPath);
        }
        intent.setDataAndType(Uri.fromFile(file), fileMimeType);
        try {
            startActivity(intent);
            if(isPreview){
                startActivityForResult(intent,10);
            }
        } catch (ActivityNotFoundException e) {
            //检测到系统尚未安装OliveOffice的apk程序
            Toast.makeText(this, "未找到软件", Toast.LENGTH_LONG).show();
            //请先到www.olivephone.com/e.apk下载并安装
        }
    }

    private class CommonAdapter2 extends BaseAdapter {

        Activity mContent ;
        public CommonAdapter2(Activity mCtx){
            mContent =mCtx;
        }
        @Override
        public int getCount() {

            if (allList!=null)
            {
                UtilTc.showLog(" bltxt.size()"+ allList.size());
                return allList.size();
            }
            UtilTc.showLog("返回0了");
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (allList != null) {
                return allList.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            Log.e("e", "getView");
            CommonAdapter2.ViewHolder holder = null;
            View mView = convertView;
            if (mView == null) {
                mView = LayoutInflater.from(
                        getApplicationContext()).inflate(
                        R.layout.item_bltxt, null);
                holder = new  CommonAdapter2.ViewHolder();


                holder.tv_blTitle = (TextView) mView.findViewById(R.id.tv_blTitle);
                holder.iv_delete = (ImageView) mView.findViewById(R.id.iv_delete);
                holder.iv_edit = (ImageView) mView.findViewById(R.id.iv_edit);

                holder.parentLayout = (LinearLayout) mView.findViewById(R.id.lin_bl);

                mView.setTag(holder);
            } else {
                holder = (CommonAdapter2.ViewHolder) mView.getTag();
            }

            //word文件删除
            holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {

                    final ConfirmDialog confirmDialog = new ConfirmDialog(mContent, "确定要删除吗?", "删除", "取消");
                    confirmDialog.show();
                    confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
                        @Override
                        public void doConfirm() {
                            // TODO Auto-generated method stub
                            confirmDialog.dismiss();

                            File file=null;
                            String filename = allList.get(position);
                            file = new File(Values.PATH_BOOKMARK + EVIDENCE_NAME+"/"+filename);

                            if(file.exists())
                            {
                                boolean isDel = file.delete();
                                if(isDel)
                                {
                                    allList.remove(position);
                                    notifyDataSetChanged();
                                }
                            }
                        }

                        @Override
                        public void doCancel() {
                            // TODO Auto-generated method stub
                            confirmDialog.dismiss();
                        }
                    });


                }
            });

            //word文件编辑
            holder.iv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {


                    String filename = allList.get(position);
                    String filepath =  Values.PATH_BOOKMARK + EVIDENCE_NAME+"/"+filename;

                    CaseUtil2.doOpenWord(filepath,mContent,false);


                }
            });


            String ret = allList.get(position);
            UtilTc.showLog("ret       :"+ret);
            holder.tv_blTitle.setText(ret);
            return mView;
        }
        private class ViewHolder {
            TextView tv_blTitle;
            LinearLayout parentLayout;
            ImageView iv_delete,iv_edit;
        }
    }
    // -------------------------遍历文件
    private void checkFileName(File[] files, String jqNum)
    {

        if (files != null)// nullPointer
        {
            for (File file : files)
            {
                if (file.isDirectory()) {
                    checkFileName(file.listFiles(), jqNum);
                }
                else
                {
                    String fileName = file.getName();

                    if (fileName.startsWith(jqNum) && fileName.endsWith(".doc"))
                    {
                        allList.add(fileName);
                    }
                }
            }
        }
    }
    private void checkDoc()
    {
        File file = new File(Values.PATH_BOOKMARK + EVIDENCE_NAME+"/");
        checkFileName(file.listFiles(),name);
    }

    /**
     * demoFile 模板文件
     * newFile 生成文件
     * map 要填充的数据
     */
    public void writeDoc(File newFile, Map<String, String> map) {
        findViewById(R.id.btn_upload).setEnabled(true);
        try {
            InputStream in = getAssets().open("xckybl.doc");
//            FileInputStream in = new FileInputStream(demoFile);
            HWPFDocument hdt = new HWPFDocument(in);
            // Fields fields = hdt.getFields();
            // 读取word文本内容
            Range range = hdt.getRange();
            // System.out.println(range.text());

            // 替换文本内容
            for (Map.Entry<String, String> entry : map.entrySet()) {
                range.replaceText(entry.getKey(), entry.getValue());
            }
            ByteArrayOutputStream ostream = new ByteArrayOutputStream();
            FileOutputStream out = new FileOutputStream(newFile, true);
            hdt.write(ostream);
            // 输出字节流
            out.write(ostream.toByteArray());
            out.close();
            ostream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String mediaType = "";
    //上传媒体信息
    Runnable media = new Runnable() {
        @Override
        public void run() {
            String url_passenger = "http://61.176.222.166:8765/interface/addmeiti/";
            HttpPost httpRequest = new HttpPost(url_passenger);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //查询出出警时间和到达现场时间
//            mApp.getmDota().jq_queryTime(plb.getJqNum());
            params.add(new BasicNameValuePair("A_ID", name));
            params.add(new BasicNameValuePair("A_type", mediaType));
            params.add(new BasicNameValuePair("A_Format", mediaFormat));
            params.add(new BasicNameValuePair("A_MM", currentFile));
            try {
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
                        params, "UTF-8");
                httpRequest.setEntity(formEntity);
                // 取得HTTP response
                HttpResponse httpResponse = new DefaultHttpClient()
                        .execute(httpRequest);
                Log.e("code", "code"
                        + httpResponse.getStatusLine().getStatusCode());
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    String strResult = EntityUtils.toString(httpResponse
                            .getEntity());
                    Log.e("e", "上传媒体的值是：" + strResult);
                    // json 解析
                    JSONTokener jsonParser = new JSONTokener(strResult);
                    JSONObject person = (JSONObject) jsonParser.nextValue();
                    String code = person.getString("error code");
                    if (code.trim().equals("0")) {
                        //上传成功
                        //	mHandler.sendEmptyMessage(Values.SUCCESS_RECORDUPLOAD);
                    } else if (code.trim().equals("10003")) {
                        JSONObject jb = person.getJSONObject("data");
                        errorMessage = jb.getString("message");
                        mHandler.sendEmptyMessage(Values.ERROR_OTHER);
                    }
                } else {
                    mHandler.sendEmptyMessage(Values.ERROR_CONNECT);
                }
            } catch (Exception e) {
                e.printStackTrace();
                mHandler.sendEmptyMessage(Values.ERROR_CONNECT);
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10)
        {
            File file=new File(newPath1);
            if(file.exists()) {
                file.delete();
                Log.e("result","deleted");
            }
        }
    }
}
