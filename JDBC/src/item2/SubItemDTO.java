package item2;

public class SubItemDTO {
	private String name;
	private int price;
	private String performance;
	private String mainitem;
	private String champion;

	public SubItemDTO() {
	}

	public SubItemDTO(String name, int price, String performance, String mainitem, String champion) {
		super();
		this.name = name;
		this.price = price;
		this.performance = performance;
		this.mainitem = mainitem;
		this.champion = champion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public String getMainitem() {
		return mainitem;
	}

	public void setMainitem(String mainitem) {
		this.mainitem = mainitem;
	}

	public String getChampion() {
		return champion;
	}

	public void setChampion(String champion) {
		this.champion = champion;
	}

	@Override
	public String toString() {
		return "아이템 이름 : " + name + '\n' + '\n' + "가격 : " + price + '\n' + '\n' + "효과 : " + performance + '\n' + '\n'
				+ "상위 아이템 : " + mainitem + '\n' + '\n' + "사용하는 챔피언 : " + champion;
	}

	public String getImageName() {
		return name;
	}

}