package misterdneh.ca.pixelprisoner.entities;

import android.graphics.Bitmap;

import misterdneh.ca.pixelprisoner.GameView;
import misterdneh.ca.pixelprisoner.R;

public class Player extends Entityinfo{

    public Player(GameView gameView){
        this.gameView = gameView;
        entityWidth = gameView.defaultTilesize;
        entityHeight = gameView.defaultTilesize;
        walkSpeed = 8;
        entityWalking = new Bitmap[12];
        setupSpritesheet(R.drawable.playwalkingspritesheet,entityWalking,16);
        defaultimg = entityWalking[0];
    }
    public void update(){
        updatePosition();
    }
    public void updatePosition(){
        if (entityRight){
            entityDirection = "right";
            entityDefaultDirection = "right";
        }else if (entityLeft){
            entityDirection = "left";
            entityDefaultDirection = "left";
        }else if (entityUp){
            entityDirection = "up";
            entityDefaultDirection = "up";
        }else if (entityDown){
            entityDirection = "down";
            entityDefaultDirection = "down";
        }
        switch (entityDirection) {
            case "right":
                if (!collision) {
                    posX += walkSpeed;
                }
                break;
            case "left":
                if (!collision) {
                    posX -= walkSpeed;
                }
                break;
            case "down":
                if (!collision) {
                    posY += walkSpeed;
                }
                break;
            case "up":
                if (!collision) {
                    posY -= walkSpeed;
                }
                break;
        }
        updateAnimation();
    }

    public void updateAnimation (){
        if (!gameView.checkbuttonpressed){
            entityDirection = "buttonreleased";
        }
        if (entityDirection.equals("down")) {
            defaultimg = entityWalking[0];
        }
        if (entityDirection.equals("up")) {
            defaultimg = entityWalking[9];
        }
        if (entityDirection.equals("right")) {
            defaultimg = entityWalking[6];
        }
        if (entityDirection.equals("left")) {
            defaultimg = entityWalking[3];
        }
    }
}
