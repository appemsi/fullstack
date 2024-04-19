package ma.emsi.contact_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;

public class AddEditContact extends AppCompatActivity {

    DbHelper dbHelper;
    Uri uri;  // uri vs url
    ImageView photo;
    EditText name,phone,email,note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contact);
        photo=findViewById(R.id.profileIv);
        name=findViewById(R.id.nameEt);
        phone=findViewById(R.id.phoneEt);
        email=findViewById(R.id.emailEt);
        note=findViewById(R.id.noteEt);
        dbHelper=new DbHelper(this,1);
    }

    public void saveContact(View v)
    {
        String sname=name.getText().toString();
        String sphone=phone.getText().toString();
        String semail=email.getText().toString();
        String snote=note.getText().toString();
        String path=uri.toString();
        long id=dbHelper.insert(sname,sphone,semail,snote,path,"2024","2024");
        Toast.makeText(this, "Data saved with id= "+id, Toast.LENGTH_SHORT).show();
        finish();
    }

    public void addImage(View v)
    {
        ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ImagePicker.REQUEST_CODE && resultCode == RESULT_OK && data != null)
        {
            uri=data.getData();
            photo.setImageURI(uri);
        }
    }
}