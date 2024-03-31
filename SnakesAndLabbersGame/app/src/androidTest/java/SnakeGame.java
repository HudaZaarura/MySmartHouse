public class SnakeGame {
    import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

    public class SnakeView extends View {
        private static final int SPEED = 50;
        private static final int MAX_X = 25;
        private static final int MAX_Y = 20;

        private Paint snakePaint;
        private Paint applePaint;
        private int appleX;
        private int appleY;
        private int snakeX;
        private int snakeY;
        private List<Point> snakeList;
        private int snakeLength;
        private int bestScore;
        private int score;
        private boolean isSnakeDead;
        private int[][] grid;
        private Random random;
        private TextView scoreView;
        private TextView bestScoreView;

        public SnakeView(Context context, AttributeSet attrs) {
            super(context, attrs);
            snakePaint = new Paint();
            snakePaint.setColor(Color.GREEN);
            applePaint = new Paint();
            applePaint.setColor(Color.RED);
            random = new Random();
            init();
        }

        private void init() {
            grid = new int[MAX_X][MAX_Y];
            for (int i = 0; i < MAX_X; i++) {
                for (int j = 0; j < MAX_Y; j++) {
                    grid[i][j] = 0;
                }
            }
            snakeX = MAX_X / 2;
            snakeY = MAX_Y / 2;
            appleX = random.nextInt(MAX_X);
            appleY = random.nextInt(MAX_Y);
            snakeList = new ArrayList<>();
            snakeList.add(new Point(snakeX, snakeY));
            snakeLength = 1;
            bestScore = 0;
            score = 0;
            isSnakeDead = false;
        }

        public void startGame() {
            score = 0;
            bestScore = 0;
            isSnakeDead = false;
            init();
            updateScoreViews();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!isSnakeDead) {
                        snakeMove();
                        checkCollision();
                        updateScoreViews();
                        invalidate();
                        new Handler().postDelayed(this, SPEED);
                    }
                }
            }, SPEED);
        }

        private void checkCollision() {
            if (snakeX == appleX && snakeY == appleY) {
                appleX = random.nextInt(MAX_X);
                appleY = random.nextInt(MAX_Y);
                snakeLength++;
                score += 10;
                if (score > bestScore) {
                    bestScore = score;
                }
            }

            for (int i = 0; i < snakeList.size(); i++) {
                Point point = snakeList.get(i);
                if (snakeX == point.x && snakeY == point.y) {
                    isSnakeDead = true;
                    break;
                }
            }

            if (snakeX < 0 || snakeX >= MAX_X || snakeY < 0 || snakeY >= MAX_Y) {
                isSnakeDead = true;
            }
        }

        private void snakeMove() {
            Point head = new Point(snakeX, snakeY);
            snakeList.add(0, head);
            snakeX += 1;
            snakeY += 1;

            if (snakeList.size() > snakeLength) {
                snakeList.remove(snakeList.size() - 1);
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawApple(canvas);
            drawSnake(canvas);
        }

        private void drawApple(Canvas canvas) {
            canvas.drawRect(appleX * getWidth() / MAX_X, appleY * getHeight() / MAX_Y,
                    (appleX + 1) * getWidth() / MAX_X, (appleY + 1) * getHeight() / MAX_Y, applePaint);
        }

        private void drawSnake(Canvas canvas) {
            for (Point point : snakeList) {
                canvas.drawRect(point.x * getWidth() / MAX_X, point.y * getHeight() / MAX_Y,
                        (point.x + 1) * getWidth() / MAX_X, (point.y + 1) * getHeight() / MAX_Y, snakePaint);
            }
        }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_UP:
                    if (snakeY != 0 && direction != DOWN) {
                        direction = UP;
                    }
                    break;
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    if (snakeY != MAX_Y - 1 && direction != UP) {
                        direction = DOWN;
                    }
                    break;
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    if (snakeX != 0 && direction != RIGHT) {
                        direction = LEFT;
                    }
                    break;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    if (snakeX != MAX_X - 1 && direction != LEFT) {
                        direction = RIGHT;
                    }
                    break;
            }
            return true;
        }

        private void updateScoreViews() {
            scoreView.setText("Score: " + score);
            bestScoreView.setText("Best Score: " + bestScore);
        }

        public void setScoreViews(TextView scoreView, TextView bestScoreView) {
            this.scoreView = scoreView;
            this.bestScoreView = bestScoreView;
        }
    }
}
