package misterdneh.ca.pixelprisoner.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import misterdneh.ca.pixelprisoner.GameView;

public class Entityinfo {
    public GameView gameView;

    public int screenX = 0;
    public int screenY = 0;
    public int entityWidth = 0;
    public int entityHeight = 0;
    public int posX = 0;
    public int posY = 0;
    public int walkSpeed = 0;

    public boolean entityRight = false;
    public boolean entityLeft = false;
    public boolean entityUp = false;
    public boolean entityDown = false;
    public boolean collision = false;

    public String entityDirection = "";
    public String entityDefaultDirection = "";

    public Bitmap defaultimg = null;
    public Bitmap[] entityWalking = new Bitmap[100];

    public void draw(Canvas canvas){
        if (defaultimg != null) {
            canvas.drawBitmap(defaultimg, posX, posY, null);
        }
    }
    public void setupSpritesheet(int drawable, Bitmap[] bitmaps, int tileSize) {
        Bitmap dummySpritesheet;
        int currentColumn = 0;
        int currentRow = 0;
        int numberOfSprites = 0;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;//Keeps the dimensions of the image we're using
        dummySpritesheet = BitmapFactory.decodeResource(gameView.getResources(),
                drawable, bitmapOptions);
        //Some sprites might be 16x16 some might be 16x32 so we set a tileSize
        int maxColumns = dummySpritesheet.getWidth() / tileSize;
        int maxRows = dummySpritesheet.getHeight() / tileSize;
        while (currentRow < maxRows) {
            bitmaps[numberOfSprites] = Bitmap.createScaledBitmap(Bitmap.createBitmap(dummySpritesheet,
                            currentColumn * tileSize,
                            currentRow * tileSize,
                            tileSize, tileSize),
                    entityWidth, entityHeight, false);//No filters added here but you could add one with a Paint object
            currentColumn++;
            if (currentColumn == maxColumns) {
                currentColumn = 0;
                currentRow++;
            }
            numberOfSprites++;
        }
    }
}
