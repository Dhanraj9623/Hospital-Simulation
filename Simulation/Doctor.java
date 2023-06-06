import java.util.Random;
public class Doctor{
    String name;
    boolean isAvailable;
    int busyDuration;
    public Doctor(){

    }
    public Doctor(String name,boolean isAvailable){
        this.name = name;
        this.isAvailable = isAvailable;
        this.busyDuration =0;
    }
    void setName(String name) {
        this.name = name;
    }
    String getName(){
        return name;
    }
    boolean getIsAvailable(){
        return isAvailable;
    }
    void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    String generateDocName() {
        String[] names = { "Dr. Smith", "Dr. Johnson", "Dr. Williams", "Dr. Brown", "Dr. Jones" };
        Random random = new Random();
        int index = random.nextInt(names.length);
        return names[index];
    }
    boolean generateAvailability() {
        Random random = new Random();
        return random.nextBoolean();
    }
    int getBusyDuration(){
        return busyDuration;
    }
    void setBusyDuration(int busyDuration){
        this.busyDuration = busyDuration;
    }
    void decreaseBusyDuration(int duration){
        this.busyDuration-=duration;
    }
}