package misterdneh.ca.pixelprisoner;

import android.view.SurfaceHolder;
import android.graphics.*;

public class GameLoop extends Thread {

    // Desired frames per second
    public static final double MAX_FPS = 60.0;
    // Maximum time frame per update
    private static final double MAX_FRAME_TIME = 1000.0 / MAX_FPS;

    private final SurfaceHolder surfaceHolder;
    private GameView gameView;  // The view where the game is drawn
    private boolean isRunning = false;
    private long averageFPS = 0;

    public GameLoop (SurfaceHolder surfaceHolder, GameView gameView) {
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = (long) MAX_FRAME_TIME;

        while (isRunning) {
            startTime = System.nanoTime();

            // Lock the canvas for drawing
            Canvas canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    // Update game logic
                    gameView.update();
                    // Draw on the canvas
                    if (canvas != null) {
                        gameView.draw(canvas);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    // Unlock and post the canvas content
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            // Calculate time taken and sleep for the remainder of the frame
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                if (waitTime > 0) {
                    sleep(waitTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            // If a second has passed, calculate FPS
            if (frameCount == MAX_FPS) {
                averageFPS = (long) (1000.0 / ((totalTime / frameCount) / 1000000.0));
                frameCount = 0;
                totalTime = 0;
                System.out.println("Average FPS: " + averageFPS);
            }
        }
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    public long getAverageFPS(){
        return averageFPS;
    }
}
