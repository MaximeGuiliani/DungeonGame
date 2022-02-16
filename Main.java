interface Syrupable{
    void getSugary();
}
abstract class Pancake implements Syrupable{}

class BlueBerryPancake implements Pancake{
    public void getSugary(){ ; }
}

class Sour extends BlueBerryPancake{
    void getSugary(int s){;}
}