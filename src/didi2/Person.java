package didi2;

public class Person implements Comparable<Person>{
    private String sj;
    private int id;

    public Person(String sj, int id) {
        this.sj = sj;
        this.id = id;
    }

    public String getSj() {
        return sj;
    }

    public void setSj(String sj) {
        this.sj = sj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Person o) {
        return this.getSj().compareTo(o.getSj());
    }

    @Override
    public String toString() {
        return "Person{" +
                "sj='" + sj + '\'' +
                ", id=" + id +
                '}';
    }
}
