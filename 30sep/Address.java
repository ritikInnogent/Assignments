public class Address {
    static int counter = 1;
    int id;
    String pinCode;
    String city;
    int studentId;

    public Address(String pinCode, String city, int studentId) {
        this.id = counter++;
        this.pinCode = pinCode;
        this.city = city;
        this.studentId = studentId;
    }

    public String toString() {
        return String.format("Address{id=%d, pinCode='%s', city='%s', studentId=%d}", id, pinCode, city, studentId);
    }
}

