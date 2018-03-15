package ro.razvan.startintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class EditActivity extends AppCompatActivity {

    private TextInputLayout tilName,tilSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tilName=findViewById(R.id.tilName);
        tilSurname=findViewById(R.id.tilSurname);

        tilName.getEditText().setText(getIntent().getStringExtra(getString(R.string.extra_name)));
        tilSurname.getEditText().setText(getIntent().getStringExtra(getString(R.string.extra_surname)));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tilName.setError(null);
                tilSurname.setError(null);
                final String txtName=tilName.getEditText().getText().toString().trim();
                if(txtName.length()>0){
                    final String txtSurname=tilSurname.getEditText().getText().toString().trim();
                    if(txtSurname.length()>0){
                        final Intent risposta=new Intent();
                        risposta.putExtra(getString(R.string.extra_surname),txtSurname);
                        risposta.putExtra(getString(R.string.extra_name),txtName);
                        setResult(Activity.RESULT_OK,risposta);
                        finish();
                    }else
                        tilSurname.setError(getString(R.string.fill_this_field));
                }else
                    tilName.setError(getString(R.string.fill_this_field));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home: onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
