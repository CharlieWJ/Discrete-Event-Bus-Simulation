import java.lang.*;
public class Bus {
    private Rider[] bus=new Rider[50];
    private int onBoard=0;//keeps track of the number of passengers on board.
    private Rider[] holder;

    public void inLine(int stopNum){
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

    public int size() {
        int count=0;
        for (Rider rider : this.bus) {
            if (rider != null) {
                count++;
            } else {
                count=count;
            }
        }
        return count;//number of people on the bus.
    }

    public int pickUp(int stopNum){
        int numBoarded=0;
        int count=0;
        int line= Stop.stop[stopNum].length();
        inLine(stopNum);
        int index=0;
        if(line>0 && hasSpace()){
            for(int i=0;i<this.holder.length;i++) {
                for(int z=0;z<ExpressBus.expressStops.length;z++) {
                    if(index>49){
                        return count*=3;
                    }
                    else if(this.bus[index]!=null){
                        index++;
                    }
                    else if(this.bus[index]==null && this.holder[i]!=null){
                        if (this.holder[i].getDropOff()!=ExpressBus.expressStops[z] && this.holder[i].getPickUp()!=ExpressBus.expressStops[z]&&this.holder[i]!=null) {
                            this.holder[i].pickUpTime=BusSim.agenda.getCurrentTime();//getPickUpTime()
                            Stats.netWait+=(this.holder[i].pickUpTime-Rider.arrivalTime+240);//lines 54 through 58 are used to calculate Statistics.
                            Stats.totWait+=(this.holder[i].pickUpTime-Rider.arrivalTime+240);
                            if(Stats.longestWait<(this.holder[i].pickUpTime-Rider.arrivalTime)){
                                Stats.longestWait=(this.holder[i].pickUpTime-Rider.arrivalTime);
                            }
                            Rider.gotOn++;
                            this.bus[index]=this.holder[i];
                            sort();
                            line--;
                            onBoard++;
                            numBoarded++;
                            count++;
                            this.holder[i]=null;
                            sortThis(this.holder);
                        }
                    }
                }
            }
        }
        else if( !( hasSpace() ) ){
            return count*=3;
        }
        //System.out.println("The Number of passengers Boarded at stop "+stopNum+" is "+count);
        count*=3;
        return count;
    }

    public int dropOff(int stopNum){
        int numDropped=0;
        int count=0;
        sort();
        for(int i=0;i<this.bus.length;i++){
            if(this.bus[i]!=null){
                if(stopNum==this.bus[i].getDropOff()){
                    this.bus[i]=null;
                    onBoard--;
                    numDropped++;
                    count++;
                }
            }
        }
        count*=2;
        //System.out.println("The Number of passengers dropped off at stop "+stopNum+" is "+numDropped);
        return count;
    }

    public void seatsFilled(){
        int holder=this.bus.length;
        System.out.print('\n'+"*******");
        System.out.println("There are "+onBoard+" seats filled, and "+(holder-onBoard)+" seats open."+"*******");
    }

    public void sort() {
        int n = this.bus.length;
        for (int i = 0; i < this.bus.length - 1; i++) {
            int low = i;
            for (int z = i + 1; z < n; z++) {
                if(this.bus[z]==null && this.bus[low]!=null){
                    low = z;
                }
                Rider temp = this.bus[low];
                this.bus[low] = this.bus[i];
                this.bus[i] = temp;
            }
        }
    }

    public Rider[] sortThis(Rider[] r){
        int n = r.length;
        for (int i = 0; i < n - 1; i++) {
            int low = i;
            for (int z = i + 1; z < n; z++) {
                if(r[z]==null && r[low]!=null){
                    low = z;
                }
                Rider temp = r[low];
                r[low] = r[i];
                r[i] = temp;
            }
        }
        return r;
    }
    public boolean hasSpace(){//if the bus is not full, then passengers can board
        return onBoard<this.bus.length;
    }
}
