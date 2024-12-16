package misterdneh.ca.pixelprisoner.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import misterdneh.ca.pixelprisoner.GameView;
import misterdneh.ca.pixelprisoner.R;

public class Dpad {
    GameView gameView;
    public int dpadRightX;
    public int dpadRightY;
    public int dpadRightWidth;
    public int dpadRightHeight;
    public int dpadLeftX;
    public int dpadLeftY;
    public int dpadLeftWidth;
    public int dpadLeftHeight;
    public int dpadUpX;
    public int dpadUpY;
    public int dpadUpWidth;
    public int dpadUpHeight;
    public int dpadDownX;
    public int dpadDownY;
    public int dpadDownWidth;
    public int dpadDownHeight;
    public int defaultDpadX;
    public int defaultDpadY;
    public Bitmap dpadIdle;
    public Bitmap dpadRight;
    public Bitmap dpadLeft;
    public Bitmap dpadUp;
    public Bitmap dpadDown;
    public int dpadButtonWidth;
    public int dpadButtonHeight;
    public Bitmap dpadDefault;
    public Paint dpadTest;
    public int dpadAbuttonX;
    public int dpadAbuttonY;
    public int dpadAbuttonWidth;
    public int dpadAbuttonHeight;

    public Dpad(GameView gameView){
        this.gameView = gameView;
        initializeDpad();
    }

    private void initializeDpad() {
        //If device is in landscape use height if in portrait use width
        dpadButtonWidth = gameView.getDisplayHeight()/6;
        dpadButtonHeight = gameView.getDisplayHeight()/6;
        dpadTest = new Paint();
        dpadTest.setColor(Color.BLUE);
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        //Sets up the default images for the buttons
        dpadIdle = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(gameView.getResources(), R.drawable.dpad_idle),dpadButtonWidth*3,dpadButtonWidth*3,false);
        dpadRight = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(gameView.getResources(),R.drawable.dpad_right),dpadButtonWidth*3,dpadButtonWidth*3,false);
        dpadLeft = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(gameView.getResources(),R.drawable.dpad_left),dpadButtonWidth*3,dpadButtonWidth*3,false);
        dpadUp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(gameView.getResources(),R.drawable.dpad_up),dpadButtonWidth*3,dpadButtonWidth*3,false);
        dpadDown = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(gameView.getResources(),R.drawable.dpad_down),dpadButtonWidth*3,dpadButtonWidth*3,false);

        defaultDpadX = 0;
        defaultDpadY = gameView.getDisplayHeight() - dpadButtonHeight *3;

        dpadLeftX = 0;
        dpadLeftY = gameView.getDisplayHeight() - dpadButtonHeight * 2;
        dpadLeftWidth = dpadLeftX + dpadButtonWidth;
        dpadLeftHeight = dpadLeftY + dpadButtonHeight;

        dpadRightX = dpadButtonWidth * 2;
        dpadRightY = gameView.getDisplayHeight() - dpadButtonHeight *2;
        dpadRightWidth = dpadRightX + dpadButtonWidth;
        dpadRightHeight = dpadRightY + dpadButtonHeight;

        dpadUpX = dpadButtonWidth;
        dpadUpY = gameView.getDisplayHeight() - dpadButtonHeight * 3;
        dpadUpWidth = dpadUpX + dpadButtonWidth;
        dpadUpHeight = dpadUpY + dpadButtonHeight;

        dpadDownX = dpadButtonWidth;
        dpadDownY = gameView.getDisplayHeight() - dpadButtonHeight;
        dpadDownWidth = dpadDownX + dpadButtonWidth;
        dpadDownHeight = dpadDownY + dpadButtonHeight;

        dpadAbuttonX = gameView.getDisplayWidth() - dpadButtonWidth - (dpadButtonWidth/4);
        dpadAbuttonY = gameView.getDisplayHeight() - dpadButtonHeight * 2;
        dpadAbuttonWidth = dpadAbuttonX + dpadButtonWidth;
        dpadAbuttonHeight = dpadAbuttonY + dpadButtonHeight;

        dpadDefault = dpadIdle;

    }

    public void updateDpad(){

    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(dpadDefault,defaultDpadX,defaultDpadY,null);
        //Testing button positions to show on screen but not shown in end game
        /*canvas.drawRect(dpadLeftX,dpadLeftY,dpadLeftWidth,dpadLeftHeight,dpadTest);
        canvas.drawRect(dpadRightX,dpadRightY,dpadRightWidth,dpadRightHeight,dpadTest);
        canvas.drawRect(dpadUpX,dpadUpY,dpadUpWidth,dpadUpHeight,dpadTest);
        canvas.drawRect(dpadDownX,dpadDownY,dpadDownWidth,dpadDownHeight,dpadTest);*/
        canvas.drawRect(dpadAbuttonX,dpadAbuttonY,dpadAbuttonWidth,dpadAbuttonHeight,dpadTest);
    }
    //When the user presses the screen in the GameView these are called to check if the user has pressed inside the buton location on screen
    public boolean getLeftXY(int x, int y){
        boolean checkxy = x > dpadLeftX && x < dpadLeftX +
                dpadButtonWidth && y > dpadLeftY && y < dpadLeftY + dpadButtonHeight;
        return checkxy;
    }
    public boolean getRightXY(int x, int y){
        boolean checkxy = x > dpadRightX && x < dpadRightX +
                dpadButtonWidth && y > dpadRightY && y < dpadRightY + dpadButtonHeight;
        return checkxy;
    }

    public boolean getUpXY(int x, int y){
        boolean checkxy = x > dpadUpX && x < dpadUpX +
                dpadButtonWidth && y > dpadUpY && y < dpadUpY + dpadButtonHeight;
        return checkxy;
    }

    public boolean getDownXY(int x, int y){
        boolean checkxy = x > dpadDownX && x < dpadDownX +
                dpadButtonWidth && y > dpadDownY && y < dpadDownY + dpadButtonHeight;
        return checkxy;
    }
    public boolean getAbuttonXY(int x, int y){
        boolean checkxy = x > dpadAbuttonX && x < dpadAbuttonX +
                dpadButtonWidth && y > dpadAbuttonY && y < dpadAbuttonY + dpadButtonHeight;
        return checkxy;
    }
}
