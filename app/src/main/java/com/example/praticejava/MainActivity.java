package com.example.praticejava;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * MainActivity uses edge-to-edge layout with safe system bar padding
 * to support immersive UI while avoiding overlap with system bars.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge rendering: allows layout to extend behind system bars
        EdgeToEdge.enable(this);

        // Set the layout for this activity
        setContentView(R.layout.activity_main);

        // Apply safe padding to avoid content being hidden behind status bar or navigation bar
        // This mimics Flutter's SafeArea behavior, and allows extra padding if needed
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // Get the space occupied by system bars (status bar, navigation bar, gesture area)
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            int extraTop = 40; // Optional: additional space above status bar (for visual breathing room)

            // Apply padding so views do not go under system bars
            v.setPadding(
                    systemBars.left,                  // left padding for gesture area or cutouts
                    systemBars.top + extraTop,        // top padding + custom top space
                    systemBars.right,                 // right padding for gesture area
                    systemBars.bottom                 // bottom padding (e.g., for nav bar)
            );

            return insets; // Always return insets to let system continue handling them
        });

        Button gotoButton = findViewById(R.id.btn_go_to_classic);
        gotoButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        });
    }
}
