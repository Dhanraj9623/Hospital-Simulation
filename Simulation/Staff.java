class Staff {
    int busyDuration;
    boolean isAvailable;
    public Staff(){

    }
    public Staff(boolean isAvailable) {
        this.isAvailable = isAvailable;
        this.busyDuration = 0;
    }

    int getBusyDuration(){
        return busyDuration;
    }
    boolean getIsAvailable(){
        return isAvailable;
    }

    void setBusyDuration(int busyDuration){
        this.busyDuration = busyDuration;
    }

    void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }
    void decreaseBusyDuration(int busyDuration){
        this.busyDuration -= busyDuration;
    }
}