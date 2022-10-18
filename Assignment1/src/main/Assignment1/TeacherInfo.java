package Assignment1;

public class TeacherInfo {

    private int id;
    private String name;

    private String sexual;//male for 1 and female for 0
    private String job;
    private String direction;

    public String getDirection() {
        return direction;
    }

    public String getName() {
        return name;
    }

    public String getSexual(){
        return sexual;
    }

    public String getJob(){
        return job;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSexual(String sexual) {
        this.sexual = sexual;
    }
}
