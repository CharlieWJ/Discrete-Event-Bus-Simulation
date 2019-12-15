public class ExpressBus extends Bus{
    //Use: Services stops 0, 1, 4, 8, 12, 14, 15, 16, 20, 24, 28, and 29. 12 Stops total
    //Thought: Riders that board express buses only travel to other express bus stops.
    //Thought: if the rider wants to go to any of the above stops, then they should board the express bus, only.
    // (unless they are traveling to stop 15 or 16).
    private Rider[] bus=new Rider[50];
    private int on;//keeps track of the number of passengers on board.
    public static int[] expressStops={0, 1, 4, 8, 12, 14, 15, 16, 20, 24, 28, 29};
    private Rider[] holder;

    public void inLineExp(int stopNum){
        Q<Object> temp=new Q<Object>();//Holds non-ExpressRiders
        int count=0;
        for(int i=0;i<Stop.stop[stopNum].length();i++){
            temp.add(Stop.stop[stopNum].remove());
            count++;
        }
        for(int i=0;i<temp.length();i++){
            Stop.stop[stopNum].add(temp.remove());
        }
        Rider[] riders=new Rider[Stop.stop[stopNum].length()];
        for(int i=0;i<count;i++){
            riders[i]=(Rider)temp.remove();
        }
        this.holder=riders;
    }

    public int pickExpress(int stopNum){
        int numBoarded=0;
        int line= Stop.stop[stopNum].length();
        inLineExp(stopNum);
        int index=0;
        int count=0;
        if(line>0 && hasSpace()){
            for(int i=0;i<this.holder.length;i++) {
                for(int z=0;z<ExpressBus.expressStops.length;z++) {
                    if(index>49){
                        //System.out.println("All seats filled");
                        count=numBoarded*3;
                        return count;
                    }
                    else if(this.bus[index]!=null){
                        index++;
                    }
                    else if(this.bus[index]==null && this.holder[i]!=null){
                        if (this.holder[i].getDropOff()==ExpressBus.expressStops[z] && this.holder[i].getPickUp()==ExpressBus.expressStops[z]) {
                            this.holder[i].expPickUpTime=BusSim.agenda.getCurrentTime();//getPickUpTime()
                            Stats.netWait+=(this.holder[i].expPickUpTime-Rider.arrivalTime);
                            Stats.totWait+=(this.holder[i].expPickUpTime-Rider.arrivalTime);
                            if(Stats.longestWait<(this.holder[i].expPickUpTime-Rider.arrivalTime)){
                                Stats.longestWait=(this.holder[i].expPickUpTime-Rider.arrivalTime);
                            }
                            Rider.gotOn++;
                            this.bus[index]=this.holder[i];
                            this.holder[i]=null;
                            sortThis(this.holder);
                            line--;
                            on++;
                            numBoarded++;
                            BusSim.theTime+=3;
                            BusSim.agenda.getCurrentTime();
                        } } } }
        }
        else if( !( hasSpace() ) ){
            //System.out.println("Express Bus is full.");
        }
        //System.out.println("The Number of Express passengers Boarded at stop "+stopNum+" is "+numBoarded);
        count=numBoarded*3;
        return count;
    }

    public int dropOffExp(int stopNum){
        int numDropped=0;
        int count=0;
        boolean val=false;
        sort();
        for(int i=0;i<this.bus.length;i++){
            if(this.bus[i]!=null){
                if(stopNum==this.bus[i].getDropOff()){
                    this.bus[i]=null;
                    on--;
                    numDropped++;
                    count++;
                    BusSim.theTime+=2;
                }
            }
            val=true;
        }
        count*=2;
        //System.out.println("The Number of passengers dropped off at stop "+stopNum+" is "+numDropped);
        return count;
    }

    public void expSeatsFilled(){
        int holder=this.bus.length;
        System.out.print('\n'+"$$$$$$$");
        System.out.println("There are "+on+" seats filled, and "+(holder-on)+" seats open."+"$$$$$$$");
    }
}
