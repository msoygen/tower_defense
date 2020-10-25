package model;

public class ShopRead extends XMLRead{

	public ShopRead() {;
		this.inputStream = ShopRead.class.getResourceAsStream("/Shop.xml");
	}

	public GameStore XmlReader(int level) {
		this.initXML("level");
		try {
			for (int i = 0; i < this.l.getLength(); i++) {
				// print attributes
				if (i == level) {
					// Set process
					int lightWeaponLimit = (Integer
							.parseInt(this.root.getElementsByTagName("lightWeaponLimit").item(i).getTextContent()));
					int fastWeaponLimit = (Integer
							.parseInt(this.root.getElementsByTagName("fastWeaponLimit").item(i).getTextContent()));
					int heavyWeaponLimit = (Integer
							.parseInt(this.root.getElementsByTagName("heavyWeaponLimit").item(i).getTextContent()));
					GameStore store = new GameStore(lightWeaponLimit, heavyWeaponLimit, fastWeaponLimit);

					return store;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
