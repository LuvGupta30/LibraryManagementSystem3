public class User {
    private final int id;
    private final String userName;

    public User(int id, String userName){
        this.id = id;
        this.userName = userName;
    }

    public int getID(){
        return this.id;
    }

    public String getUserName(){
        return this.userName;
    }
}