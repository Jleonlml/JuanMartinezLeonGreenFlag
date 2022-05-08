package com.example.juanmartinezleongreenflag;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class SignupActivity extends AppCompatActivity {

    //region variable declaration
    private boolean [] errors = {true, true, true};
    private Utilities utilitiesObj = new Utilities();
    private View loError;
    private View btnNext;
    private View loEmail;
    private View loPass;
    //endregion

    //region Main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null)
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        loError = findViewById(R.id.loError);
        loEmail = findViewById(R.id.loEmail);
        btnNext = findViewById(R.id.btn_Next);
        loPass = findViewById(R.id.loPass);

        loError.setVisibility(View.GONE);

        setupBackBtn();
        setupNextBtn();
        setupInputsValidationsListeners();
    }
    //endregion

    //region main program Logic
    private void setupBackBtn() {
        View btnBack = findViewById(R.id.iv_back);
        btnBack.setOnClickListener(View -> {
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void setupNextBtn() {
        btnNext.setOnClickListener(View -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Success!");
            builder.setMessage("Data stored correctly");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                }
            });
            /*builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            })*/
            AlertDialog dialog = builder.create();
            dialog.show();

        });
    }

    private void setupInputsValidationsListeners() {
        validateEmail();
        validatePasswords();
    }

    private void validateEmail() {
        TextInputEditText textInputViewEmail = findViewById(R.id.tivEmail);
        ImageView ivEmailCheck = findViewById(R.id.ivEmailCheck);
        textInputViewEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                errors[0] = utilitiesObj.validateStringWithRegex(textInputViewEmail.getText().toString(), utilitiesObj.emailRegEx);

                if (!errors[0]) {
                    ivEmailCheck.setImageResource(R.drawable.ic_baseline_check_24);
                    loEmail.setBackgroundResource(0);
                    loEmail.setBackgroundColor(getResources().getColor(android.R.color.white));
                } else {
                    ivEmailCheck.setImageResource(0);
                    loEmail.setBackgroundResource(R.drawable.border_bwhite_red);
                }

                btnNext.setEnabled(validateErrors());
                setEnabledDisabledBtnBackground();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });
    }

    private void validatePasswords() {
        TextInputEditText textInputViewPass = findViewById(R.id.tivPass);
        ImageView ivPassCheck = findViewById(R.id.ivPassCheck);
        TextInputEditText textInputRepeatPass = findViewById(R.id.itvRepeatPass);
        textInputViewPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                errors[1] = utilitiesObj.validateStringWithRegex(textInputViewPass.getText().toString(), utilitiesObj.passwordRegEx);
                errors[2] = utilitiesObj.compareStrings(textInputRepeatPass.getText().toString(), textInputViewPass.getText().toString());


                if (!errors[1]) {
                    ivPassCheck.setImageResource(R.drawable.ic_baseline_check_24);
                    loPass.setBackgroundResource(0);
                    loPass.setBackgroundColor(getResources().getColor(android.R.color.white));
                }
                else {
                    ivPassCheck.setImageResource(0);
                    loPass.setBackgroundResource(R.drawable.border_bwhite_red);
                }

                btnNext.setEnabled(validateErrors());
                setEnabledDisabledBtnBackground();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textInputRepeatPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                errors[2] = utilitiesObj.compareStrings(textInputRepeatPass.getText().toString(), textInputViewPass.getText().toString());


                if (!errors[2])
                    loError.setVisibility(View.GONE);
                else
                    loError.setVisibility(View.VISIBLE);

                btnNext.setEnabled(validateErrors());
                setEnabledDisabledBtnBackground();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setEnabledDisabledBtnBackground()
    {
        if (btnNext.isEnabled())
            btnNext.setBackgroundResource(R.drawable.btn_background);
        else
            btnNext.setBackgroundResource(R.drawable.disabledbg);
    }

    //endregion

    //region inputs errors validation
    private boolean validateErrors() {
        int errorsCounter = 0;
        for (Boolean error: errors)
        {
            if (error)
                errorsCounter ++;
        }
        if (errorsCounter == 0)
                return true;
            else
                return false;
    }
    //endregion
}