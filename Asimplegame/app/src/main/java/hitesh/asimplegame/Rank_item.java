package hitesh.asimplegame;

public class Rank_item {
    private int rank;
    private int grade;
    private String id;



    public Rank_item(int rank, int grade, String id) {
        this.rank=rank;
        this.grade = grade;
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade= grade;
    }

}
