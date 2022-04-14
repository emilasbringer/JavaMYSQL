public class meep {
    private int id;
    private String body;
    private String createdAt;
    private String updatedAt;
    private int userId;

    public meep(int id, String body, String createdAt, String updatedAt, int userId) {
        this.id = id;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }

    public meep (meep meepObject) {
        this.id = meepObject.id;
        this.body = meepObject.body;
        this.createdAt = meepObject.createdAt;
        this.updatedAt = meepObject.updatedAt;
        this.userId = meepObject.userId;
    }

    public String toString() {
        String output = "";
        output += body + " ";
        return output;
    }

    public int getId() {return id;}
}
