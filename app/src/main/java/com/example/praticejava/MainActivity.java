package com.example.praticejava;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {


    Button btnPick;

    ActivityResultLauncher<Intent> imagePickerLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Uri imageUri = result.getData().getData();
                            saveImageToPublicFolder(imageUri);
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnPick = findViewById(R.id.btnPick);

        btnPick.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            imagePickerLauncher.launch(intent);
        });

    }

    private void saveImageToPublicFolder(Uri sourceUri) {
        try {
            ContentResolver resolver = getContentResolver();

            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DISPLAY_NAME,
                    "IMG_" + System.currentTimeMillis() + ".jpg");
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

            // 🔥 This saves it in PUBLIC Pictures folder
            values.put(MediaStore.Images.Media.RELATIVE_PATH,
                    "Pictures/MyPublicImages");

            Uri destUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            InputStream inputStream = resolver.openInputStream(sourceUri);
            OutputStream outputStream = resolver.openOutputStream(destUri);

            byte[] buffer = new byte[4096];
            int read;

            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }

            inputStream.close();
            outputStream.close();

            Toast.makeText(this,
                    "Saved to Pictures/MyPublicImages",
                    Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(this, "Error saving image", Toast.LENGTH_SHORT).show();
        }
    }
}