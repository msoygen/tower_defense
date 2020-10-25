package model;

public class LevelRead extends XMLRead{
	private GameLevel level = null;

	public LevelRead() {
		this.inputStream = LevelRead.class.getResourceAsStream("/Level.xml");
	}

	public GameLevel XmlReader(int levelCount) {
		this.initXML("level");
		try {
			for (int i = 0; i < this.l.getLength(); i++) {
				// print attributes
				if (i == levelCount) {
					level = new GameLevel();

					level.setZombieCount(Integer.parseInt(root.getElementsByTagName("zombieCount").item(i).getTextContent()));
					level.setNormalZombieCount(
							Integer.parseInt(root.getElementsByTagName("normalZombieCount").item(i).getTextContent()));
					level.setFastZombieCount(
							Integer.parseInt(root.getElementsByTagName("fastZombieCount").item(i).getTextContent()));
					level.setSlowZombie(Integer.parseInt(root.getElementsByTagName("slowZombie").item(i).getTextContent()));
					level.setBossZombie(Integer.parseInt(root.getElementsByTagName("bossZombie").item(i).getTextContent()));
					level.setReward(Integer.parseInt(root.getElementsByTagName("reward").item(i).getTextContent()));
					level.setMap(setMap(root.getElementsByTagName("map").item(i).getTextContent()));

					return level;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public int[][] setMap(String i) {
		int mapInt[][] = new int[12][16];
		int start = 0;
		int end = 16;
		for (int j = 0; j < 12; j++) {
			String row = i.substring(start, end);
			start = end;
			end = end + 16;
			for (int k = 0; k < 16; k++) {
				String block = String.valueOf(row.charAt(k));
				mapInt[j][k] = Integer.parseInt(block);
			}
		}

		return mapInt;
	}
}