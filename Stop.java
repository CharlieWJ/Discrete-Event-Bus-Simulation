//contain the queue of Riders waiting for the bus at that stop.
import java.util.Random;
public class Stop {
    public static Q[] stop=new Q[30];
    //                                          0,  1,  2,  3,  4,  5,  6,  7,  8, 9,10, 11,  12,  13,   14,  15,  16,   17,   18
    private static double[] arrivalPercents = {.75,.75,.5, .5, .5, .2, .2, .2, .2, 0, 0, -.2, -.2, -.2, -.5, -.5, -.5, -.75, -.75};
    public static double addWhen;
    public static int maxLine,maxStop;

    public static void addRider(int stopNum){
        Random rand1 = new Random();
        int idx=rand1.nextInt(19);
        double pct=arrivalPercents[idx];
        addWhen=120+pct*120;
        if(stopNum==0||stopNum==1||stopNum==29||stopNum==14||stopNum==15||stopNum==16){
            if(BusSim.agenda.getCurrentTime()%(addWhen)==0){
                stop[stopNum].add(new Rider(stopNum));
                stop[stopNum].add(new Rider(stopNum));
                Rider.arrivalTime=BusSim.agenda.getCurrentTime();
                Rider.allArrival+=Rider.arrivalTime;
                Stats.totPeople+=2;//adds 2 riders to downtown location.
            } }
        else{
            if(BusSim.agenda.getCurrentTime()%addWhen==0) {//every 120 seconds a new rider is added to the lines
                stop[stopNum].add(new Rider(stopNum));
                Rider.arrivalTime=BusSim.agenda.getCurrentTime();
                Rider.allArrival+=Rider.arrivalTime;
                Stats.totPeople++; } } }
}
