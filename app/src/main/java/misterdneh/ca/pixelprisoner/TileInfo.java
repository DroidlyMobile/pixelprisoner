package misterdneh.ca.pixelprisoner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.ArrayList;

import android.graphics.*;

public class TileInfo {

    public GameView gameView;
    public InputStream inputStream;
    public BufferedReader bufferedReader;
    public int[][] worldTileNumLayer1;//Receives the tile at the XY positions in the world
    public int tileNum1 = 1;
    public int tileNum2 = 0;

    public boolean tileCollision = false;

    public Bitmap defaultTileimg = null;//Default tile image drawn when cycling through world data
    public Bitmap[] tileImgs = new Bitmap[1000];//Add up to 1000 tile images

    public ArrayList<Integer> tilesList = new ArrayList<>();
    public ArrayList<String> collisionTiles = new ArrayList<>();
    public ArrayList<String> collisionTilesLayer2 = new ArrayList<>();
    public ArrayList<String> animatedTileList = new ArrayList<>();
}
