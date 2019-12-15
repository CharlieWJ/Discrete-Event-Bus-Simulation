import java.util.Random;
//Rider object, gets on the bus. Similar to the passenger class in Lab 11.
public class Rider{
    private int pickUpStop;
    private int dropOff;//idx= 0, 1 ,2, 3, 4, 5, 6, 7, 8, 9, 10,11,12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35
    private int[] stopSelect= {0, 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 14, 15, 15, 16, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 29};
    public static double arrivalTime,allArrival;
    public static double pickUpTime,expPickUpTime;
    public static double gotOn;
    //Chances of desired drop-off stops being downtown (0, 1. 29, 14, 15, and  16) are doubled.

    public int getPickUp(){ /*pickUpTime=BusSim.agenda.getCurrentTime();*/return pickUpStop; }
    public int getDropOff(){ return dropOff; }
    public double getPickUpTime(){ pickUpTime=BusSim.agenda.getCurrentTime(); return pickUpTime;}
    public double getDropOffTime(){ return BusSim.agenda.getCurrentTime(); }

    public Rider(int pickUp){
        //Rule 1) Eastbound riders (boarding at bus stops 0-14) will only travel east.
        //Rule 2) Westbound riders (boarding at bus stops 15-29) will only travel west.
        //Going off of this, riders that board on stop 14 must and will only travel to stop 15.
        this.pickUpStop = pickUp;//the bus stop they get where they are picked up.
        Random rand1 = new Random();
        if(pickUp<14){//Purpose: Generates a number between 0 (inclusive) and 20 (exclusive). This serves as the index of the stop that will be assigned.
            this.dropOff=stopSelect[rand1.nextInt(20)];//15-pickUp)+pickUp];//possible stops are only between pick up and 15
        }
        else if(pickUp==14){
            this.dropOff=15;
        }
        else if(pickUp==15){//Purpose: Generates a number between 20 (inclusive) and 36 (exclusive). This serves as the index of the stop that will be assigned.
            this.dropOff=stopSelect[rand1.nextInt(36-20)+20];
        }
    }
}
