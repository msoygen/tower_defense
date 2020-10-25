package model;

public class GameLevel {
	private int levelId;
	private int zombieCount;
	private int normalZombieCount;
	private int fastZombieCount;
	private int slowZombie;
	private int bossZombie;
	private int reward;
	private int[][] map;

	public GameLevel(int levelId, int zombieCount, int normalZombieCount, int fastZombieCount, int slowZombie,
			int bossZombie, int reward, int map[][]) {
		this.levelId = levelId;
		this.zombieCount = zombieCount;
		this.normalZombieCount = normalZombieCount;
		this.fastZombieCount = fastZombieCount;
		this.slowZombie = slowZombie;
		this.bossZombie = bossZombie;
		this.reward = reward;
		this.map = map;
	}

	public GameLevel(){
		
	}

	public int getLevelId() {
		return this.levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getZombieCount() {
		return this.zombieCount;
	}

	public void setZombieCount(int zombieCount) {
		this.zombieCount = zombieCount;
	}

	public int getNormalZombieCount() {
		return this.normalZombieCount;
	}

	public void setNormalZombieCount(int normalZombieCount) {
		this.normalZombieCount = normalZombieCount;
	}

	public int getFastZombieCount() {
		return this.fastZombieCount;
	}

	public void setFastZombieCount(int fastZombieCount) {
		this.fastZombieCount = fastZombieCount;
	}

	public int getSlowZombie() {
		return this.slowZombie;
	}

	public void setSlowZombie(int slowZombie) {
		this.slowZombie = slowZombie;
	}

	public int getBossZombie() {
		return this.bossZombie;
	}

	public void setBossZombie(int bossZombie) {
		this.bossZombie = bossZombie;
	}

	public int getReward() {
		return this.reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public int[][] getMap() {
		return this.map;
	}

	public void setMap(int map[][]) {
		this.map = map;
	}
}