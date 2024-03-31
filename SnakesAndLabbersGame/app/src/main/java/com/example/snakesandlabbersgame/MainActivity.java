import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView scoreView;
    private TextView bestScoreView;
    private Button btnStart;
    private SnakeView snakeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreView = findViewById(R.id.score);
        bestScoreView = findViewById(R.id.best_score);
        btnStart = findViewById(R.id.btn_start);
        snakeView = findViewById(R.id.snake_view);

        snakeView.setScoreView(scoreView);
        snakeView.setBestScoreView(bestScoreView);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snakeView.startGame();
            }
        });
    }
}