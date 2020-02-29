package com.blt.helperenglish.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.text.Editable;
import android.view.*;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.databinding.DataBindingUtil;
import com.blt.helperenglish.R;
import com.blt.helperenglish.common.retro.CallbackMethods;
import com.blt.helperenglish.common.retro.impl.APICallBackListener;
import com.blt.helperenglish.constant.PagesNames;
import com.blt.helperenglish.constant.ResponseType;
import com.blt.helperenglish.databinding.LayoutTranslateFloatingWidgetBinding;
import com.blt.helperenglish.model.translate.TranslateModel;
import retrofit2.Call;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Fatih Bulut
 * Run service of the float widget on display screen
 */
public class FloatTranslateWidgetService extends Service implements APICallBackListener<TranslateModel> {

    private WindowManager mWindowManager;
    private LayoutTranslateFloatingWidgetBinding binding;
    private View expandedView;
    private View collapsedView;
    private WindowManager.LayoutParams params;
    private LayoutInflater layoutInflater;
    private CallbackMethods<TranslateModel> callbackMethods;

    public FloatTranslateWidgetService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint({"InflateParams", "RtlHardcoded"})
    @Override
    public void onCreate() {
        super.onCreate();

        initializeComponent();
        initializeEvent();


    }

    private void initializeComponent() {
        callbackMethods = new CallbackMethods(this, PagesNames.TRANSLATE_API_BASE);

        initSampleTranslate();

        layoutInflater = LayoutInflater.from(this);
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_translate_floating_widget, null, false);

        int LAYOUT_FLAG;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
        }
        params = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                LAYOUT_FLAG,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        assert mWindowManager != null;
        mWindowManager.addView(binding.getRoot(), params);

        collapsedView = binding.collapseView;
        expandedView = binding.expandedContainer;

        binding.editDestLang.setKeyListener(null);
       // binding.editDestLang.setEnabled(false);
    }

    private void initSampleTranslate() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("text", "naber");
        parameters.put("from", "tr");
        parameters.put("to", "en");

        callbackMethods.callData(ResponseType.TRANSLATE, "", parameters);
    }

    private void initializeEvent() {
        ImageView closeButtonCollapsed = binding.closeBtn;
        closeButtonCollapsed.setOnClickListener(view -> stopSelf());

        ImageView closeButton = binding.closeButton;
        closeButton.setOnClickListener(view -> {
            collapsedView.setVisibility(View.VISIBLE);
            expandedView.setVisibility(View.GONE);
        });

        binding.rootContainer.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        int Xdiff = (int) (event.getRawX() - initialTouchX);
                        int Ydiff = (int) (event.getRawY() - initialTouchY);
                        if (Xdiff < 10 && Ydiff < 10) {
                            if (isViewCollapsed()) {
                                // collapsedView.setVisibility(View.GONE);
                                expandedView.setVisibility(View.VISIBLE);
                            }
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        mWindowManager.updateViewLayout(binding.getRoot(), params);
                        return true;
                }
                return false;
            }
        });

        binding.editDestLang.setOnClickListener(v -> {
            //TODO: copy value in edit text.
            Toast.makeText(this,"Copied text:" +binding.editDestLang.getText(),Toast.LENGTH_LONG).show();
        });
    }

    private boolean isViewCollapsed() {
        return binding == null || binding.collapseView.getVisibility() == View.VISIBLE;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (binding != null) mWindowManager.removeView(binding.getRoot());
    }

    @Override
    public void onResponse(Call<TranslateModel> call, Response<TranslateModel> response) {
        System.out.println("onResponse");
        assert response.body() != null;
        System.out.println("SONUCCC: " + response.body().getText());
        binding.setModel(response.body());
    }

    @Override
    public void onFailure(Call<TranslateModel> call, Throwable t) {
        System.out.println("PODCAST_VOA HATASI VAR " + t.getMessage());
    }


}
