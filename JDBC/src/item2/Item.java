package item2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;

public class Item extends JFrame {
   private ItemDAOImpl impl;
   private JPanel contentPane;
   private JTextField textField;
   private JTable table;

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
      searchButton.setBounds(786, 30, 57, 23);
      contentPane.add(searchButton);

      JButton modifyButton = new JButton("수정");
      modifyButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
             String[] options = { "상위 아이템", "하위 아이템" };
             int choice = JOptionPane.showOptionDialog(contentPane, "수정할 아이템의 종류를 선택하세요.", "아이템 수정", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
             
             if (choice == 0) { // 상위 아이템 선택
                JTextField nameField = new JTextField();
                JTextField priceField = new JTextField();
                JTextField performanceField = new JTextField();
                JTextField materialField = new JTextField();
                JTextField championField = new JTextField();
                
                Object[] fields = { "이름:", nameField, "가격:", priceField, "성능:", performanceField, "재료:", materialField, "챔피언:", championField };
                int result = JOptionPane.showConfirmDialog(contentPane, fields, "상위 아이템 수정", JOptionPane.OK_CANCEL_OPTION);
                
                if (result == JOptionPane.OK_OPTION) {
                   String name = nameField.getText();
                   int price = Integer.parseInt(priceField.getText());
                   String performance = performanceField.getText();
                   String material = materialField.getText();
                   String champion = championField.getText();
                   
                   
                   System.out.println("상위 아이템 수정:");
                   System.out.println("이름: " + name);
                   System.out.println("가격: " + price);
                   System.out.println("성능: " + performance);
                   System.out.println("재료: " + material);
                   System.out.println("챔피언: " + champion);
                }
             } else if (choice == 1) { // 하위 아이템 선택
                JTextField nameField = new JTextField();
                JTextField priceField = new JTextField();
                JTextField performanceField = new JTextField();
                JTextField mainItemField = new JTextField();
                JTextField championField = new JTextField();
                
                Object[] fields = { "이름:", nameField, "가격:", priceField, "성능:", performanceField, "상위 아이템:", mainItemField, "챔피언:", championField };
                int result = JOptionPane.showConfirmDialog(contentPane, fields, "하위 아이템 수정", JOptionPane.OK_CANCEL_OPTION);
                
                if (result == JOptionPane.OK_OPTION) {
                   String name = nameField.getText();
                   int price = Integer.parseInt(priceField.getText());
                   String performance = performanceField.getText();
                   String mainItem = mainItemField.getText();
                   String champion = championField.getText();
                   
                  
                   System.out.println("하위 아이템 수정:");
                   System.out.println("이름: " + name);
                   System.out.println("가격: " + price);
                   System.out.println("성능: " + performance);
                   System.out.println("상위 아이템: " + mainItem);
                   System.out.println("챔피언: " + champion);
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
             int choice = JOptionPane.showOptionDialog(contentPane, "삭제할 아이템의 종류를 선택하세요.", "아이템 삭제", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
             
             if (choice == 0) { // 상위 아이템 선택
                String itemName = JOptionPane.showInputDialog(contentPane, "삭제할 상위 아이템의 이름을 입력하세요:");
                
                if (itemName != null && !itemName.isEmpty()) {
                  
                   System.out.println("상위 아이템 삭제:");
                   System.out.println("이름: " + itemName);
                }
             } else if (choice == 1) { // 하위 아이템 선택
                String itemName = JOptionPane.showInputDialog(contentPane, "삭제할 하위 아이템의 이름을 입력하세요:");
                
                if (itemName != null && !itemName.isEmpty()) {
                   
                   System.out.println("하위 아이템 삭제:");
                   System.out.println("이름: " + itemName);
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
             int choice = JOptionPane.showOptionDialog(contentPane, "추가할 아이템의 종류를 선택하세요.", "아이템 추가", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
             
             if (choice == 0) { // 상위 아이템 선택
                JTextField nameField = new JTextField();
                JTextField priceField = new JTextField();
                JTextField performanceField = new JTextField();
                JTextField materialField = new JTextField();
                JTextField championField = new JTextField();
                
                Object[] fields = { "이름:", nameField, "가격:", priceField, "성능:", performanceField, "재료:", materialField, "챔피언:", championField };
                int result = JOptionPane.showConfirmDialog(contentPane, fields, "상위 아이템 추가", JOptionPane.OK_CANCEL_OPTION);
                
                if (result == JOptionPane.OK_OPTION) {
                   String name = nameField.getText();
                   int price = Integer.parseInt(priceField.getText());
                   String performance = performanceField.getText();
                   String material = materialField.getText();
                   String champion = championField.getText();
                   
                
                   System.out.println("상위 아이템 추가:");
                   System.out.println("이름: " + name);
                   System.out.println("가격: " + price);
                   System.out.println("성능: " + performance);
                   System.out.println("재료: " + material);
                   System.out.println("챔피언: " + champion);
                }
             } else if (choice == 1) { // 하위 아이템 선택
                JTextField nameField = new JTextField();
                JTextField priceField = new JTextField();
                JTextField performanceField = new JTextField();
                JTextField mainItemField = new JTextField();
                JTextField championField = new JTextField();
                
                Object[] fields = { "이름:", nameField, "가격:", priceField, "성능:", performanceField, "상위 아이템:", mainItemField, "챔피언:", championField };
                int result = JOptionPane.showConfirmDialog(contentPane, fields, "하위 아이템 추가", JOptionPane.OK_CANCEL_OPTION);
                
                if (result == JOptionPane.OK_OPTION) {
                   String name = nameField.getText();
                   int price = Integer.parseInt(priceField.getText());
                   String performance = performanceField.getText();
                   String mainItem = mainItemField.getText();
                   String champion = championField.getText();
                   
                   
                   System.out.println("하위 아이템 추가:");
                   System.out.println("이름: " + name);
                   System.out.println("가격: " + price);
                   System.out.println("성능: " + performance);
                   System.out.println("상위 아이템: " + mainItem);
                   System.out.println("챔피언: " + champion);
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

            JTextArea textArea = new JTextArea(itemList.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            JOptionPane.showMessageDialog(contentPane, scrollPane, "상위 아이템 목록", JOptionPane.INFORMATION_MESSAGE);
         }
      });
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
             String itemName = JOptionPane.showInputDialog(contentPane, "검색어를 입력하세요:");
             if (itemName != null && !itemName.isEmpty()) {
                MainItemDTO mainItem = impl.findMaterial(itemName);
                if (mainItem != null) {
                   JOptionPane.showMessageDialog(contentPane, "상위 아이템 " + itemName + "의 재료:\n" + mainItem.getMaterial());
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
             String Itemname = JOptionPane.showInputDialog(contentPane, "검색어를 입력하세요:");
             if (Itemname != null && !Itemname.isEmpty()) {
                MainItemDTO mainItem = impl.selectItemPerformance(Itemname);
                if (mainItem != null) {
                   JOptionPane.showMessageDialog(contentPane, "상위 아이템 " + Itemname + "의 효과:\n" + mainItem.getPerformance());
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
             String itemName = JOptionPane.showInputDialog(contentPane, "검색어를 입력하세요:");
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
      
      JTextArea textArea = new JTextArea();
      textArea.setWrapStyleWord(true);
      textArea.setLineWrap(true);
      textArea.setBounds(386, 65, 457, 362);
      contentPane.add(textArea);
   }
}