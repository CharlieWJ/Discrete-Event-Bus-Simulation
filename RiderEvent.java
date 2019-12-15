import java.util.Random;

public class RiderEvent implements IEvent{
    private int busStop;

    public RiderEvent(int busStop){
        this.busStop = busStop;
    }
    public int getBusStop(){ return busStop; }

    @Override
    public void run() {
        Rider rider=new Rider(busStop);//this is their pick up
        //Stop.stop[busStop].add(rider);
        Stop.addRider(busStop);
        BusSim.agenda.add(new RiderEvent(busStop),BusSim.arrival);//BusSim.arrival);
        //System.out.println("Stop: "+busStop+", Current Time is: "+BusSim.theTime);
    }
}
