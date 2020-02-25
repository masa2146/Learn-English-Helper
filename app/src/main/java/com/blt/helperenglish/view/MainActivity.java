package com.blt.helperenglish.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blt.helperenglish.R;
import com.blt.helperenglish.adapter.PageMainAdapter;
import com.blt.helperenglish.constant.PagesNames;
import com.blt.helperenglish.databinding.ActivityMainBinding;
import com.blt.helperenglish.service.FloatTranslateWidgetService;


public class MainActivity extends AppCompatActivity {

    private static final int APP_PERMISSION_REQUEST = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        checkWidgetSystemPermission();

        RecyclerView recyclerView = binding.rv;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        PageMainAdapter mainPageAdapter = new PageMainAdapter();


        mainPageAdapter.setMainPageData(PagesNames.mainPageDataArray);
        mainPageAdapter.setContext(this);

        recyclerView.setAdapter(mainPageAdapter);
    }

    private void checkWidgetSystemPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, APP_PERMISSION_REQUEST);
        } else {
            initializeView();
        }
    }

    private void initializeView() {
        startService(new Intent(MainActivity.this, FloatTranslateWidgetService.class));
        //finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == APP_PERMISSION_REQUEST && resultCode == RESULT_OK) {
            initializeView();
        } else {
            Toast.makeText(this, "Draw over other app permission not enable.", Toast.LENGTH_SHORT).show();
        }
    }
}
