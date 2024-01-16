package com.damilola.core_android.utils.ui_providers;

import android.app.AlertDialog;
import android.content.Context;
import com.damilola.core_android.R;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class AlertDialogManager{
    /**
     * Function to display simple Alert Dialog
     *
     * @param context - application context
     * @param title   - alert dialog title
     * @param message - alert message
     * @param status  - success/failure (used to set icon)
     *                - pass null if you don't want icon
     *
     */


    public void showAlertDialog(Context context, String title, String message,
                                Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);


        if (status != null)
            // Setting alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.ic_success : R.drawable.ic_block);

        alertDialog.setCancelable(true);

        alertDialog.setCanceledOnTouchOutside(true);
        // Setting OK Button
        alertDialog.setButton("OK", (dialog, which) -> alertDialog.dismiss());

        // Showing Alert Message
        alertDialog.show();
    }

    @Inject
    public AlertDialogManager() {

    }
}
