package com.example.minp;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.DatePicker;
import android.app.TimePickerDialog;
import android.graphics.Color;

import org.w3c.dom.Text;

import java.util.Calendar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class dashboard extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton, time, time2, search;

    public TextView tv;
    EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        initDatePicker();
        dateButton = findViewById(R.id.datepickerbutton);
        // dateButton.setText(getTodaysDate());
        search = findViewById(R.id.search);
        time = findViewById(R.id.time);
        time2 = findViewById(R.id.time2);
        RadioGroup radioGroup;
        RadioGroup radioGroup1;


        String st;
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup2);
        details ob = new details();
       // tv=findViewById(R.id.textview);
//        st=getIntent().getExtras().getString("Welcome value");
      //  tv.setText(st);
        radioGroup.clearCheck();
        radioGroup1.clearCheck();
        radioGroup1.setOnCheckedChangeListener(
                new RadioGroup
                        .OnCheckedChangeListener() {
                    @Override

                    // The flow will come here when
                    // any of the radio buttons in the radioGroup
                    // has been clicked

                    // Check which radio button has been clicked

                    public void onCheckedChanged(RadioGroup group,
                                                 int checkedId) {

                        // Get the selected Radio Button
                        RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                        radioButton.getText();
                        RadioButton
                                radioButton1
                                = (RadioButton) group
                                .findViewById(checkedId);
                        radioButton.getText();
                        String a, b;
                        if (radioButton.getText().equals("upto 100")) {
                            ob.dashdetails("2","1");
                        } else if (radioButton.getText().equals("upto 200")) {
                            ob.dashdetails("1","2");
                        } else if (radioButton.getText().equals("upto 300")) {
                            ob.dashdetails("3","3");
                        }


                    }
                });

        radioGroup.setOnCheckedChangeListener(
                new RadioGroup
                        .OnCheckedChangeListener() {
                    @Override

                    // The flow will come here when
                    // any of the radio buttons in the radioGroup
                    // has been clicked

                    // Check which radio button has been clicked

                    public void onCheckedChanged(RadioGroup group,
                                                 int checkedId) {

                        // Get the selected Radio Button
                        RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                        radioButton.getText();
                        String c;
                        if (radioButton.getText().equals("A")) {
                            ob.dashdetails("1");
                        } else if (radioButton.getText().equals("B")) {
                            ob.dashdetails("2");
                        } else if (radioButton.getText().equals("C")) {
                            ob.dashdetails("3");
                        }


                    }
                });


        // perform click event listener on edit text

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();

                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(dashboard.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String am_pm;
                        if (selectedHour >= 0 && selectedHour < 12) {
                            am_pm = "am";
                        } else {
                            am_pm = "pm";
                        }
                        time.setText(selectedHour + ":" + selectedMinute + " " + am_pm);

                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("from time");
                mTimePicker.show();

            }
        });
        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(dashboard.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String am_pm;
                        if (selectedHour >= 0 && selectedHour < 12) {
                            am_pm = "am";
                        } else {
                            am_pm = "pm";
                        }
                        time2.setText(selectedHour + ":" + selectedMinute + " " + am_pm);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("to time");
                mTimePicker.show();

            }
        });

    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);

        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }


    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }

        };
        Calendar cal =Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEPT";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";


        return "JAN";
    }


    public void openDatePicker(View view) {
        datePickerDialog.show();
    }


    public class details {

        void dashdetails(String c) {
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (c.equals("1")) {
                        Intent intent = new Intent(dashboard.this, bookingdetails.class);
                        startActivity(intent);
                    }
                    if (c.equals("2")) {
                        Intent intent = new Intent(dashboard.this, bookingdetails2.class);
                        startActivity(intent);
                    }
                    if (c.equals("3")) {
                        Intent intent = new Intent(dashboard.this, bookingdetails3.class);
                        startActivity(intent);
                    }
                }

            });
        }


        void dashdetails(String a, String b) {
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (a.equals("2") && b.equals("1")) {
                        Intent intent = new Intent(dashboard.this, bookingdetails.class);
                        startActivity(intent);
                    }
                    if (a.equals("1") && b.equals("2")) {
                        Intent intent = new Intent(dashboard.this, bookingdetails2.class);
                        startActivity(intent);
                    }
                    if (a.equals("3") &&b.equals("3")) {
                        Intent intent = new Intent(dashboard.this, bookingdetails3.class);
                        startActivity(intent);

                    }


                }

            });
        }
    }
}

