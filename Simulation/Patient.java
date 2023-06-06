import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Patient {
    int id;
    int priority;
    String name;
    int arrivalTime;
    int treatmentTime;
    int dischargeTime;
    int simulationDuration;
    Random random = new Random();
    public Patient(){

    }
    public Patient(int id, int priority, String name,int arrivalTime) {
        this.id = id;
        this.priority = priority;
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.treatmentTime =0;
    }
    int getId(){
        return id;
    }
    void setId(int id){
        this.id = id;
    }
    int getPriority(){
        return priority;
    }

    void setPriority(int priority){
        this.priority = priority;
    }
    String getName(){
        return name;
    }
    void setName(String name){
        this.name = name;
    }
    int getArrivalTime(){
        return arrivalTime;
    }
    void setArrivalTime(int arrivalTime){
        this.arrivalTime = arrivalTime;
    }
    int getTreatmentTime(){
        return treatmentTime;
    }
    void setTreatmentTime(int treatmentTime){
        this.treatmentTime = treatmentTime;
    }
    int getDischargeTime(){
        return dischargeTime;
    }
    void setDischargeTime(int dischargeTime){
        this.dischargeTime = dischargeTime;
    }
    int generateID() {
        
        return random.nextInt(100000);
    }

    int generatePriority() {
        
        int randomNumber = random.nextInt(100) + 1; 
        
        if (randomNumber <= 50) {
            return 1; // 50% chance for priority 1
        } else if (randomNumber <= 80) {
            return 2; // 30% chance for priority 2
        } else {
            return 3; // 20% chance for priority 3
        }
    }

    String generateName() {
        String[] names = {"John", "Mary", "David", "Emma", "James"};
        
        int index = random.nextInt(names.length);
        return names[index];
    }
    int generateArrivalTime(int simulationDuration){
        return random.nextInt(0,simulationDuration);
    }


    // int generateDepartureTime(){

    // }

}

