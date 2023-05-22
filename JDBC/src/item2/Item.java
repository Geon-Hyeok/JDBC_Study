package item2;

import java.awt.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Item extends JFrame {
	private ItemDAOImpl impl;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextArea textArea;
	private JLabel imageLabel;

	private Map<String, String> imageMap;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Item frame = new Item();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void displayImage(String imageName) {
		if (imageName == null || imageName.isEmpty()) {
			// 이미지를 지우는 처리
			imageLabel.setIcon(null);
		} else {
			// 이미지 파일의 경로를 가져옵니다.
			String imagePath = null;
			for (Map.Entry<String, String> entry : imageMap.entrySet()) {
				if (entry.getKey().equalsIgnoreCase(imageName)) {
					imagePath = entry.getValue();
					break;
				}
			}

			if (imagePath != null) {
				// 이미지 파일을 로딩하여 표시합니다.
				try {
					BufferedImage image = ImageIO.read(new File(imagePath));
					Image scaledImage = image.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(),
							Image.SCALE_SMOOTH);
					ImageIcon imageIcon = new ImageIcon(scaledImage);
					imageLabel.setIcon(imageIcon);

				} catch (IOException e) {
					// 이미지 파일 로딩 중 예외 발생 시, 이미지를 지웁니다.
					imageLabel.setIcon(null);
					e.printStackTrace();
				}
			} else {
				// 이미지 경로가 없는 경우, 이미지를 지웁니다.
				imageLabel.setIcon(null);
			}
		}
	}

	public Item() {

		impl = new ItemDAOImpl();
		setTitle("Item Search Helper");
		setSize(800, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 509);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		imageLabel = new JLabel();
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setBounds(30, 167, 319, 240);
		contentPane.add(imageLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(386, 71, 447, 389);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textField);
		contentPane.setLayout(null);

		textField.setBounds(386, 28, 388, 27);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton searchButton = new JButton("검색");
		searchButton.setBackground(Color.LIGHT_GRAY);
		searchButton.setForeground(new Color(0, 0, 0));
		searchButton.setBounds(786, 30, 70, 23);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = textField.getText().replaceAll("\\s", "");
				// main 명령을 실행하고 결과값이 없을 때 sub 명령을 실행하기 위해 두 개의 변수를 사용
				MainItemDTO mainItem = null;
				SubItemDTO subItem = null;

				if (input != null && !input.equals("")) {
					mainItem = ItemDAOImpl.getDAO().selectItem(input);
					if (mainItem != null) {
						textArea.setText(mainItem.toString());
						displayImage(mainItem.getImageName());
					} else {
						subItem = ItemDAOImpl.getDAO().selectSubItem(input);
						if (subItem != null) {
							textArea.setText(subItem.toString());
							displayImage(subItem.getImageName());
						} else {
							// 결과값에 저장된 문자열과 입력값을 비교할 때 공백을 제거하여 비교
							boolean found = false;
							for (MainItemDTO item : impl.getDAO().selectAllItemList()) {
								String result = item.getName().replaceAll("\\s", ""); // 결과값에서 공백 제거
								if (result.equalsIgnoreCase(input)) {
									textArea.setText(item.toString());
									displayImage(item.getImageName());
									found = true;
									break;
								}
							}
							if (!found) {
								for (SubItemDTO item : impl.getDAO().selectAllSubItemList()) {
									String result = item.getName().replaceAll("\\s", ""); // 결과값에서 공백 제거
									if (result.equalsIgnoreCase(input)) {
										textArea.setText(item.toString());
										displayImage(item.getImageName());
										found = true;
										break;
									}
								}
							}
							if (!found) {
								JOptionPane.showMessageDialog(contentPane, "해당 아이템을 찾을 수 없습니다.");
								displayImage(null);
							}
						}
					}
				}
			}

		});
		contentPane.add(searchButton);
		// 이미지 파일의 경로를 저장할 맵 초기화
		imageMap = new HashMap<>();
		// 이미지 파일의 경로를 맵에 추가 (예시)
		imageMap.put("돌풍", "C:\\Users\\user\\Desktop\\Item\\돌풍.png");
		imageMap.put("무한의 대검", "C:\\Users\\user\\Desktop\\Item\\무한의 대검.png");
		imageMap.put("나보리 신속검", "C:\\Users\\user\\Desktop\\Item\\나보리 신속검.png");
		imageMap.put("월식", "C:\\Users\\user\\Desktop\\Item\\월식.jpg");
		imageMap.put("크라켄 학살자", "C:\\Users\\user\\Desktop\\Item\\크라켄 학살자.png");
		imageMap.put("철갑궁", "C:\\Users\\user\\Desktop\\Item\\철갑궁.jpg");
		imageMap.put("곡괭이", "C:\\Users\\user\\Desktop\\Item\\곡괭이.jpg");
		imageMap.put("톱날단검", "C:\\Users\\user\\Desktop\\Item\\톱날단검.jpg");
		imageMap.put("절정의 화살", "C:\\Users\\user\\Desktop\\Item\\절정의 화살.jpg");
		imageMap.put("콜필드의 전투 망치", "C:\\Users\\user\\Desktop\\Item\\콜필드의 전투망치.jpg");
		imageMap.put("민첩성의 망토", "C:\\Users\\user\\Desktop\\Item\\민첩성의 망토.jpg");
		imageMap.put("흡혈의 낫", "C:\\Users\\user\\Desktop\\Item\\흡혈의 낫.jpg");
		// 이미지 파일의 경로를 맵에 추가해주세요.

		// 이미지를 표시하거나 지우는 메서드

		JButton modifyButton = new JButton("수정");
		modifyButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String[] options = { "상위 아이템", "하위 아이템" };
				int choice = JOptionPane.showOptionDialog(contentPane, "수정할 아이템의 종류를 선택하세요.", "아이템 수정",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				if (choice == 0) { // 상위 아이템 선택
					JTextField nameField = new JTextField();
					JTextField priceField = new JTextField();
					JTextField performanceField = new JTextField();
					JTextField materialField = new JTextField();
					JTextField championField = new JTextField();

					Object[] fields = { "이름:", nameField, "가격:", priceField, "성능:", performanceField, "재료:",
							materialField, "챔피언:", championField };
					int result = JOptionPane.showConfirmDialog(contentPane, fields, "상위 아이템 수정",
							JOptionPane.OK_CANCEL_OPTION);

					if (result == JOptionPane.OK_OPTION) {
						String name = nameField.getText();
						int price = Integer.parseInt(priceField.getText());
						String performance = performanceField.getText();
						String material = materialField.getText();
						String champion = championField.getText();

						MainItemDTO mainItem = new MainItemDTO();
						mainItem.setName(name);
						mainItem.setPrice(price);
						mainItem.setPerformance(performance);
						mainItem.setMaterial(material);
						mainItem.setChampion(champion);

						int rowsUpdated = impl.updateItem(mainItem); // 수정된 부분: impl 객체의 updateItem 메소드 호출
						System.out.println(rowsUpdated + " 행이 수정되었습니다.");
						textArea.append(rowsUpdated + " 행이 수정되었습니다.\n");
						Timer timer = new Timer(10000, new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								textArea.setText("");
							}
						});
						timer.setRepeats(false);
						timer.start();
					}
				} else if (choice == 1) { // 하위 아이템 선택
					JTextField nameField = new JTextField();
					JTextField priceField = new JTextField();
					JTextField performanceField = new JTextField();
					JTextField mainItemField = new JTextField();
					JTextField championField = new JTextField();

					Object[] fields = { "이름:", nameField, "가격:", priceField, "성능:", performanceField, "상위 아이템:",
							mainItemField, "챔피언:", championField };
					int result = JOptionPane.showConfirmDialog(contentPane, fields, "하위 아이템 수정",
							JOptionPane.OK_CANCEL_OPTION);

					if (result == JOptionPane.OK_OPTION) {
						String name = nameField.getText();
						int price = Integer.parseInt(priceField.getText());
						String performance = performanceField.getText();
						String mainItem = mainItemField.getText();
						String champion = championField.getText();

						SubItemDTO sub = new SubItemDTO();
						sub.setName(name);
						sub.setPrice(price);
						sub.setPerformance(performance);
						sub.setMainitem(mainItem);
						sub.setChampion(champion);

						int rowsUpdated = impl.updateSubItem(sub); // 수정된 부분: impl 객체의 updateSubItem 메소드 호출
						System.out.println(rowsUpdated + " 행이 수정되었습니다.");
						textArea.append(rowsUpdated + " 행이 수정되었습니다.\n");
						Timer timer = new Timer(10000, new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								textArea.setText("");
							}
						});
						timer.setRepeats(false);
						timer.start();

					}
				}
			}
		});

		modifyButton.setBounds(132, 437, 97, 23);
		contentPane.add(modifyButton);

		JButton deleteButton = new JButton("삭제");
		deleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String[] options = { "상위 아이템", "하위 아이템" };
				int choice = JOptionPane.showOptionDialog(contentPane, "삭제할 아이템의 종류를 선택하세요.", "아이템 삭제",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				if (choice == 0) { // 상위 아이템 선택
					String itemName = JOptionPane.showInputDialog(contentPane, "삭제할 상위 아이템의 이름을 입력하세요:");

					if (itemName != null && !itemName.isEmpty()) {
						int rowsDeleted = impl.deleteItem(itemName); // 수정된 부분: impl 객체의 deleteItem 메소드 호출
						System.out.println(rowsDeleted + " 행이 삭제되었습니다.");
						textArea.append(rowsDeleted + " 행이 삭제되었습니다.\n");
						Timer timer = new Timer(10000, new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								textArea.setText("");
							}
						});
						timer.setRepeats(false);
						timer.start();
					}
				} else if (choice == 1) { // 하위 아이템 선택
					String itemName = JOptionPane.showInputDialog(contentPane, "삭제할 하위 아이템의 이름을 입력하세요:");

					if (itemName != null && !itemName.isEmpty()) {
						int rowsDeleted = impl.deleteSubItem(itemName); // 수정된 부분: impl 객체의 deleteSubItem 메소드 호출
						System.out.println(rowsDeleted + " 행이 삭제되었습니다.");
						textArea.append(rowsDeleted + " 행이 삭제되었습니다.\n");
						Timer timer = new Timer(10000, new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								textArea.setText("");
							}
						});
						timer.setRepeats(false);
						timer.start();
					}
				}
			}
		});
		deleteButton.setBounds(252, 437, 97, 23);
		contentPane.add(deleteButton);

		JButton addButton = new JButton("추가");
		addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String[] options = { "상위 아이템", "하위 아이템" };
				int choice = JOptionPane.showOptionDialog(contentPane, "추가할 아이템의 종류를 선택하세요.", "아이템 추가",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				if (choice == 0) { // 상위 아이템 선택
					JTextField nameField = new JTextField();
					JTextField priceField = new JTextField();
					JTextField performanceField = new JTextField();
					JTextField materialField = new JTextField();
					JTextField championField = new JTextField();

					Object[] fields = { "이름:", nameField, "가격:", priceField, "성능:", performanceField, "재료:",
							materialField, "챔피언:", championField };
					int result = JOptionPane.showConfirmDialog(contentPane, fields, "상위 아이템 추가",
							JOptionPane.OK_CANCEL_OPTION);

					if (result == JOptionPane.OK_OPTION) {
						String name = nameField.getText();
						int price = Integer.parseInt(priceField.getText());
						String performance = performanceField.getText();
						String material = materialField.getText();
						String champion = championField.getText();

						MainItemDTO mainItem = new MainItemDTO();
						mainItem.setName(name);
						mainItem.setPrice(price);
						mainItem.setPerformance(performance);
						mainItem.setMaterial(material);
						mainItem.setChampion(champion);

						int rowsInserted = impl.insertItem(mainItem); // 수정된 부분: impl 객체의 insertItem 메소드 호출
						System.out.println(rowsInserted + " 행이 삽입되었습니다.");
						textArea.append(rowsInserted + " 행이 삽입되었습니다.\n");
						Timer timer = new Timer(10000, new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								textArea.setText("");
							}
						});
						timer.setRepeats(false);
						timer.start();

					}
				} else if (choice == 1) { // 하위 아이템 선택
					JTextField nameField = new JTextField();
					JTextField priceField = new JTextField();
					JTextField performanceField = new JTextField();
					JTextField mainItemField = new JTextField();
					JTextField championField = new JTextField();

					Object[] fields = { "이름:", nameField, "가격:", priceField, "성능:", performanceField, "상위 아이템:",
							mainItemField, "챔피언:", championField };
					int result = JOptionPane.showConfirmDialog(contentPane, fields, "하위 아이템 추가",
							JOptionPane.OK_CANCEL_OPTION);

					if (result == JOptionPane.OK_OPTION) {
						String name = nameField.getText();
						int price = Integer.parseInt(priceField.getText());
						String performance = performanceField.getText();
						String mainItem = mainItemField.getText();
						String champion = championField.getText();

						SubItemDTO sub = new SubItemDTO();
						sub.setName(name);
						sub.setPrice(price);
						sub.setPerformance(performance);
						sub.setMainitem(mainItem);
						sub.setChampion(champion);

						int rowsInserted = impl.insertSubItem(sub); // 수정된 부분: impl 객체의 insertSubItem 메소드 호출
						System.out.println(rowsInserted + " 행이 삽입되었습니다.");
						textArea.append(rowsInserted + " 행이 삽입되었습니다.\n");
						Timer timer = new Timer(10000, new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								textArea.setText("");
							}
						});
						timer.setRepeats(false);
						timer.start();
					}
				}
			}
		});
		addButton.setBounds(12, 437, 97, 23);
		contentPane.add(addButton);

		JButton mainItemListButton = new JButton("상위 아이템 목록");
		mainItemListButton.setBounds(12, 30, 172, 23);
		mainItemListButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				List<MainItemDTO> mainItem = impl.getDAO().selectAllItemList();
				StringBuilder itemList = new StringBuilder();

				for (MainItemDTO item : mainItem) {
					itemList.append("이름: ").append(item.getName()).append("\n");
					itemList.append("가격: ").append(item.getPrice()).append("\n");
					itemList.append("성능: ").append(item.getPerformance()).append("\n");
					itemList.append("재료: ").append(item.getMaterial()).append("\n");
					itemList.append("챔피언: ").append(item.getChampion()).append("\n\n");
				}

				JOptionPane.showMessageDialog(contentPane, itemList.toString());
			}
		});
		contentPane.add(mainItemListButton);

		contentPane.add(mainItemListButton);
		contentPane.add(mainItemListButton);
		JButton subItemListButton = new JButton("하위 아이템 목록");
		subItemListButton.setBounds(196, 30, 172, 24);
		subItemListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder itemList = new StringBuilder();
				List<SubItemDTO> subItems = impl.getDAO().selectAllSubItemList();
				for (SubItemDTO subItem : subItems) {
					itemList.append("이름: ").append(subItem.getName()).append("\n");
					itemList.append("가격: ").append(subItem.getPrice()).append("\n");
					itemList.append("성능: ").append(subItem.getPerformance()).append("\n");
					itemList.append("재료: ").append(subItem.getMainitem()).append("\n");
					itemList.append("챔피언: ").append(subItem.getChampion()).append("\n");
					itemList.append("\n");
				}
				JOptionPane.showMessageDialog(contentPane, itemList.toString());
			}
		});
		contentPane.add(subItemListButton);

		JButton championButton = new JButton("아이템 사용 챔피언 검색");
		championButton.setBounds(12, 120, 172, 23);
		contentPane.add(championButton);

		championButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String championName = JOptionPane.showInputDialog(contentPane, "챔피언 이름을 입력하세요:");
				if (championName != null) {
					List<MainItemDTO> mainItems = impl.getDAO().selectNameItemList(championName);
					if (mainItems.isEmpty()) {
						JOptionPane.showMessageDialog(contentPane, "해당 챔피언이 사용하는 메인 아이템이 없습니다.");
					} else {
						StringBuilder itemNames = new StringBuilder();
						for (MainItemDTO mainItem : mainItems) {
							itemNames.append(mainItem.getName()).append("\n");
						}
						JOptionPane.showMessageDialog(contentPane, itemNames.toString());
					}
				}
			}
		});
		championButton.setBounds(12, 120, 172, 23);
		contentPane.add(championButton);

		JButton materialButton = new JButton("재료");
		materialButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = JOptionPane.showInputDialog(contentPane, "상위 아이템을 입력하세요:");
				if (itemName != null && !itemName.isEmpty()) {
					MainItemDTO mainItem = impl.findMaterial(itemName);
					if (mainItem != null) {
						JOptionPane.showMessageDialog(contentPane,
								"상위 아이템 " + itemName + "의 재료:\n" + mainItem.getMaterial());
					} else {
						JOptionPane.showMessageDialog(contentPane, "해당 아이템의 재료를 찾을 수 없습니다.");
					}
				}
			}
		});
		materialButton.setBounds(196, 120, 172, 23);
		contentPane.add(materialButton);

		JButton effectButton = new JButton("상위 아이템 효과");
		effectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Itemname = JOptionPane.showInputDialog(contentPane, "상위 아이템을 입력하세요:");
				if (Itemname != null && !Itemname.isEmpty()) {
					MainItemDTO mainItem = impl.selectItemPerformance(Itemname);
					if (mainItem != null) {
						JOptionPane.showMessageDialog(contentPane,
								"상위 아이템 " + Itemname + "의 효과:\n" + mainItem.getPerformance());
					} else {
						JOptionPane.showMessageDialog(contentPane, "해당 아이템의 효과를 찾을 수 없습니다.");
					}
				}
			}
		});
		effectButton.setBounds(12, 76, 172, 23);
		contentPane.add(effectButton);

		JButton priceButton = new JButton("하위 아이템 가격");
		priceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = JOptionPane.showInputDialog(contentPane, "하위 아이템을 입력하세요:");
				if (itemName != null && !itemName.isEmpty()) {
					SubItemDTO subItem = impl.selectPriceSubItemList(itemName);
					if (subItem != null) {
						JOptionPane.showMessageDialog(contentPane, itemName + "의 가격: " + subItem.getPrice());
					} else {
						JOptionPane.showMessageDialog(contentPane, "해당 아이템이 존재하지 않습니다.");
					}
				}
			}
		});
		priceButton.setBounds(196, 76, 172, 23);
		contentPane.add(priceButton);

	}
}