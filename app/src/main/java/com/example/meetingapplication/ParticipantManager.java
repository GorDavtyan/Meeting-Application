package com.example.meetingapplication;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ParticipantManager {
    private Context context;
    private GridLayout participantContainer;
    private int count = 0;
    private static final int MAX_PARTICIPANT = 10;
    private static final int MAX_PARTICIPANTS_PER_ROW = 3;


    public ParticipantManager(Context context, GridLayout participantContainer) {
        this.context = context;
        this.participantContainer = (GridLayout) ((Activity) context).findViewById(R.id.participantContainer);
    }


    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public View inflateParticipantView() {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.participant_view, null);
    }


    public void addParticipant() {
        if (count < MAX_PARTICIPANT) {
            addParticipantView(count);
            count++;
        } else {
            showToast("Participant limit reached. Cannot add more participants.");
        }
    }

    public void removeParticipant() {
        if (participantContainer.getChildCount() > 0) {
            View participantView = inflateParticipantView();
            final View lastParticipant = participantContainer.getChildAt(participantContainer.getChildCount() - 1);

            AnimationUtils.scaleDownAndFadeOutView(lastParticipant, 500, new Runnable() {
                @Override
                public void run() {
                    participantContainer.removeView(lastParticipant);
                    if (count > 0) {
                        count--;
                    }
                    // Call rearrangeParticipants here if needed
                }
            });
        } else {
            showToast("Participant limit reached. Cannot add more participants.");
        }
    }


    private void addParticipantView(int serialNumber) {
        updateGridLayout();
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.width = 0; // width is 0 for stretch
        layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT; // wrap content in height for horizontal layout
        layoutParams.setGravity(Gravity.FILL);
        layoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        layoutParams.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);

        View participantView = LayoutInflater.from(context).inflate(R.layout.participant_view, participantContainer, false);
        TextView participantText = participantView.findViewById(R.id.participantText);
        participantText.setText(String.valueOf(serialNumber + 1));

        participantView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addParticipant();
            }
        });

        AnimationUtils.scaleUpView(participantView, 500);

        participantContainer.addView(participantView, layoutParams);
    }


    private void updateGridLayout() {
        int rows = (int) Math.ceil((double) count / MAX_PARTICIPANTS_PER_ROW);
        participantContainer.setRowCount(rows > 0 ? rows : 1);
        participantContainer.setColumnCount(MAX_PARTICIPANTS_PER_ROW);
    }


}
