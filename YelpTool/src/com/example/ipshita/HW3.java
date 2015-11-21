package com.example.ipshita;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.example.ipshita.bean.BusinessBean;
import com.example.ipshita.bean.ReviewBean;
import com.example.ipshita.util.ConnectionFactory;
import com.example.ipshita.util.DatabaseUtil;

public class HW3 {
	private static Connection connection;
	private static String[] mainCategories;
	private static String[] subCategories;
	private static String[] attributes;

	private static  ArrayList<BusinessBean> businessValueList;
	public static void main(String[] args) {
		JFrame appFrame = new JFrame();
		appFrame.setLayout(new GridBagLayout());
		GridBagConstraints gridBagContraints = new GridBagConstraints();
		DefaultListModel defaultListModel;
		appFrame.setTitle("Yelp Search Tool");
		populateLists();
		String[] daysOfTheWeekArr = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		String[] timeSlots = { "00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30",
				"05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00",
				"10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
				"16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00",
				"21:30", "22:00", "22:30", "23:00", "23:30", "00:00", "00:30", "01:00", "01:30", "02:00", "02:30",
				"03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00",
				"08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
				"14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00",
				"19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30" };
		String[] allOrAnyAttributes = { "All Attributes", "Any Attribute" };
		businessValueList = new ArrayList<>();
		gridBagContraints.weightx = 2;
		gridBagContraints.weighty = 1;
		gridBagContraints.gridwidth = 2;
		gridBagContraints.fill = GridBagConstraints.BOTH;
		gridBagContraints.insets = new Insets(5, 5, 5, 5);

		defaultListModel = new DefaultListModel();
		JList<CheckListItem> mainCategoryList = new JList<CheckListItem>(defaultListModel);
		mainCategoryList.setCellRenderer(new CheckListRenderer());
		for (int i = 0; i < mainCategories.length; i++) {
			defaultListModel.addElement(new CheckListItem(i + ". " + mainCategories[i]));
		}
		mainCategoryList.setBackground(Color.ORANGE);
		JScrollPane mainCatPanel = new JScrollPane(mainCategoryList);
		mainCatPanel.setPreferredSize(new Dimension(450, 110));
		mainCategoryList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList eventList = (JList) mouseEvent.getSource();
				// Get index of item clicked
				int index = eventList.locationToIndex(mouseEvent.getPoint());
				CheckListItem checkListItem = (CheckListItem) eventList.getModel().getElementAt(index);
				// Toggle selected state
				checkListItem.setSelected(!checkListItem.isSelected());
				// Repaint cell
				eventList.repaint(eventList.getCellBounds(index, index));
			}
		});

		gridBagContraints.gridx = 0;
		gridBagContraints.gridy = 1;
		appFrame.add(mainCatPanel, gridBagContraints);

		defaultListModel = new DefaultListModel();
		JList<CheckListItem> subCategoryList = new JList<CheckListItem>(defaultListModel);
		subCategoryList.setCellRenderer(new CheckListRenderer());
		for (int i = 0; i < subCategories.length; i++) {
			defaultListModel.addElement(new CheckListItem(i + ". " + subCategories[i]));
		}
		subCategoryList.setBackground(Color.PINK);
		JScrollPane subCatPanel = new JScrollPane(subCategoryList);
		subCatPanel.setPreferredSize(new Dimension(450, 110));
		subCategoryList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList eventList = (JList) mouseEvent.getSource();
				// Get index of item clicked
				int index = eventList.locationToIndex(mouseEvent.getPoint());
				CheckListItem checkListItem = (CheckListItem) eventList.getModel().getElementAt(index);
				// Toggle selected state
				checkListItem.setSelected(!checkListItem.isSelected());
				// Repaint cell
				eventList.repaint(eventList.getCellBounds(index, index));
			}
		});
		gridBagContraints.gridx = 2;
		gridBagContraints.gridy = 1;
		appFrame.add(subCatPanel, gridBagContraints);

		defaultListModel = new DefaultListModel();
		JList<CheckListItem> attributeList = new JList<CheckListItem>(defaultListModel);
		attributeList.setCellRenderer(new CheckListRenderer());
		for (int i = 0; i < attributes.length; i++) {
			defaultListModel.addElement(new CheckListItem(i + ". " + attributes[i]));
		}
		JScrollPane attributePanel = new JScrollPane(attributeList);
		attributePanel.setPreferredSize(new Dimension(450, 110));
		attributeList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList eventList = (JList) mouseEvent.getSource();
				// Get index of item clicked
				int index = eventList.locationToIndex(mouseEvent.getPoint());
				CheckListItem checkListItem = (CheckListItem) eventList.getModel().getElementAt(index);
				// Toggle selected state
				checkListItem.setSelected(!checkListItem.isSelected());
				// Repaint cell
				eventList.repaint(eventList.getCellBounds(index, index));
			}
		});
		attributeList.setBackground(Color.orange);
		gridBagContraints.gridx = 4;
		gridBagContraints.gridy = 1;
		appFrame.add(attributePanel, gridBagContraints);

		JTable tableOfBusinesses = new JTable();
		/*tableOfBusinesses.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println(tableOfBusinesses.getSelectedColumn());
			}
		});*/
		/*tableOfBusinesses.setModel(new DefaultTableModel(new Object[][] {  },
				new String[] { "Business", "City", "State", "Stars" }));
*/
		/*tableOfBusinesses.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				// final JTable selectedCell = (JTable)mouseEvent.getSource();
				// int selectedCol = selectedCell.getSelectedColumn();
				int selectedRow = tableOfBusinesses.getModel().getColumnCount();
				System.out.println(tableOfBusinesses.getSelectedColumn());
			}
		});*/
		gridBagContraints.gridx = 6;
		gridBagContraints.gridy = 1;

		appFrame.add(new JScrollPane(tableOfBusinesses), gridBagContraints);

		gridBagContraints.gridx = 0;
		gridBagContraints.gridy = 0;
		gridBagContraints.weightx = 3;
		gridBagContraints.weighty = 0.01;
		gridBagContraints.gridwidth = GridBagConstraints.REMAINDER;

		gridBagContraints.insets = new Insets(5, 5, 5, 5);
		gridBagContraints.fill = GridBagConstraints.NONE;

		gridBagContraints.gridx = 7;
		gridBagContraints.gridy = 3;
		gridBagContraints.weightx = 0.125;
		gridBagContraints.weighty = 0.01;
		gridBagContraints.gridwidth = 1;

		JButton searchButton = new JButton("Search");
		gridBagContraints.gridx = 6;
		gridBagContraints.gridy = 3;
		gridBagContraints.weightx = 0.125;
		gridBagContraints.weighty = 0.01;
		gridBagContraints.gridwidth = 1;

		gridBagContraints.weightx = 0.25;
		gridBagContraints.weighty = 0.01;
		gridBagContraints.gridwidth = 1;
		appFrame.add(searchButton, gridBagContraints);

		JLabel selectDaysLabel = new JLabel("Select Day");
		gridBagContraints.gridx = 0;
		gridBagContraints.gridy = 2;
		appFrame.add(selectDaysLabel, gridBagContraints);

		JComboBox<String> selectDayOfTheWeek = new JComboBox<String>(daysOfTheWeekArr);
		gridBagContraints.gridx = 0;
		gridBagContraints.gridy = 3;
		appFrame.add(selectDayOfTheWeek, gridBagContraints);

		JLabel openTimeLabel = new JLabel("Opening Time");
		gridBagContraints.gridx = 1;
		gridBagContraints.gridy = 2;
		appFrame.add(openTimeLabel, gridBagContraints);

		JComboBox<String> openTimingsComboBox = new JComboBox<String>(timeSlots);
		gridBagContraints.gridx = 1;
		gridBagContraints.gridy = 3;
		appFrame.add(openTimingsComboBox, gridBagContraints);

		JLabel closeTimeLabel = new JLabel("Closing Time");
		gridBagContraints.gridx = 2;
		gridBagContraints.gridy = 2;
		appFrame.add(closeTimeLabel, gridBagContraints);

		JComboBox<String> closeTimingComboBox = new JComboBox<String>(timeSlots);
		gridBagContraints.gridx = 2;
		gridBagContraints.gridy = 3;
		appFrame.add(closeTimingComboBox, gridBagContraints);

		JLabel searchForLabel = new JLabel("Search For");
		gridBagContraints.gridx = 4;
		gridBagContraints.gridy = 2;
		appFrame.add(searchForLabel, gridBagContraints);

		JComboBox<String> anyOrAll = new JComboBox<String>(allOrAnyAttributes);
		gridBagContraints.gridx = 4;
		gridBagContraints.gridy = 3;
		appFrame.add(anyOrAll, gridBagContraints);

		appFrame.setSize(1000, 600);

		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		searchButton.addActionListener(new ActionListener() {
			
		

			@Override
			public void actionPerformed(ActionEvent e) {
				businessValueList.clear();
				businessValueList = DatabaseUtil.getBusinessValues(mainCategories,
						subCategories, attributes, mainCategoryList.getSelectedIndices(),
						subCategoryList.getSelectedIndices(), attributeList.getSelectedIndices(),
						daysOfTheWeekArr[selectDayOfTheWeek.getSelectedIndex()],
						timeSlots[openTimingsComboBox.getSelectedIndex()],
						timeSlots[closeTimingComboBox.getSelectedIndex()], anyOrAll.getSelectedIndex());
				for (Iterator iterator = businessValueList.iterator(); iterator.hasNext();) {
					BusinessBean businessBean = (BusinessBean) iterator.next();

				}

				tableOfBusinesses.setModel(showBusinessData(tableOfBusinesses, businessValueList));
				
				tableOfBusinesses.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent arg0) {
						JTable target = (JTable) arg0.getSource();
						int selectedRow = target.getSelectedRow();
						
						ArrayList<ReviewTable> reviewTableList = DatabaseUtil
								.getReviews(businessValueList.get(selectedRow).getBusiness_id());
						for (Iterator iterator = reviewTableList.iterator(); iterator.hasNext();) {
							ReviewTable reviewTable = (ReviewTable) iterator.next();
							
						}

						JFrame reviewFrame = new JFrame("Reviews By Users");
						reviewFrame.setSize(500, 500);
						JTable reviewTable = new JTable();
						reviewTable.setModel(showReviewData(reviewTable, reviewTableList));
						
						
						reviewFrame.setContentPane(new JScrollPane(reviewTable));
						reviewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						reviewFrame.setVisible(true);
					}

				});
			}

		});

		appFrame.setVisible(true);
	}
	
	private static TableModel showReviewData(JTable reviewTable, ArrayList<ReviewTable> reviewTableList) {

		DefaultTableModel reviewModel = new DefaultTableModel(
				new Object[][] {  },
				new String[] { "Description", "Stars", "Useful Votes", "Username", "Review Date" });
		
		for (ReviewTable review : reviewTableList) {
			reviewModel.addRow(new Object[]{review.review.getDescription(),review.review.getStars(),review.review.getUseful(), review.username, review.review.getDate()});
		}
		
		return reviewModel;
	}

	private static TableModel showBusinessData(JTable tableOfBusinesses, ArrayList<BusinessBean> businessValueList) {
		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {  },
				new String[] { "Business", "City", "State", "Stars" });

		for (BusinessBean businessBean : businessValueList) {
			tableModel.addRow(new Object[] { businessBean.getName(), businessBean.getCity(), businessBean.getState(),
					businessBean.getStars() });
		}

		return tableModel;
	}

	static class CheckListItem {
		private String label;
		private boolean isSelected = false;

		public CheckListItem(String label) {
			this.label = label;
		}

		public boolean isSelected() {
			return isSelected;
		}

		public void setSelected(boolean isSelected) {
			this.isSelected = isSelected;
		}

		public String toString() {
			return label;
		}
	}

	static class CheckListRenderer extends JCheckBox implements ListCellRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean hasFocus) {
			setEnabled(list.isEnabled());
			setSelected(((CheckListItem) value).isSelected());
			setFont(list.getFont());
			setBackground(list.getBackground());
			setForeground(list.getForeground());
			setText(value.toString());
			return this;
		}
	}

	private static void populateLists() {

		try {
			connection = ConnectionFactory.getOJDBCConnection();
			mainCategories = DatabaseUtil.getMainCategories(connection);
			subCategories = DatabaseUtil.getSubCategories(connection);
			attributes = DatabaseUtil.getAllAttribues(connection);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static class ReviewTable {
		public ReviewBean review;
		public String username;
	}

}
