package com.codecool.snake.entities.enemies;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.HealthText;
import com.codecool.snake.entities.powerups.Shoot;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PowerEnemy extends GameEntity implements Animatable, Interactable{
    private static final int damage = 10;




    public PowerEnemy(Pane pane) {
        super(pane);
        setImage(Globals.fear);
        pane.getChildren().add(this);
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        startingPosition();


    }
    public void startingPosition(){
        List<Double> playerXPos = new ArrayList<>();
        List<Double> playerYPos = new ArrayList<>();

        for (GameEntity entity: Globals.getGameObjects()){
            if (entity instanceof SnakeHead || entity instanceof SnakeBody){
                playerXPos.add(entity.getX());
                playerYPos.add(entity.getY());
            }
        }
        Random rnd = new Random();
        Double enemyXPos = rnd.nextDouble()*Globals.WINDOW_WIDTH;
        Double enemyYPos = rnd.nextDouble()*Globals.WINDOW_HEIGHT;

        for (Double snakeXPos: playerXPos){
            if (enemyXPos < 700 && enemyXPos > 300){
                if(enemyXPos < 600) {
                    enemyXPos -= snakeXPos / 2;
                } else if (enemyYPos >= 400){
                    enemyXPos += snakeXPos / 2;
                }
            }
        }

        for (Double snakeYPos: playerYPos){
            if (enemyYPos < 700 && enemyYPos > 300){
                if (enemyYPos < 600){
                    enemyYPos -= snakeYPos / 2;
                } else if (enemyYPos >= 400){
                    enemyYPos += snakeYPos / 2;
                }
            }
        }

        setX(enemyXPos);
        setY(enemyYPos);

    }

    @Override
    public void step() {

        if (getX() < Globals.snakehead.getX()) {
            setX(getX() + 1);

        } else {
            setX(getX() - 1);
        }
        if (getY() < Globals.snakehead.getY()) {
            setY(getY() + 1);
        } else {
            setY(getY() - 1);
        }

    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        player.getText().changeHealth(player.getHealth(),player);
        destroy();

    }

    @Override
    public String getMessage() {
        return "10 damage";
    }

}


