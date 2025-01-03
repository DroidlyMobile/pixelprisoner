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
        entityAnimMaxCount = 12;
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
        }else if (!gameView.checkbuttonpressed){//resets player idle image
            entityDirection = "buttonreleased";
            entityAnimNum = 1;
            entityAnimCounter = 0;
        }

        if (entityDirection.equals("attack")){
            //attackAnimation();
        }else if (entityDirection.equals("mining")){
            //miningAnimation();
        }else {
            updateEntityWalking();
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

    }

    public void updateEntityWalking (){
            if (!gameView.checkbuttonpressed) {
                entityDirection = "buttonreleased";
            }
            if (gameView.checkbuttonpressed) {
                entityAnimCounter++;
                if (entityAnimCounter > entityAnimMaxCount) {
                    if (entityAnimNum == 1) {
                        entityAnimNum = 2;
                    } else if (entityAnimNum == 2) {
                        entityAnimNum = 3;
                    } else if (entityAnimNum == 3) {
                        entityAnimNum = 4;
                    } else if (entityAnimNum == 4) {
                        entityAnimNum = 1;
                    }
                    entityAnimCounter = 0;
                }

            } else {
                if (entityAnimCounter < entityAnimMaxCount + 1) {
                    entityAnimCounter = 0;
                    entityAnimNum = 1;
                    if (entityAnimNum == 1) {
                        entityAnimNum = 2;
                    } else if (entityAnimNum == 2) {
                        entityAnimNum = 3;
                    } else if (entityAnimNum == 3) {
                        entityAnimNum = 4;
                    }
                }
            }

        if (entityDirection.equals("down")) {
            if (entityAnimNum == 1 || entityAnimNum == 3) {
                defaultimg = entityWalking[0];
            }
            if (entityAnimNum == 2) {
                defaultimg = entityWalking[1];
            }
            if (entityAnimNum == 4) {
                defaultimg = entityWalking[2];
            }
        }
        if (entityDirection.equals("up")) {
            if (entityAnimNum == 1 || entityAnimNum == 3) {
                defaultimg = entityWalking[9];
            }
            if (entityAnimNum == 2) {
                defaultimg = entityWalking[10];
            }
            if (entityAnimNum == 4) {
                defaultimg = entityWalking[11];
            }
        }
        if (entityDirection.equals("right")) {
            if (entityAnimNum == 1 || entityAnimNum == 3) {
                defaultimg = entityWalking[6];
            }
            if (entityAnimNum == 2) {
                defaultimg = entityWalking[7];
            }
            if (entityAnimNum == 4) {
                defaultimg = entityWalking[8];
            }
        }
        if (entityDirection.equals("left")) {
            if (entityAnimNum == 1 || entityAnimNum == 3) {
                defaultimg = entityWalking[3];
            }
            if (entityAnimNum == 2) {
                defaultimg = entityWalking[4];
            }
            if (entityAnimNum == 4) {
                defaultimg = entityWalking[5];
            }
        }
        if (entityDirection.equals("buttonreleased")) {//helps reset player animation to idle
            if (entityDefaultDirection.equals("up")) {
                defaultimg = entityWalking[9];
            }
            if (entityDefaultDirection.equals("down")) {
                defaultimg = entityWalking[0];
            }
            if (entityDefaultDirection.equals("right")) {
                defaultimg = entityWalking[6];
            }
            if (entityDefaultDirection.equals("left")) {
                defaultimg = entityWalking[3];
            }
        }
    }
}
