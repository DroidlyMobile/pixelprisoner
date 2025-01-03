package misterdneh.ca.pixelprisoner;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TileManager extends  TileInfo{

    public TileInfo[] tileInfoLayer1 = new TileInfo[1000];//1000 is just random amount could be 20

    public TileManager(GameView gameView){
        this.gameView = gameView;
        loadTilesheet();
        worldTileNumLayer1 = new int[gameView.worldSizeX][gameView.worldSizeY];
    }

    public void loadTilesheet(){
        Bitmap tilesheet;
        int col1 = 0;
        int row1 = 0;
        int numoftiles = 0;
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        tilesheet = BitmapFactory.decodeResource(gameView.getResources(),
                R.drawable.groundtilesheet,bitmapOptions);
        int maxcol1 = tilesheet.getWidth()/16;
        int maxrow1 = tilesheet.getHeight()/16;
        while (row1 < maxrow1){
            tileImgs[numoftiles] = Bitmap.createScaledBitmap(Bitmap.createBitmap
                            (tilesheet,col1 * 16,row1 * 16,16,16),gameView.defaultTilesize,
                    gameView.defaultTilesize,false);
            col1 ++;
            if (col1 == maxcol1){
                col1 = 0;
                row1 ++;
            }
            numoftiles ++;
            tilesList.add(numoftiles);//added to keep track of the number of tiles
        }
        setUpTileInfo();//After tiles are loaded from tilesheet all tile details are setup
    }

    private void setUpTileInfo() {
        //each tile has it's own id and info stored inside it this is setup here
        collisionTiles.add(String.valueOf(11));

        for (int tileID = 0; tileID < tilesList.size(); tileID++) {
            tileInfoLayer1[tileID] = new TileInfo();
            tileInfoLayer1[tileID].defaultTileimg = tileImgs[tileID];
            if (collisionTiles.contains(String.valueOf((int) tileID))) {
                tileInfoLayer1[tileID].tileCollision = true;
            }
        }
    }

    @SuppressLint("DiscouragedApi")
    public void loadMapLayer1(final String _mapname){
        try {
            inputStream = gameView.getContext().getResources().openRawResource(
                    gameView.getContext().getResources().getIdentifier(
                            _mapname,"raw", gameView.getContext().getPackageName()));
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int column = 0;
            int row = 0;
            while (column< gameView.worldSizeX && row < gameView.worldSizeY){
                String line = bufferedReader.readLine();
                while (column < gameView.worldSizeX){
                    //Splits the line to read the data from the text
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[column]);
                    worldTileNumLayer1[column][row]= num;
                    column ++;
                }
                if (column == gameView.worldSizeX){
                    column = 0;
                    row ++;
                }
            }
            bufferedReader.close();
        }catch (Exception ignored){

        }
    }

    public void draw(Canvas canvas){
        int tileCol = 0;
        int tileRow = 0;
        while (tileCol < gameView.worldSizeX && tileRow < gameView.worldSizeY){
            tileNum1 = worldTileNumLayer1[tileCol][tileRow];
            int tilePosX = tileCol * gameView.defaultTilesize;
            int tilePosY = tileRow * gameView.defaultTilesize;
            int tileScreenX = tilePosX - gameView.player.posX + gameView.player.screenX;
            int tileScreenY = tilePosY - gameView.player.posY + gameView.player.screenY;

            if(tileScreenX > -gameView.defaultTilesize
                    && tileScreenY > -gameView.defaultTilesize
                    && tileScreenX < gameView.getDisplayWidth() + (gameView.defaultTilesize *2)
                    && tileScreenY < (gameView.getDisplayHeight() + gameView.defaultTilesize )){
                if (tileInfoLayer1[tileNum1]!=null) {
                    if (tileInfoLayer1[tileNum1].defaultTileimg != null) {
                        canvas.drawBitmap(tileInfoLayer1[tileNum1].defaultTileimg, tileScreenX, tileScreenY, null);
                    }
                }
            }
            tileCol ++;
            if (tileCol == gameView.worldSizeX){
                tileCol = 0;
                tileRow++;
            }
        }

    }
}
