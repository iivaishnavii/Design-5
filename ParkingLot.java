//TC : O(log(m*n)) where m - number of floors, n - number of levels
//SC : O(mn )  m - number of floors, n - number of levels

import java.util.PriorityQueue;

class ParkingLot{
    int maxFloors,spotsPerLevel;

    PriorityQueue<ParkingSpot> pq;
    ParkingLot(int maxFloors,int spotsPerLevel){
        this.maxFloors = maxFloors;
        this.spotsPerLevel = spotsPerLevel;

        pq = new PriorityQueue<>((a,b) -> {
            if(a.floor == b.floor)
                return a.spot-b.spot;
            else
                return a.floor-b.floor;
        });
    }


    public ParkingSpot park(){

            if(pq.isEmpty())
                throw new IllegalStateException("No available parking spots");
            ParkingSpot ps = pq.poll();
            return ps;
        
    }

    public void unpark(int floor, int spot){
            pq.add(new ParkingSpot(floor, spot));
    }

    public ParkingSpot getNextAvailable(){
        if(pq.isEmpty())
            throw new IllegalStateException("Parking Lot is full");
        return pq.peek();
    }

    public void addParkingSpot(int floor,int spot){
        if(floor > maxFloors)
            throw new IllegalStateException("Floor Input greater than max floors allowed");
        if(spot > spotsPerLevel){
            throw new IllegalStateException("Spot Input greater than max spots allowed");
        }
        pq.add(new ParkingSpot(floor,spot));
    }

    class ParkingSpot{
        int floor,spot;
        public ParkingSpot(int floor,int spot){
            this.floor = floor;
            this.spot = spot;
        }
        public int getFloor(){
            return this.floor;
        }
        public int getSpot(){
            return this.spot;
        }

    }

    public static void main(String[] args){
        ParkingLot pl = new ParkingLot(5, 10);
        pl.addParkingSpot(1, 1);
        pl.addParkingSpot(2, 1);
        pl.addParkingSpot(3, 1);
        pl.addParkingSpot(1, 2);
        pl.addParkingSpot(2, 2);
        pl.addParkingSpot(3, 2);
        pl.park();
		pl.park();
		pl.park();
		ParkingSpot ps = pl.getNextAvailable();
		System.out.println("Next available location is-> Floor: " + ps.getFloor() + " Spot: " + ps.getSpot()); //2,2
		pl.unpark(1,1);
		ParkingSpot ps1 = pl.getNextAvailable();
		System.out.println("Next available location is-> Floor: " + ps1.getFloor() + " Spot: " + ps1.getSpot());//1,1


    }
}

