package map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Wall extends MapObject {
    public Wall() {
        this.imageUrl = "img/background/wall.png";

    }

    public ImageView initializeWall( int x, int y, int width, int height){
        FileInputStream inputstreamWall;
        ImageView imageViewWall = new ImageView();

        try {
            inputstreamWall = new FileInputStream(imageUrl);
        
        Image wall = new Image(inputstreamWall);
        imageViewWall.setImage(wall);
        imageViewWall.setX(x);
        imageViewWall.setY(y);
        imageViewWall.setFitHeight(height);
        imageViewWall.setFitWidth(width);
        imageViewWall.setPreserveRatio(false);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
        return imageViewWall;

    }
    public ImageView initializeWalltop( int x, int y, int width, int height)  {
        FileInputStream inputstreamWall;
        ImageView imageViewWall = new ImageView();

        try {
            inputstreamWall = new FileInputStream("img/background/walltop.png");
        
        Image wall = new Image(inputstreamWall);
        imageViewWall.setImage(wall);
        imageViewWall.setX(x);
        imageViewWall.setY(y);
        imageViewWall.setFitHeight(height);
        imageViewWall.setFitWidth(width);
        imageViewWall.setPreserveRatio(false);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
        return imageViewWall;

    }

}
