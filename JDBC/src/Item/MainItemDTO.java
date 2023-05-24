package Item;

public class MainItemDTO {

	private String name;
	private int price;
	private String performance;
	private String material;
	private String champion;

	public MainItemDTO() {
		// TODO Auto-generated constructor stub
	}

	public MainItemDTO(String name, int price, String performance, String material, String champion) {
		super();
		this.name = name;
		this.price = price;
		this.performance = performance;
		this.material = material;
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

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getChampion() {
		return champion;
	}

	public void setChampion(String champion) {
		this.champion = champion;
	}
// ############ 스트링 구현 필요 #################
	}
