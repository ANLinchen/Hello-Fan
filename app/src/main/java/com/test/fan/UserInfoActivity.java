package com.test.fan;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.test.model.dto.UserInfo;
import com.test.util.ACache;
import com.test.util.OkHttpRequest;

import java.io.File;
import java.io.IOException;

import static com.test.util.Constant.SERVER_URL;
import static com.test.util.Constant.SEVER_PORT;

public class UserInfoActivity extends AppCompatActivity {
    private Handler handler;
    private TextView username_tv, nickname_tv, school_tv, sex_tv, brief_tv, studydays_tv;
    private ImageView avatar_iv;
    //aCache缓存
    private ACache aCache;
    private Dialog mBottomDialog;
    private Button btn_edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        username_tv = (TextView) findViewById(R.id.username_tv);
        nickname_tv = (TextView) findViewById(R.id.nickname_tv);
        school_tv = (TextView) findViewById(R.id.school_tv);
        sex_tv = (TextView) findViewById(R.id.sex_tv);
        brief_tv = (TextView) findViewById(R.id.brief_tv);
        avatar_iv = (ImageView) findViewById(R.id.avatar_iv);
        studydays_tv = (TextView) findViewById(R.id.studydays);
        btn_edit = (Button) findViewById(R.id.btn_edit);
        aCache = ACache.get(this);
        init();
    }

    private void goToEdit() {
        Intent intent = new Intent(this, EditUserInfoActivity.class);
        startActivityForResult(intent,200);
    }

    private void init() {
//        avatar_iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                initBottomDialog();
//                mBottomDialog.show();
//            }
//        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToEdit();
            }
        });
        //先从缓存里面获取
        UserInfo userInfo = (UserInfo) aCache.getAsObject("userInfo");
        if (userInfo != null) {
            setUserInfo(userInfo);
        } else {
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.obj != null) {
                        UserInfo userInfo = (UserInfo) msg.obj;
                        //将user缓存起来
                        aCache.put("userInfo", userInfo);
                        setUserInfo(userInfo);
                    } else {
                        Toast.makeText(UserInfoActivity.this, "获取用户信息失败", Toast.LENGTH_SHORT).show();
                    }
                }
            };
            getUserInfo();
        }
    }

    private void setUserInfo(UserInfo userInfo) {
        Picasso.get().load("https://avatars3.githubusercontent.com/u/30856589?s=460&v=4").
                transform(new CircleTransform()).into(avatar_iv);
        if (userInfo.getStudyDays() != null)
            studydays_tv.setText(userInfo.getStudyDays());
        if (userInfo.getUserName() != null)
            username_tv.setText(userInfo.getUserName());
        if (userInfo.getNickName() != null)
            nickname_tv.setText(userInfo.getNickName());
        if (userInfo.getSchool() != null)
            school_tv.setText(userInfo.getSchool());
        if (userInfo.getSex() != null)
            sex_tv.setText(userInfo.getSex());
        if (userInfo.getBrief() != null)
            brief_tv.setText(userInfo.getBrief());
    }

    private void getUserInfo() {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        final String userName = sp.getString("userName", "");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                if ("".equals(userName))
                    message.obj = null;
                else {
                    String requestUrl = SERVER_URL + ":" + SEVER_PORT + "/user/getUserInfo?userName=" + userName;
                    System.out.println("请求Url" + requestUrl);
                    try {
                        String json = OkHttpRequest.get(requestUrl);
                        message.obj = JSON.parseObject(json, UserInfo.class);
                    } catch (IOException e) {
                        message.obj = null;
                    }
                }
                handler.sendMessage(message);

            }
        }).start();
    }

    public class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UserInfo userInfo = (UserInfo) aCache.getAsObject("userInfo");
        if (userInfo != null) {
            setUserInfo(userInfo);
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            switch (requestCode) {
//                case 200:
//                    if (data != null) {
//                        String realPathFromUri = PhotoUtil.getRealPathFromUri(this, data.getData());
//                        avatar_iv.setImageURI(Uri.fromFile(new File(realPathFromUri)));
//                    } else {
//                        Toast.makeText(this, "图片损坏，请重新选择", Toast.LENGTH_SHORT).show();
//                    }
//                    if (mBottomDialog != null && mBottomDialog.isShowing()) {
//                        mBottomDialog.dismiss();
//                    }
//                    break;
//                case 300:
//                    if (data != null) {
//                        String realPathFromUri = PhotoUtil.getRealPathFromUri(this, data.getData());
//                        avatar_iv.setImageURI(Uri.fromFile(new File(realPathFromUri)));
//                    } else {
//                        Toast.makeText(this, "图片损坏，请重新选择", Toast.LENGTH_SHORT).show();
//                    }
//                    if (mBottomDialog != null && mBottomDialog.isShowing()) {
//                        mBottomDialog.dismiss();
//                    }
//                    break;
//                default:
//                        break;
//            }
//        }
//    }


//    private void initBottomDialog() {
//        mBottomDialog = new Dialog(this, R.style.dialog_bottom_full);
//        mBottomDialog.setCanceledOnTouchOutside(false);
//        Window window = mBottomDialog.getWindow();
//        //设置Dialog外窗口可以点击
//        WindowManager.LayoutParams layoutParams = window.getAttributes();
//        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;//核心
//        window.setAttributes(layoutParams);
//        window.setDimAmount(0f);
//        window.setGravity(Gravity.BOTTOM);
//        window.setWindowAnimations(R.style.share_animation);
//        View dialog_view = View.inflate(this, R.layout.dialog_photo, null);
//        dialog_view.findViewById(R.id.cancel_tv).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mBottomDialog != null && mBottomDialog.isShowing()) {
//                    mBottomDialog.dismiss();
//                }
//            }
//        });
//        dialog_view.findViewById(R.id.photograph_tv).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                startActivityForResult(intent,200);
//            }
//        });
//        dialog_view.findViewById(R.id.album_tv).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
//                startActivityForResult(intent, 300);
//            }
//        });
//        window.setContentView(dialog_view);
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
//    }

}
