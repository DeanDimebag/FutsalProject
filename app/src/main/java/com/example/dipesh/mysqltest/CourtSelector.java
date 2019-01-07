package com.example.dipesh.mysqltest;

public class CourtSelector extends BookingFragment
{
    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        selectTime = (EditText) getActivity().findViewById(R.id.sDate);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        month = month + 1;
        selectTime.setText(day + "/" + month + "/" + year);
        final int finalMonth = month;
        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month = month + 1;
                                selectTime.setText(day + "/" + month + "/" + year);
                            }
                        }, year, finalMonth, day);
                datePickerDialog.show();
            }
        });
    }

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
        }*/

}
