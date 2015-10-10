package com.example.alexandervandesteene.fasterslowerapp2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by alexandervandesteene on 10/10/15.
 */
public class MyDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder theDialog = new AlertDialog.Builder(getActivity());

        theDialog.setTitle("FeedBack");

        theDialog.setMessage("Are you happy with the speed of the Course?");

        theDialog.setPositiveButton("I'm happy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "glad to here that", Toast.LENGTH_SHORT).show();
            }
        });

        theDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "give me some more feedback", Toast.LENGTH_SHORT).show();
            }
        });
        return theDialog.create();
    }
}
