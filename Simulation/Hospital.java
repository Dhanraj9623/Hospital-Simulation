import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import javax.swing.*;
import java.awt.event.*;
class Hospital{
    Gui gui = new Gui();
    Random random = new Random();
    PriorityQueue<Patient> patientQueue = new PriorityQueue<>((p1, p2) -> {
        if(p1.getArrivalTime() == p2.getArrivalTime()){
            return Integer.compare(p2.getPriority(), p1.getPriority());
        }
        return Integer.compare(p1.getArrivalTime(), p2.getArrivalTime());
    });

    Doctor[] doctors;
    Bed[] beds;
    Staff[] staffs;
    WaitingArea waitingArea;
    int totalTreatmentTime,services;

    public Hospital(){
        gui.create();
    }
    void buttonRun(){
        JButton button = gui.getButton();
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                gui.setField1Text("Running");
                button.setEnabled(false);
                SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
                    @Override
                    protected Void doInBackground() throws Exception{
                        int totalIterations = 201;
                        for (int i = 0; i <= totalIterations; i++){
                            int progress;
                            if (i <= totalIterations / 2){
                                progress = 2 * i;
                            }
                            else{
                                progress = 2 * totalIterations - 2 * i;
                            }
                            publish(progress);
                            Thread.sleep(5);
                        }
                        return null;
                    }
                    @Override
                    protected void process(java.util.List<Integer> chunks){
                        int progress = chunks.get(chunks.size() - 1);
                        gui.setProgressBarValue(progress);
                    }
                    @Override
                    protected void done() {
                        button.setEnabled(true);
                        gui.setField1Text("Finished");
                    }
                };
                worker.execute();
                int totalWaitTime = 0,emergencyVisits = 0, overflow = 0,totalOccupy=0;
                int numDoctors = Integer.parseInt(gui.getTextField8());
                int numPatients = Integer.parseInt(gui.getTextField6());
                int numBeds = Integer.parseInt(gui.getTextField7());
                int numStaffs = Integer.parseInt(gui.getTextField9());
                int numCapacity = Integer.parseInt(gui.getTextField10());
                int simulationDuration = Integer.parseInt(gui.getTextField11());
                doctors = new Doctor[numDoctors];
                beds = new Bed[numBeds];
                staffs = new Staff[numStaffs];
                waitingArea = new WaitingArea(numCapacity);
                for(int i = 0; i < numBeds; i++){
                    beds[i] = new Bed(i,true);
                }
                for(int i = 0; i < numDoctors; i++){
                    Doctor doctor = new Doctor();
                    doctors[i] = new Doctor(doctor.generateDocName(), doctor.generateAvailability());
                }
                for(int i = 0; i <numPatients; i++){
                    Patient patient = new Patient();
                    patientQueue.add(new Patient(patient.generateID(), patient.generatePriority(), patient.generateName(),patient.generateArrivalTime(simulationDuration)));
                }
                for(int i=0;i<numStaffs;i++){
                    Staff staff = new Staff();
                    staffs[i] = new Staff(true);
                }
                for(int i = 0;i<simulationDuration;i++){
                    System.out.print("===============");
                    System.out.print(" HOUR "+i+"==============\n");
                    while(!patientQueue.isEmpty()&&patientQueue.peek().getArrivalTime()==i){
                        Patient x = patientQueue.poll();
                        if(x.getPriority()==3){
                            emergencyVisits+=1;
                        }
                        waitingArea.addPatient(x);
                        if (x.getArrivalTime()==i+1){
                            break;
                        }
                    }
                    while(!waitingArea.isEmpty()){
                        Patient y ,z;
                        y = waitingArea.checkNextPatient();
                        long waitingTime = i - y.getArrivalTime();
                        totalWaitTime+=waitingTime;
                        if(checkResources(y,numDoctors,numBeds,numStaffs)){
                            z= waitingArea.getNextPatient();
                            assignResources(z,numDoctors,numBeds,numStaffs);
                        }
                        else{
                            System.out.println("Patient is waiting in waiting area because");
                            System.out.println("Resources unavailable at the moment. Please wait for them to become available.");
                            break;
                        }
                        //how to break
                    }
                    for(int k =0 ; k<numDoctors;k++){
                        if(doctors[k].getBusyDuration()>=1){
                            doctors[k].decreaseBusyDuration(1);
                        }
                        if(doctors[k].getBusyDuration()== 0){
                            doctors[k].setAvailable(true);
                        }
                    }
                    for(int j =0;j<numBeds;j++){
                        if(beds[j].getOccupyDuration()>=1){
                            beds[j].decreaseOccupyDuration(1);
                        }
                        if(beds[j].getOccupyDuration()==0){
                            beds[j].setAvailable(true);
                        }
                    }
                    for(int m =0;m<numStaffs;m++){
                        if(staffs[m].getBusyDuration()>=1){
                            staffs[m].decreaseBusyDuration(1);
                        }
                        if(staffs[m].getBusyDuration()== 0){
                            staffs[m].setAvailable(true);
                        }
                    }
                    for(int n =0;n<numBeds;n++){
                        if(!beds[n].getIsAvailable()){
                            totalOccupy+=1;
                        }
                    }
                }

                while(!waitingArea.isEmpty()){
                    waitingArea.getNextPatient();
                    overflow+=1;
                }
                double averageOccupancy = (double) totalOccupy/simulationDuration;
                float averageWaitingTime = (float) totalWaitTime / numPatients;
                double efficiency = (((double) numPatients / simulationDuration) * ((double) totalTreatmentTime / numPatients) / (numDoctors + numBeds + numStaffs))*100;

                gui.setField2Text(Float.toString(averageWaitingTime)+" hours");
                gui.setField3Text(Integer.toString(overflow));
                gui.setField4Text(Integer.toString(emergencyVisits));
                gui.setField5Text(Double.toString(averageOccupancy));
                gui.setField12Text(Double.toString(efficiency)+" %");
                gui.setField1Text("Finished");
                totalTreatmentTime=0;
            }
        });
    }
    boolean checkResources(Patient patient, int numDoctors, int numBeds, int numStaffs){
        boolean doctorsAvailable = false;
        boolean bedsAvailable = false;
        boolean staffsAvailable = false;
        for(int i = 0; i < numDoctors; i++){
            if (doctors[i].getBusyDuration() == 0 && doctors[i].getIsAvailable()) {
                doctorsAvailable = true;
                break;
            }
        }
        for (int j = 0; j < numBeds; j++){
            if (beds[j].getOccupyDuration() == 0 && beds[j].getIsAvailable()) {
                bedsAvailable = true;
                break;
            }
        }
        for(int k = 0; k < numStaffs; k++){
            if (staffs[k].getBusyDuration() == 0 && staffs[k].getIsAvailable()) {
                staffsAvailable = true;
                break;
            }
        }
        return doctorsAvailable && staffsAvailable && bedsAvailable;
    }
    void assignResources(Patient patient, int numDoctors, int numBeds, int numStaffs) {
        int duration;
        int x = patient.getPriority();
        boolean doctorsAvailable = false;
        if (x == 1){
            duration = 1;
        }else if(x == 2){
            duration = random.nextInt(4) + 1;
        }else{
            duration = random.nextInt(7) + 1;
        }
        int assignedDoctorIndex = -1;
        int assignedBedIndex = -1;
        int assignedStaffIndex = -1;
        totalTreatmentTime+= duration;
        patient.setTreatmentTime(duration);

        for (int i = 0; i < numDoctors; i++){
            if (doctors[i].getBusyDuration() == 0 && doctors[i].getIsAvailable()){
                assignedDoctorIndex = i;
                break;
            }
        }
        for (int j = 0; j < numBeds; j++){
            if (beds[j].getOccupyDuration() == 0 && beds[j].getIsAvailable()){
                assignedBedIndex = j;
                break;
            }
        }
        for(int k = 0; k < numStaffs; k++){
            if (staffs[k].getBusyDuration()== 0 && staffs[k].getIsAvailable()) {
                assignedStaffIndex = k;
                break;
            }
        }
        if (assignedDoctorIndex != -1 && assignedBedIndex != -1 && assignedStaffIndex != -1) {
            doctors[assignedDoctorIndex].setBusyDuration(duration);
            doctors[assignedDoctorIndex].setAvailable(false);

            beds[assignedBedIndex].setOccupyDuration(duration);
            beds[assignedBedIndex].setAvailable(false);

            staffs[assignedStaffIndex].setBusyDuration(duration);
            staffs[assignedStaffIndex].setAvailable(false);

            // SQL sql = new SQL();
            // sql.insertPatientsData(patient);
        }
    }

    public static void main(String[] args){
        Hospital hospital = new Hospital();
        hospital.buttonRun();
    }
}