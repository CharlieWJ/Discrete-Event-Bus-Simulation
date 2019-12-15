public class ExpressBusEvent implements IEvent {
    private int expressStop;
    private ExpressBus express;
    private int time;


    public ExpressBusEvent(int stop, ExpressBus b){
        this.express=b;this.expressStop=stop;
    }
    //Use: Services stops 0, 1, 4, 8, 12, 14, 15, 16, 20, 24, 28, and 29. 12 Stops total
    @Override
    public void run() {//TODO Fix the stops
        time=(this.express.dropOffExp(expressStop)+this.express.pickExpress(expressStop));
        if(Stats.totCap>0){ Stats.totCap-=this.express.size();}
        if(expressStop==0){
            expressStop=1;
            BusSim.agenda.add(new ExpressBusEvent(expressStop,this.express),time+240+15);
            time=0;
        }
        else if(expressStop==1){
            expressStop=4;
            BusSim.agenda.add(new ExpressBusEvent(expressStop,this.express),time+240*3+15);time=0;
        }
        else if(expressStop==4||expressStop==8){
            expressStop+=4;
            time+=15+240*4;
            BusSim.agenda.add(new ExpressBusEvent(expressStop,this.express),time);time=0;
        }
        else if(expressStop==12){
            expressStop=14;time+=15+240*2;
            BusSim.agenda.add(new ExpressBusEvent(expressStop,this.express),time);time=0;
        }
        else if(expressStop==14||expressStop==15){
            expressStop+=1;time+=15+240;
            BusSim.agenda.add(new ExpressBusEvent(expressStop,this.express),time);time=0;
        }
        else if (expressStop==16 || expressStop==20 ||expressStop==24){
            expressStop+=4;time+=15+240*4;
            BusSim.agenda.add(new ExpressBusEvent(expressStop,this.express),time);time=0;
        }
        else if(expressStop==28){
            expressStop=29;time+=15+240;
            BusSim.agenda.add(new ExpressBusEvent(expressStop,this.express),time);time=0;
        }
        else if(expressStop==29){
            expressStop=0;time+=15+240;
            BusSim.agenda.add(new ExpressBusEvent(expressStop,this.express),time);time=0;
        }
        //this.express.expSeatsFilled();
        time=0;
    }
}
