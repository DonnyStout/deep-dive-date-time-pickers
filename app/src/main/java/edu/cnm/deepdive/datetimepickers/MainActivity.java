package edu.cnm.deepdive.datetimepickers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private Calendar calendar;
    private EditText dateControl;
    private EditText timeControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateControl = findViewById(R.id.date_control);
        timeControl = findViewById(R.id.time_control);
        calendar = Calendar.getInstance();
        setupListeners();
        updateDisplay();
    }


    private void updateDisplay() {
        dateControl.setText(DateFormat.getDateFormat(this).format(calendar.getTime()));
        timeControl.setText(DateFormat.getTimeFormat(this).format(calendar.getTime()));
    }

    private void showDatePicker() {
        DateTimePickerFragment fragment = new DateTimePickerFragment();
        fragment.setMode(DateTimePickerFragment.Mode.DATE);
        fragment.setPassthrough(false);
        fragment.setCalendar(calendar);
        fragment.setOnChangeListener(new DateTimePickerFragment.OnChangeListener() {
            @Override
            public void onChange(Calendar calendar) {
                // TODO Do something more interesting
                MainActivity.this.calendar = calendar;
                updateDisplay();
            }
        });
        fragment.show(getSupportFragmentManager(), fragment.getClass().getName());
    }

    private void showTimePicker() {
        DateTimePickerFragment fragment = new DateTimePickerFragment();
        fragment.setMode(DateTimePickerFragment.Mode.TIME);
        fragment.setPassthrough(true);
        fragment.setCalendar(calendar);
        fragment.setOnChangeListener(new DateTimePickerFragment.OnChangeListener() {
            @Override
            public void onChange(Calendar calendar) {
                // TODO Do something more interesting
                updateDisplay();
            }
        });
        fragment.show(getSupportFragmentManager(), fragment.getClass().getName());

    }

    private void setupListeners() {
        dateControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        timeControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });
    }
}
