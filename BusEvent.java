public class BusEvent implements IEvent{
    private Bus bus;
    private int currentStop;

    public BusEvent(int stop, Bus b){
        this.bus=b;this.currentStop=stop;
    }

    @Override
    public void run() {
        int time=0;
        if(Stats.totCap>0){ Stats.totCap-=this.bus.size();}
        time=(this.bus.dropOff(currentStop)+this.bus.pickUp(currentStop));
        if(currentStop==29){//if it is the final stop, the bus loops around and travels to the first stop.
            currentStop=-1;
        }
        currentStop++;//moves to the next stop

        BusSim.agenda.add(new BusEvent(currentStop,this.bus),time+240+15);//adds 240 to account for the time it takes to travel to the next stop, and 15 to wait for riders to arrive.
        Stats.totCap+=this.bus.size();
        //this.bus.seatsFilled();
    }
}
