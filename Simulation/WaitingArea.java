import java.util.LinkedList;
import java.util.Queue;
class WaitingArea {
    private int capacity;
    private Queue<Patient> patientQueue;

    public WaitingArea(int capacity) {
        this.capacity = capacity;
        this.patientQueue = new LinkedList<>();
    }

    public boolean isFull() {
        return patientQueue.size() >= capacity;
    }
    boolean isEmpty(){
        return patientQueue.isEmpty();
    }
    public void addPatient(Patient patient) {
        if (!isFull()) {
            patientQueue.add(patient);
            System.out.println("Patient added to the waiting area.");
        } else {
            System.out.println("Waiting area is full. Cannot add more patients.");
        }
    }
    Patient checkNextPatient(){
        return patientQueue.peek();
    }
    Patient getNextPatient(){
        return patientQueue.poll();
    }

}
