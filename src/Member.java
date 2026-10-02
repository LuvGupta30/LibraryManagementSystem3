public class Member extends User{

    public Member(int id, String userName){
        super(id, userName);
    }

    @Override
    public String toString(){
        return "ID: " + getID()+ " || " + "User Name: "+ getUserName();
    }
}