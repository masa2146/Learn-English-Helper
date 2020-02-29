package com.blt.helperenglish.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.*;
import android.widget.ImageView;
import com.blt.helperenglish.R;

/**
 * @author Fatih Bulut
 * Run service of the float widget on display screen
 */
public class FloatTranslateWidgetService extends Service {

    private WindowManager mWindowManager;
    private View mFloatingWidget;

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

        mFloatingWidget = LayoutInflater.from(this).inflate(R.layout.layout_translate_floating_widget, null);
        int LAYOUT_FLAG;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
        }
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
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
        mWindowManager.addView(mFloatingWidget, params);

        final View collapsedView = mFloatingWidget.findViewById(R.id.collapse_view);
        final View expandedView = mFloatingWidget.findViewById(R.id.expanded_container);


        ImageView closeButtonCollapsed = mFloatingWidget.findViewById(R.id.close_btn);
        closeButtonCollapsed.setOnClickListener(view -> stopSelf());

        ImageView closeButton = mFloatingWidget.findViewById(R.id.close_button);
        closeButton.setOnClickListener(view -> {
            collapsedView.setVisibility(View.VISIBLE);
            expandedView.setVisibility(View.GONE);
        });

        mFloatingWidget.findViewById(R.id.root_container).setOnTouchListener(new View.OnTouchListener() {
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
                        mWindowManager.updateViewLayout(mFloatingWidget, params);
                        return true;
                }
                return false;
            }
        });
    }

    private boolean isViewCollapsed() {
        return mFloatingWidget == null || mFloatingWidget.findViewById(R.id.collapse_view).getVisibility() == View.VISIBLE;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFloatingWidget != null) mWindowManager.removeView(mFloatingWidget);
    }
}
