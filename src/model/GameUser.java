package model;

public class GameUser {
    public String username;
    public int gold;
    
    public GameUser(String username, int gold){
        this.username = username;
        this.gold = gold;
    }

    public int getGold(){
        return this.gold;
    }

    public void setGold(int gold){
        this.gold = gold;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

	@Override
	public String toString() {
		return this.username + " " + this.gold;
	}
}