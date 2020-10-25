package model;

public class GameStore {
	private int levelId;
    private int lightWeaponLimit;
    private int heavyWeaponLimit;
    private int fastWeaponLimit;
    private int limit;

    public GameStore(){

    }

    public GameStore(int levelId, int lightWeaponLimit, int heavyWeaponLimit, int fastWeaponLimit, int limit){
		this.levelId = levelId;
		this.lightWeaponLimit = lightWeaponLimit;
		this.heavyWeaponLimit = heavyWeaponLimit;
		this.fastWeaponLimit = fastWeaponLimit;
		this.limit = limit;
    }

    public GameStore(int lightWeaponLimit, int heavyWeaponLimit, int fastWeaponLimit){
		this.lightWeaponLimit = lightWeaponLimit;
		this.heavyWeaponLimit = heavyWeaponLimit;
		this.fastWeaponLimit = fastWeaponLimit;
    }

    public int getLevelId() {
        return this.levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getLightWeaponLimit() {
        return this.lightWeaponLimit;
    }

    public void setLightWeaponLimit(int lightWeaponLimit) {
        this.lightWeaponLimit = lightWeaponLimit;
    }

    public int getHeavyWeaponLimit() {
        return this.heavyWeaponLimit;
    }

    public void setHeavyWeaponLimit(int heavyWeaponLimit) {
        this.heavyWeaponLimit = heavyWeaponLimit;
    }

    public int getFastWeaponLimit() {
        return this.fastWeaponLimit;
    }

    public void setFastWeaponLimit(int fastWeaponLimit) {
        this.fastWeaponLimit = fastWeaponLimit;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}