package com.example.dipesh.mysqltest;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;


public class BookingFragment extends Fragment implements TimePickerDialog.OnTimeSetListener
{
    /*FloatingActionButton floatingActionButton;*/

    Spinner spinner;
    EditText selectDate;
    EditText chooseTime;
    EditText chooseTime2;
    Button insertButton;
    Button showButton;
    TimePickerDialog timePickerDialog;
    int currentHour;
    int currentMinute;
    String amPm;
    RequestQueue requestQueue;
    String insertUrl = "http://192.168.1.5:8080/tutorial/books.php";
    String showUrl = "http://192.168.1.5:8080/tutorial/fetchs.php";

    public ListView list;
    public ArrayList<Bookings> bookings = new ArrayList<Bookings>();
    public ListAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

       /* floatingActionButton = (FloatingActionButton)getActivity().findViewById(R.id.bFloat);
        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openDialog();
                startActivity(new Intent(getActivity().getApplicationContext(),Dialog.class));
            }
        });*/



       // Select Date Coding
        selectDate = (EditText) getActivity().findViewById(R.id.sDate);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        month = month + 1;
        selectDate.setText(day + "/" + month + "/" + year);
        final int finalMonth = month;
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month = month + 1;
                                selectDate.setText(day + "/" + month + "/" + year);
                            }
                        }, year, finalMonth, day);
                datePickerDialog.show();
            }
        });



        // Select Time Coding
        chooseTime = (EditText)getActivity().findViewById(R.id.sTime);
        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Calendar mcalendar = Calendar.getInstance();
                currentHour = mcalendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = mcalendar.get(Calendar.MINUTE);

                 TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });

        // Coding For Fetching Data From DataBase
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        list = (ListView)getActivity().findViewById(R.id.listView);
        adapter = new ListAdapter(this);
        list.setAdapter(adapter);

        showButton= (Button)getActivity().findViewById(R.id.showBtn);
        showButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.e("Button", "Clicked");
                Toast.makeText(getActivity().getApplicationContext(), "Button Clicked", Toast.LENGTH_LONG).show();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, showUrl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            JSONArray court = response.getJSONArray("court");
                            for (int i = 0;i<court.length();i++)
                            {

                                Bookings add=new Bookings();

                                JSONObject student = court.getJSONObject(i);

                                String Username = student.getString("court");
                                String StartTime = student.getString("startTime");
                                String EndTime = student.getString("endTime");

                                add.username = Username;
                                add.startTime = StartTime;
                                add.endTime = EndTime;

                                bookings.add(add);
                            }
                            adapter.notifyDataSetChanged();
                            //  ageText.append("===\n");
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {

                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });

        // Coding For Inserting Data In DataBase
        /*insertButton = (Button)getActivity().findViewById(R.id.insertBtn);
        insertButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError
                    {
                        Map<String,String> parameters = new HashMap<String, String>();
                        parameters.put("court",spinner.toString());
                        parameters.put("date",selectDate.getText().toString());
                        parameters.put("startTime",chooseTime.getText().toString());
                        parameters.put("endTime",chooseTime2.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);

            }
        });*/
    };

    //Select Court Coding
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking, container, false);


        spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.list));
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(LTRadapter);
        return view;
    }

    //Remaining Coding of Time Selector
    @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {

        }

    public BookingFragment()
    {
        // Required empty public constructor
    }


   /* public void openDialog()
    {

    }*/
}
