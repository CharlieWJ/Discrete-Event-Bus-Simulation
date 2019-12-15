import java.util.Scanner;
public class BusSim {
    public static PQ agenda=new PQ();
    public static double theTime=agenda.getCurrentTime();
    public static int runTime;
    public static int arrival;
    public static int all;

    public static void main(String[] args){
        for(int i=0;i<30;i++){
            if(Stop.stop[i]==null){
                Stop.stop[i]=new Q<Rider>();
            }
        }
        for(int i=0;i<30;i++){
            agenda.add(new RiderEvent(i),0);
        }
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to the Bus Simulation for Minneapolis, Minnesota's Bus Route 3.");
        //String pick = s.nextLine();
        System.out.println("Do you want to run the simulation for one day 12 hours, or during rush hour?");
        String pick = s.nextLine();
        if(pick.equals("rush")||pick.equals("hour")||pick.equals("rush hour")){
            arrival=60;
            runTime=10800;
        }
        else if(pick.equals("day")||pick.equals("one day")||pick.equals("one")){
            runTime=86400;
            arrival=120;
        }
        else if(pick.equals("half")||pick.equals("12 hours")||pick.equals("half day")){
            runTime=86400/2;
            arrival=120;
        }
        //NORMAL
        agenda.add(new BusEvent(1,new Bus()),0);Stats.numBuses++;
        agenda.add(new BusEvent(3,new Bus()),0);Stats.numBuses++;
        agenda.add(new BusEvent(5,new Bus()),0);Stats.numBuses++;
        agenda.add(new BusEvent(9,new Bus()),0);Stats.numBuses++;
        agenda.add(new BusEvent(11,new Bus()),0);Stats.numBuses++;
        agenda.add(new BusEvent(17,new Bus()),0);Stats.numBuses++;
        agenda.add(new BusEvent(19,new Bus()),0);Stats.numBuses++;
        agenda.add(new BusEvent(22,new Bus()),0);Stats.numBuses++;
        agenda.add(new BusEvent(25,new Bus()),0);Stats.numBuses++;
        agenda.add(new BusEvent(27,new Bus()),0);Stats.numBuses++;

        //EXPRESS
        agenda.add(new ExpressBusEvent(29,new ExpressBus()),0);Stats.expBuses++;
        agenda.add(new ExpressBusEvent(8,new ExpressBus()),0);Stats.expBuses++;
        agenda.add(new ExpressBusEvent(15,new ExpressBus()),0);Stats.expBuses++;
        agenda.add(new ExpressBusEvent(24,new ExpressBus()),0);Stats.expBuses++;

        while(agenda.getCurrentTime()<runTime){//one day
            agenda.remove().run();
            if (BusSim.agenda.getCurrentTime() % 60 == 0) {
                for (int i = 0; i < 30; i++) {
                    if (Stop.stop[i].length() > Stop.maxLine) {
                        Stop.maxLine = Stop.stop[i].length();
                        Stop.maxStop = i;
                    }
                }
            }
        }
        System.out.println("-------------------");
        for(int i=0;i<30;i++){
            all+=Stop.stop[i].length();
            System.out.println("The Number of people waiting at bus stop "+i+" is: "+Stop.stop[i].length());
        }
        System.out.println("-------------------");
        System.out.println("The longest line for all bus stops was "+Stop.maxLine+" people, at Stop "+Stop.maxStop);
        Stats.averageWaitTime();
        int totalBuses=Stats.numBuses+Stats.expBuses;
        Stats.averageUnservedTime();
        System.out.println("Number of Buses Ran "+totalBuses);//+Stats.expBuses);
        System.out.println("Number of Normal Buses Ran: "+ Stats.numBuses+'\n'+"Number of Express Buses Ran: "+Stats.expBuses);
        Stats.averageBusCap();
        System.out.println("The number of people that were unable to be serviced is "+all);
        System.out.println("This concludes the simulation.");

    }
}

