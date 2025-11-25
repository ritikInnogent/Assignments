public class ClassRoom {
    static int counter = 1; // Unique id banane ke liye static counter
    int id;
    String name;

    public ClassRoom(String name) {
        this.id = counter++;
        this.name = name;
    }

    public String toString() {
        return String.format("ClassRoom{id=%d, name='%s'}", id, name);
    }
}

