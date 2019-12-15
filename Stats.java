public class Stats{
    public static double netWait,totWait;
    public static int totCap,totPeople;
    public static int numBuses,expBuses;
    public static double longestWait;


    public static void averageWaitTime(){
        System.out.println("The average wait time for riders who do board the bus is: "+netWait/Rider.gotOn);
        //System.out.println("Total average wait time: "+Rider.allArrival/totPeople);
    }
    public static void averageBusCap(){
        System.out.println("The average number of seats filled on a normal bus is: "+totCap/numBuses);
    }

    public static void averageUnservedTime(){
        System.out.println("The average wait time for all riders is: "+totWait/BusSim.all);
    }
    public static void getLongestWait(){
        System.out.println("The longest wait time for a rider who did board the bus is: "+longestWait);
    }

}
