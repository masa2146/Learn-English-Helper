package com.blt.helperenglish.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.databinding.DataBindingUtil;
import com.blt.helperenglish.R;
import com.blt.helperenglish.common.retro.CallbackMethods;
import com.blt.helperenglish.common.retro.impl.APICallBackListener;
import com.blt.helperenglish.constant.Languages;
import com.blt.helperenglish.constant.PagesNames;
import com.blt.helperenglish.constant.ResponseType;
import com.blt.helperenglish.databinding.LayoutTranslateFloatingWidgetBinding;
import com.blt.helperenglish.model.translate.Language;
import com.blt.helperenglish.model.translate.TranslateModel;
import retrofit2.Call;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Fatih Bulut
 * Run service of the float widget on display screen
 */
@SuppressWarnings("ALL")
public class FloatTranslateWidgetService extends Service implements APICallBackListener<TranslateModel> {

    private WindowManager mWindowManager;
    private LayoutTranslateFloatingWidgetBinding binding;

    private WindowManager.LayoutParams params;
    private CallbackMethods callbackMethods;
    private int width = ViewGroup.LayoutParams.WRAP_CONTENT;
    private int height = WindowManager.LayoutParams.WRAP_CONTENT;

    private String sourceLang = "au";
    private String destLang = Locale.getDefault().getLanguage();
    private String translateText = "";


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

        WindowManager window = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        assert window != null;
        Display display = window.getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        System.out.println("DEFAULT LANG: " + sourceLang);

        initializeComponent();
        initializeListener();
    }

    private void initializeComponent() {
        callbackMethods = new CallbackMethods(this, PagesNames.TRANSLATE_API_BASE);

        // initSampleTranslate();

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_translate_floating_widget, null, false);

        binding.rootContainer.getLayoutParams().width = width;

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

        binding.editDestLang.setKeyListener(null);
        // binding.editDestLang.setEnabled(false);

        ArrayAdapter<Language> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Languages.languagesList);
        System.out.println("LANGUAGE lİST: " + Languages.languagesList.size());
        binding.sourceSpinner.setAdapter(adapter);
        binding.destSpinner.setAdapter(adapter);
        binding.sourceSpinner.setSelection(1);

        Language tempLang = Languages.languagesList.stream().filter(x -> x.getLangCode().equals(destLang)).findAny()
                .orElse(null);

        binding.destSpinner.setSelection(Languages.languagesList.indexOf(tempLang));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initializeListener() {
        binding.closeBtn.setOnClickListener(view -> stopSelf());

        binding.closeButton.setOnClickListener(view -> {
            binding.collapseView.setVisibility(View.VISIBLE);
            binding.expandedContainer.setVisibility(View.GONE);
            binding.rootContainer.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
            binding.expandedContainer.getLayoutParams().height = WindowManager.LayoutParams.WRAP_CONTENT;
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
                                binding.expandedContainer.setVisibility(View.VISIBLE);
                                binding.rootContainer.getLayoutParams().width = width;
                                binding.expandedContainer.getLayoutParams().height = height / 4;
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
            Toast.makeText(this, "Copied text:" + binding.editDestLang.getText(), Toast.LENGTH_LONG).show();
        });

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        assert clipboardManager != null;
        clipboardManager.addPrimaryClipChangedListener(() -> {
            binding.editSourceLang.setText(clipboardManager.getText());
        });

        binding.editSourceLang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                translateText = s.toString();
                if (sourceLang.equals("au"))
                    translate(destLang, translateText);
                else translate(sourceLang, destLang, translateText);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        binding.sourceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Language language = (Language) parent.getSelectedItem();
                sourceLang = language.getLangCode();
                translate(sourceLang, destLang, translateText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.destSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Language language = (Language) parent.getSelectedItem();
                destLang = language.getLangCode();

                if (sourceLang.equals("au"))
                    translate(destLang, translateText);
                else translate(sourceLang, destLang, translateText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean isViewCollapsed() {
        return binding == null || binding.collapseView.getVisibility() == View.VISIBLE;
    }

    private void translate(String from, String to, String text) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("text", text);
        parameters.put("from", from);
        parameters.put("to", to);

        callbackMethods.callData(ResponseType.TRANSLATE, "", parameters);
    }

    private void translate(String to, String text) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("text", text);
        parameters.put("to", to);

        callbackMethods.callData(ResponseType.TRANSLATE, "", parameters);
    }

    private void initSampleTranslate() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("text", "naber");
        parameters.put("from", "tr");
        parameters.put("to", "en");

        callbackMethods.callData(ResponseType.TRANSLATE, "", parameters);
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
        System.out.println("SONUCCC: " + response.body().toString());
        binding.setModel(response.body());
    }

    @Override
    public void onFailure(Call<TranslateModel> call, Throwable t) {
        System.out.println("PODCAST_VOA HATASI VAR " + t.getMessage());
    }


}
