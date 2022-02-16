package map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Door extends MapObject {
    public Door() {
        this.imageUrl = "img/background/door.png";
    }

    public ImageView initializeDoor(int x, int y) {
        FileInputStream inputstreamDoor;
        ImageView imageViewDoor = new ImageView();

        try {
            inputstreamDoor = new FileInputStream(imageUrl);
       
        Image door = new Image(inputstreamDoor);
        imageViewDoor.setImage(door);
        imageViewDoor.setX(x);
        imageViewDoor.setY(y);
        imageViewDoor.setFitHeight(200);
        imageViewDoor.setFitWidth(100);
        imageViewDoor.setPreserveRatio(true);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
        return imageViewDoor;

    }

}
