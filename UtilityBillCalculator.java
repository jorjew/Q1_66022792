import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

public class UtilityBillCalculator {

    public static void main(String[] args) {

        // Set the color of the progress bar

        UIManager.put("ProgressBar.foreground", Color.BLUE); // เปลี่ยนสีเป็นสีน้ำเงิน

        UIManager.put("ProgressBar.selectionBackground", Color.WHITE);

        UIManager.put("ProgressBar.selectionForeground", Color.BLACK);

        // Create frame

        JFrame frame = new JFrame("Utility Bill Calculator");

        frame.setSize(450, 500);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(null);

        // Radio buttons for selecting bills

        JLabel selectBillLabel = new JLabel("Select Bills:");

        selectBillLabel.setBounds(20, 20, 100, 20);

        frame.add(selectBillLabel);

        JRadioButton waterBill = new JRadioButton("Water Bill");

        waterBill.setBounds(20, 50, 100, 20);

        JRadioButton electricBill = new JRadioButton("Electric Bill");

        electricBill.setBounds(20, 80, 100, 20);

        ButtonGroup billGroup = new ButtonGroup();

        billGroup.add(waterBill);

        billGroup.add(electricBill);

        frame.add(waterBill);

        frame.add(electricBill);

        // Room type selection

        JLabel roomTypeLabel = new JLabel("Room Type:");

        roomTypeLabel.setBounds(150, 20, 100, 20);

        frame.add(roomTypeLabel);

        String[] roomTypes = {"Standard", "Deluxe", "Suite"};

        JComboBox<String> roomTypeComboBox = new JComboBox<>(roomTypes);

        roomTypeComboBox.setBounds(250, 20, 120, 20);

        frame.add(roomTypeComboBox);

        // Total rental display

        JLabel totalRentalLabel = new JLabel("Total Rental:");

        totalRentalLabel.setBounds(150, 50, 100, 20);

        frame.add(totalRentalLabel);

        JTextField totalRentalField = new JTextField();

        totalRentalField.setBounds(250, 50, 120, 20);

        totalRentalField.setEditable(false);

        frame.add(totalRentalField);

        // Input fields for meter readings

        JLabel lastMeterLabel = new JLabel("Last Meter:");

        lastMeterLabel.setBounds(20, 120, 100, 20);

        frame.add(lastMeterLabel);

        JTextField lastMeterField = new JTextField();

        lastMeterField.setBounds(120, 120, 100, 20);

        frame.add(lastMeterField);

        JLabel currentMeterLabel = new JLabel("Current Meter:");

        currentMeterLabel.setBounds(20, 150, 100, 20);

        frame.add(currentMeterLabel);

        JTextField currentMeterField = new JTextField();

        currentMeterField.setBounds(120, 150, 100, 20);

        frame.add(currentMeterField);

        // Unit amount display

        JLabel unitAmountLabel = new JLabel("Unit Amount:");

        unitAmountLabel.setBounds(20, 190, 100, 20);

        frame.add(unitAmountLabel);

        JLabel unitAmountValue = new JLabel("Waiting");

        unitAmountValue.setBounds(120, 190, 100, 20);

        frame.add(unitAmountValue);

        // Result display

        JLabel resultLabel = new JLabel("Result:");

        resultLabel.setBounds(20, 220, 100, 20);

        frame.add(resultLabel);

        JTextField resultField = new JTextField();

        resultField.setBounds(120, 220, 100, 20);

        resultField.setEditable(false);

        frame.add(resultField);

        // Buttons

        JButton calculateButton = new JButton("Calculate Bill");

        calculateButton.setBounds(50, 270, 150, 30);

        frame.add(calculateButton);

        JButton resetButton = new JButton("Reset");

        resetButton.setBounds(250, 270, 150, 30);

        frame.add(resetButton);

        // Progress Bar

        JProgressBar progressBar = new JProgressBar(0, 100);

        progressBar.setBounds(50, 330, 350, 20);

        progressBar.setValue(0);

        progressBar.setStringPainted(true);

        frame.add(progressBar);

        // Action listeners

        calculateButton.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                try {

                    // รับค่าจากช่อง Last Meter และ Current Meter

                    double lastMeter = Double.parseDouble(lastMeterField.getText());

                    double currentMeter = Double.parseDouble(currentMeterField.getText());

                    // ตรวจสอบว่า Current Meter < Last Meter

                    if (currentMeter < lastMeter) {

                        JOptionPane.showMessageDialog(

                            frame,

                            "Error: Current Meter cannot be less than Last Meter.",

                            "Invalid Input",

                            JOptionPane.ERROR_MESSAGE

                        );

                        return; // ออกจากเมธอดเพื่อไม่ให้ทำการคำนวณต่อ

                    }

                    // คำนวณหน่วยที่ใช้ (Unit Amount)

                    double unitAmount = currentMeter - lastMeter;

                    unitAmountValue.setText(String.valueOf(unitAmount));

                    // กำหนดอัตราค่าไฟฟ้าหรือค่าน้ำ

                    double rate = waterBill.isSelected() ? 1.5 : 2.5; // ตัวอย่าง: ค่าน้ำ = 1.5, ค่าไฟฟ้า = 2.5

                    double result = unitAmount * rate;

                    // กำหนดค่า Total Rental ตามประเภทห้อง

                    double totalRental = roomTypeComboBox.getSelectedItem().toString().equals("Standard") ? 500 : 1000;

                    totalRentalField.setText(String.format("%.2f", totalRental));

                    // แสดงผลลัพธ์ของ Bill

                    resultField.setText(String.format("%.2f", result + totalRental));

                    // เมื่อไม่มีข้อผิดพลาดให้ Progress Bar วิ่งไปที่ 50%

                    progressBar.setValue(50);

                } catch (NumberFormatException ex) {

                    // ข้อความแสดงเตือนหากข้อมูลที่ป้อนเป็นตัวอักษรหรือว่างเปล่า

                    JOptionPane.showMessageDialog(

                        frame,

                        "Please enter valid meter readings.",

                        "Error",

                        JOptionPane.ERROR_MESSAGE

                    );

                }

            }

        });

        resetButton.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                billGroup.clearSelection();

                lastMeterField.setText("");

                currentMeterField.setText("");

                unitAmountValue.setText("Waiting");

                resultField.setText("");

                totalRentalField.setText("");

                progressBar.setValue(0); // Reset progress bar

            }

        });

        // Show frame

        frame.setVisible(true);

    }

} 