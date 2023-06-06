class Bed{
    int id;
    boolean isAvailable;
    int occupyDuration;
    public Bed(){

    }
    public Bed(int id,boolean isAvailable){
        this.id = id;
        this.isAvailable =isAvailable;
        this.occupyDuration = 0;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
    int getOccupyDuration(){
        return occupyDuration;
    }
    void setOccupyDuration(int occupyDuration){
        this.occupyDuration = occupyDuration;;
    }
    void decreaseOccupyDuration(int duration){
        this.occupyDuration -= duration;
    }
}