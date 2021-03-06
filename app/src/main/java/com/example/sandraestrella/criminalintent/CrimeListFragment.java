package com.example.sandraestrella.criminalintent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by sandra estrella on 30/11/2017.
 */

public class CrimeListFragment extends Fragment{
        private RecyclerView mCrimeRecyclerView;
        private CrimeAdapter mAdapter;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
            mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
            mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            updateUI();
            return view;
        }

        private void updateUI(){
            CrimeLab crimeLab = CrimeLab.get(getActivity());
            List<Crime> crimes = crimeLab.getCrimes();

            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }


        private class CrimeHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener{
            private Crime mCrime;
            private TextView mTitleTextView;
            private TextView mDateTextView;
            private CheckBox mSolvedCheckBox;


            public CrimeHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);

                mTitleTextView = (TextView) itemView
                        .findViewById(R.id.list_item_crime_title_text_view);
                mDateTextView = (TextView) itemView
                        .findViewById(R.id.list_item_crime_date_text_view);
                mSolvedCheckBox = (CheckBox) itemView
                        .findViewById(R.id.list_item_crime_solved_chech_box);
            }

            public void bindCrime(Crime crime) {
                mCrime = crime;
                mTitleTextView.setText(mCrime.getmTitle());
                mDateTextView.setText(mCrime.getmDate().toString());
                mSolvedCheckBox.setChecked(mCrime.ismSolved());
            }

            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),
                        "Se hizo clic sobre " + mCrime.getmTitle(),
                        Toast.LENGTH_SHORT).show();
            }
        }

            private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
                private List<Crime> mCrimes;

                public CrimeAdapter(List<Crime> crimes){
                    mCrimes = crimes;
                }

                @Override
                public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                    View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);

                    return new CrimeHolder(view);
                }

                @Override
                public void onBindViewHolder(CrimeHolder holder, int position) {
                    Crime crime = mCrimes.get(position);
                    holder.mTitleTextView.setText(crime.getmTitle());
                }

                @Override
                public int getItemCount() {
                    return mCrimes.size();
                }
            }

        }