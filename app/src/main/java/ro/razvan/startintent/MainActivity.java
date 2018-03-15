package ro.razvan.startintent;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView txvName,txvSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvName=findViewById(R.id.txvName);
        txvSurname=findViewById(R.id.txvSurname);

    }


    public final void firstWay(final View view) {
        final Intent intent = new Intent(this, EmptyActivity.class);
        startActivity(intent);
    }

    public final void secondWay(final View view) {
        final Intent intent = new Intent("AZIONE_SPECIFICATA");
        startActivity(intent);
    }

    public final void editDetails(final View view) {
        final Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(getString(R.string.extra_name),txvName.getText());
        intent.putExtra(getString(R.string.extra_surname),txvSurname.getText());

        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data!=null) {

            switch(requestCode){
                case 0: txvName.setText(data.getStringExtra(getString(R.string.extra_name)));
                    txvSurname.setText(data.getStringExtra(getString(R.string.extra_surname)));
                    Toast.makeText(this,getString(R.string.done),Toast.LENGTH_SHORT).show();
                    break;
            }

        } else {
            Toast.makeText(this, getString(R.string.operation_canceled), Toast.LENGTH_SHORT).show();
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
