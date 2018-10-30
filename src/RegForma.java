/*
    Приложението представлява регистрационна форма извеждаща информацията попълнена от потребителя към база от данни.
    Database: MySQL (ver. 8.0.12) Driver (JDBC4.0): MySQL Connector Java (ver. mysql-connector-java-5.1.46.
    Извършва се проверка на потребителската парола и се генерира допълнителен прозорец с потвърждение на статуса.
*/


import javax.swing.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class RegForma implements ActionListener {
    //Създаване на обекти
    JFrame frame;
    String[] gender={"Мъж","Жена", "Дете (под 18 години)"};
    JLabel nameLabel=new JLabel("Име :");
    JLabel genderLabel=new JLabel("Пол :");
    JLabel surnameLabel =new JLabel("Фамилия :");
    JLabel passwordLabel=new JLabel("Парола :");
    JLabel confirmPasswordLabel=new JLabel("Потвърдете паролата :");
    JLabel addressLabel =new JLabel("Адрес :");
    JLabel emailLabel=new JLabel("Електронна поща :");
    JLabel phonenLabel=new JLabel("Телефонен номер :");
    JTextField nameTextField=new JTextField();
    JComboBox genderComboBox=new JComboBox(gender);
    JTextField surnameTextField =new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JPasswordField confirmPasswordField=new JPasswordField();
    JTextField addressTextField =new JTextField();
    JTextField emailTextField=new JTextField();
    JTextField phonenTextField=new JTextField();
    JButton registerButton=new JButton("Регистрирай");
    JButton resetButton=new JButton("Изчисти");


    RegForma()
    {
        createWindow();
        setLocationAndSize();
        addComponentsToFrame();
        actionEvent();
    }
    public void createWindow()
    {
        frame=new JFrame();
        frame.setTitle("Регистрационна форма");
        frame.setBounds(60,60,400,610);
        frame.getContentPane().setBackground(new java.awt.Color(255, 215, 0));
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
    public void setLocationAndSize()
    {
        nameLabel.setBounds(20,20,40,70);
        surnameLabel.setBounds(20,70,100,70);
        genderLabel.setBounds(20,120,80,70);
        addressLabel.setBounds(20,170,100,70);
        emailLabel.setBounds(20,220,120,70);
        phonenLabel.setBounds (20, 270, 120,70);
        passwordLabel.setBounds(20,320,100,70);
        confirmPasswordLabel.setBounds(20,370,140,70);
        nameTextField.setBounds(180,43,165,23);
        surnameTextField.setBounds(180,93,165,23);
        genderComboBox.setBounds(180,143,165,23);
        addressTextField.setBounds(180,193,165,23);
        emailTextField.setBounds(180,243,165,23);
        phonenTextField.setBounds(180,293 ,165,23);

        passwordField.setBounds(180,343,165,23);
        confirmPasswordField.setBounds(180,393,165,23);
        registerButton.setBounds(70,457,120,35);
        resetButton.setBounds(220,457,120,35);
    }
    public void addComponentsToFrame()
    {
        frame.add(nameLabel);
        frame.add(genderLabel);
        frame.add(surnameLabel);
        frame.add(passwordLabel);
        frame.add(confirmPasswordLabel);
        frame.add(addressLabel);
        frame.add(emailLabel);
        frame.add(phonenLabel);
        frame.add(nameTextField);
        frame.add(genderComboBox);
        frame.add(surnameTextField);
        frame.add(passwordField);
        frame.add(confirmPasswordField);
        frame.add(addressTextField);
        frame.add(emailTextField);
        frame.add(phonenTextField);
        frame.add(registerButton);
        frame.add(resetButton);
    }
    public void actionEvent()
    {
        registerButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==registerButton)
        {
            try {
                System.setProperty("jdbc.drivers", "org.gjt.mm.mysql.Driver");
                //Създаване на връзка с базата
                Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/martinDatabase","root","root");
                //Деклариране на подготвените изрази
                PreparedStatement Pstatement=connection.prepareStatement("insert into potrebitel values(?,?,?,?,?,?,?,?)");
                //Уточнение на прараметрите - тук при извикване на getText получавам String (като непостоянен обект),
                //който може да не се променя и така паролата остава в паметта, докато не се събере от garbage collector.
                Pstatement.setString(1,nameTextField.getText());
                Pstatement.setString(2,surnameTextField.getText());
                Pstatement.setString(3,genderComboBox.getSelectedItem().toString());
                Pstatement.setString(4,addressTextField.getText());
                Pstatement.setString(5,emailTextField.getText());
                Pstatement.setString(6,phonenTextField.getText());
                Pstatement.setString(7,passwordField.getText());
                Pstatement.setString(8,confirmPasswordField.getText());

                //Проверка за съвпадение на паролата
                if(passwordField.getText().equalsIgnoreCase(confirmPasswordField.getText()))
                {
                    //Изпълнение на заявки
                    Pstatement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Регистрирахте се успешно.");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"ВНИМАНИЕ: Паролата не съвпада!");
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }


        }
        if(e.getSource()==resetButton)
        {
            nameTextField.setText("");
            genderComboBox.setSelectedItem("Мъж");
            surnameTextField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
            addressTextField.setText("");
            emailTextField.setText("");
            phonenTextField.setText("");
        }

    }
}