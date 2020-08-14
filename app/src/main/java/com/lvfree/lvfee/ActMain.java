package com.lvfree.lvfee;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.lvfree.lvfee.data.DataPreferenceManager;
import com.lvfree.lvfee.view.CustomTextView;
import com.lvfree.lvfee.view.WaveLoadingView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class ActMain extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private long stTime;
    private CustomTextView tvY, tvM, tvW, tvD, tvH, tvMu, tvS;
    private WaveLoadingView waveLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        waveLoadingView = findViewById(R.id.waveLoadingView);
        waveLoadingView.setAnimDuration(2000);
        waveLoadingView.setOnClickListener(this);
        tvY = findViewById(R.id.tvY);
        tvM = findViewById(R.id.tvM);
        tvW = findViewById(R.id.tvW);
        tvD = findViewById(R.id.tvD);

        tvH = findViewById(R.id.tvH);
        tvMu = findViewById(R.id.tvMu);
        tvS = findViewById(R.id.tvS);
        waveLoadingView.setBottomTitle("Ngày");
        waveLoadingView.setTopTitle("Đang yêu");

        waveLoadingView.setTopTitleSize(15);
        waveLoadingView.setCenterTitleSize(36);
        waveLoadingView.setBottomTitleSize(15);

        stTime = DataPreferenceManager.getInstance(this).getDataLongFromHolder(DataPreferenceManager.PREF_TIME);
        if (stTime == -1) {
            stTime = System.currentTimeMillis();
            DataPreferenceManager.getInstance(this).writeLongData(DataPreferenceManager.PREF_TIME, stTime);
        }

        startCounting();

    }

    private Handler handler = new Handler();

    private void startCounting() {
        handler.post(run);
    }

    private Runnable run = new Runnable() {
        @Override
        public void run() {
            long ccTime = System.currentTimeMillis();
            long time = ccTime - stTime;
            MillisToYearMonthDayHrMinSec(time);
            handler.postDelayed(this, 1000);
        }
    };


    private long day;

    public void MillisToYearMonthDayHrMinSec(long milliseconds) {
        long dy = TimeUnit.MILLISECONDS.toDays(milliseconds);
        waveLoadingView.setCenterTitle(dy+"");
        day = dy;
        final long yr = dy / 365;
        dy %= 365;
        final long mn = dy / 30;
        dy %= 30;
        final long wk = dy / 7;
        dy %= 7;
        final long hr = TimeUnit.MILLISECONDS.toHours(milliseconds)
                - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));
        final long min = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));
//        final long ms = TimeUnit.MILLISECONDS.toMillis(milliseconds)
//                - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(milliseconds));

        tvY.setText(yr + "");
        tvM.setText(mn + "");
        tvW.setText(wk + "");

        tvD.setText(day + "");
        tvH.setText(hr + "");
        tvMu.setText(min + "");
        tvS.setText(sec + "");
//           return String.format("%d Years %d Months %d Weeks %d Days %d Hours %d Minutes %d Seconds %d Milliseconds", yr,
//                   mn, wk, dy, hr, min, sec, ms);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(run);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.waveLoadingView:
                showDatePicker();
                break;
        }
    }

    private void showDatePicker() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(ActMain.this,
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
        );
        dpd.show(getSupportFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

    }
}
