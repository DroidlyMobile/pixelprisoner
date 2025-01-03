package misterdneh.ca.pixelprisoner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import misterdneh.ca.pixelprisoner.entities.Player;
import misterdneh.ca.pixelprisoner.ui.Dpad;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    public GameLoop gameLoop;
    public Dpad dpad;
    public ArrayList<String> buttonlist = new ArrayList<>();
    public ArrayList<String> buttonpointerlist = new ArrayList<>();
    public int pointerID = 0;
    public int defaultTilesize = 0;
    public boolean checkbuttonpressed = false;
    public String checkbutton = "none";
    public Player player;
    public int worldSizeX = 0;
    public int worldSizeY = 0;
    public TileManager tileManager;

    public GameView(Context context){
        super(context);
        getHolder().addCallback(this);
        gameLoop = new GameLoop(getHolder(),this);
        dpad = new Dpad(this);
        defaultTilesize = 160;//multiplied by 10
        player = new Player(this);
        worldSizeX = 50;
        worldSizeY = 50;
        tileManager = new TileManager(this);
        tileManager.loadMapLayer1("testmap");
    }
    public void update(){
        handleButtonsPressed();
        player.update();
    }
    public void draw(Canvas canvas){
        super.draw(canvas);
        tileManager.draw(canvas);
        player.draw(canvas);
        dpad.draw(canvas);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        pointerID = event.getPointerId(event.getActionIndex());
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                //Get pointer ID, set pointer to list, check list, handle button or buttons pressed
                try {
                    if (dpad.getAbuttonXY((int)event.getX(pointerID),
                            (int)event.getY(pointerID))
                            && !buttonlist.contains("A BUTTON")) {
                        buttonlist.add("A BUTTON");
                        buttonpointerlist.add(String.valueOf(pointerID));
                    } else if (dpad.getLeftXY((int)event.getX(pointerID),
                            (int)event.getY(pointerID))
                            && !buttonlist.contains("LEFT BUTTON")
                            && !buttonlist.contains("RIGHT BUTTON")
                            && !buttonlist.contains("UP BUTTON")
                            && !buttonlist.contains("DOWN BUTTON")) {
                        buttonlist.add("LEFT BUTTON");
                        buttonpointerlist.add(String.valueOf(pointerID));
                        dpad.dpadDefault = dpad.dpadLeft;
                    } else if (dpad.getRightXY((int)event.getX(pointerID),
                            (int)event.getY(pointerID))
                            && !buttonlist.contains("RIGHT BUTTON")
                            && !buttonlist.contains("LEFT BUTTON")
                            && !buttonlist.contains("UP BUTTON")
                            && !buttonlist.contains("DOWN BUTTON")) {
                        buttonlist.add("RIGHT BUTTON");
                        buttonpointerlist.add(String.valueOf(pointerID));
                        dpad.dpadDefault = dpad.dpadRight;
                    } else if (dpad.getUpXY((int)event.getX(pointerID),
                            (int)event.getY(pointerID))
                            && !buttonlist.contains("UP BUTTON")
                            && !buttonlist.contains("LEFT BUTTON")
                            && !buttonlist.contains("RIGHT BUTTON")
                            && !buttonlist.contains("DOWN BUTTON")) {
                        buttonlist.add("UP BUTTON");
                        buttonpointerlist.add(String.valueOf(pointerID));
                        dpad.dpadDefault = dpad.dpadUp;
                    } else if (dpad.getDownXY((int)event.getX(pointerID),
                            (int)event.getY(pointerID))
                            && !buttonlist.contains("UP BUTTON")
                            && !buttonlist.contains("LEFT BUTTON")
                            && !buttonlist.contains("RIGHT BUTTON")
                            && !buttonlist.contains("DOWN BUTTON")) {
                        buttonlist.add("DOWN BUTTON");
                        buttonpointerlist.add(String.valueOf(pointerID));
                        dpad.dpadDefault = dpad.dpadDown;
                    }

                } catch (IllegalArgumentException iae) {
                }
                return true;
            //When the user releases the finger at pointer location, the indexed pointer removes the pointer and the
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                //pointerID = event.getPointerId(event.getActionIndex());
                if (buttonpointerlist.contains(String.valueOf(pointerID))) {
                    handButtonsReleased(pointerID);
                    buttonlist.remove(buttonpointerlist.indexOf(String.valueOf(pointerID)));
                    buttonpointerlist.remove(buttonpointerlist.indexOf(String.valueOf(pointerID)));
                }

        }
        return super.onTouchEvent(event);
    }
    private void handleButtonsPressed() {
        if (buttonlist.contains("LEFT BUTTON")){
            checkbuttonpressed = true;
            checkbutton = "left";
            player.entityLeft = true;
        }else
        if (buttonlist.contains("RIGHT BUTTON")){
            checkbuttonpressed = true;
            checkbutton = "right";
            player.entityRight = true;
        }else
        if (buttonlist.contains("UP BUTTON")){
            checkbuttonpressed = true;
            checkbutton = "up";
            player.entityUp = true;
        }else
        if (buttonlist.contains("DOWN BUTTON")){
            checkbuttonpressed = true;
            checkbutton = "down";
            player.entityDown = true;
        }else {
            checkbuttonpressed = false;
            checkbutton = "none";
            player.entityRight = false;
            player.entityLeft = false;
            player.entityUp = false;
            player.entityDown = false;
        }
        checkbutton = "none";

        if (buttonlist.contains("A BUTTON")){

        }else {

        }
    }

    public void handButtonsReleased(int pointerID){
        String getPointInfo = buttonlist.get(buttonpointerlist.indexOf(String.valueOf(pointerID)));
        if (getPointInfo.equals("A BUTTON")){
            checkbuttonpressed = false;

            if ( buttonlist.contains("RIGHT BUTTON")){
                checkbuttonpressed = true;
                dpad.dpadDefault = dpad.dpadRight;
            }
            if (buttonlist.contains("LEFT BUTTON")){
                checkbuttonpressed = true;
                dpad.dpadDefault = dpad.dpadLeft;
            }
            if (buttonlist.contains("UP BUTTON")){
                checkbuttonpressed = true;
                dpad.dpadDefault = dpad.dpadUp;
            }
            if ( buttonlist.contains("DOWN BUTTON")){
                checkbuttonpressed = true;
                dpad.dpadDefault = dpad.dpadDown;
            }
        }

        if (getPointInfo.equals("LEFT BUTTON")||
                getPointInfo.equals("RIGHT BUTTON")||
                getPointInfo.equals("UP BUTTON")||
                getPointInfo.equals("DOWN BUTTON")){
            checkbuttonpressed = false;
            dpad.dpadDefault = dpad.dpadIdle;
        }

    }

    public int getDisplayWidth(){
        return getResources().getDisplayMetrics().widthPixels;
    }
    public int getDisplayHeight(){
        return getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameLoop = new GameLoop(getHolder(),this);
        gameLoop.setRunning(true);
        gameLoop.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        boolean retry = true;
        gameLoop.setRunning(false);
        while (retry){
            try {
                gameLoop.join();
                retry = false;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
