package bsu.rfe.java.group8.lab2.KONONOK.varC3;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.*;

public class MainFrame extends JFrame {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 350;
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    private int formulaId = 1;

    private ImageIcon fi1;
    private ImageIcon fi2;
    private JLabel formimage;

    private Double mem1, mem2, mem3, result;
    private int memId = 1;

    private Double calculate1(Double x, Double y, Double z) {
        return (Math.sin(Math.PI * y * y) + Math.log(y * y))
                /(Math.sin(Math.PI * z * z) + Math.sin(x) + Math.log(z * z) + x*x + Math.pow(Math.E,Math.cos(z * x)));
    }

    private Double calculate2(Double x, Double y, Double z) {
        return (Math.pow(Math.cos(Math.pow(Math.E, y)) + Math.pow(Math.pow(Math.E,y),2) + Math.pow(1/x,0.5),0.25))
                / (Math.pow(Math.cos(Math.PI*z*z*z) + Math.pow(Math.log(1+z),2),Math.sin(y)));
    }

    private MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        mem1 = 0.0;
        mem2 = 0.0;
        mem3 = 0.0;
        result = 0.0;

        fi1 = new ImageIcon("Strikelev-Prikl_progr-Lab-02.1.bmp");
        fi2 = new ImageIcon("Strikelev-Prikl_progr-Lab-02.2.bmp");
        formimage = new JLabel (fi1);
        Box hboxIm = Box.createHorizontalBox();
        hboxIm.add(Box.createHorizontalGlue());
        hboxIm.add(formimage);
        hboxIm.add(Box.createHorizontalGlue());

        Box hboxFormulaType = Box.createHorizontalBox();
        hboxFormulaType.add(Box.createHorizontalGlue());
        JRadioButton buttonF1 = new JRadioButton("Формула 1");
        JRadioButton buttonF2 = new JRadioButton("Формула 2");
        ButtonGroup group = new ButtonGroup();
        group.add(buttonF1);
        group.add(buttonF2);
        buttonF1.addActionListener(ev -> {
            formulaId = 1;
            formimage.setIcon(fi1);
        });
        buttonF2.addActionListener(ev -> {
            formulaId = 2;
            formimage.setIcon(fi2);
        });
        hboxFormulaType.add(buttonF1);
        hboxFormulaType.add(buttonF2);
        group.setSelected(group.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());

        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(40));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(40));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());

        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 14);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());

        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(ev -> {
            try {

                Double x = Double.parseDouble(textFieldX.getText());
                Double y = Double.parseDouble(textFieldY.getText());
                Double z = Double.parseDouble(textFieldZ.getText());
                if (formulaId==1)
                    result = calculate1(x, y, z);
                else
                    result = calculate2(x, y, z);
                textFieldResult.setText(result.toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MainFrame.this,
                        "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(ev -> {
            textFieldX.setText("");
            textFieldY.setText("");
            textFieldZ.setText("");
            textFieldResult.setText("0");
        });

        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());


        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxIm);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());


        JRadioButton buttonMem1 = new JRadioButton("Переменная 1");
        JRadioButton buttonMem2 = new JRadioButton("Переменная 2");
        JRadioButton buttonMem3 = new JRadioButton("Переменная 3");
        ButtonGroup group2 = new ButtonGroup();
        group2.add(buttonMem1);
        group2.add(buttonMem2);
        group2.add(buttonMem3);
        buttonMem1.addActionListener(ev -> memId = 1);
        buttonMem2.addActionListener(ev -> memId = 2);
        buttonMem3.addActionListener(ev -> memId = 3);
        group2.setSelected(group2.getElements().nextElement().getModel(), true);

        JLabel labelMem1 = new JLabel("       0.0");
        JLabel labelMem2 = new JLabel("       0.0");
        JLabel labelMem3 = new JLabel("       0.0");

        JButton buttonMP = new JButton("M+");
        buttonMP.addActionListener(ev -> {
            if (memId==1) {
                mem1 += result;
                labelMem1.setText("       " + mem1.toString());
            }
            else {
                if (memId == 2) {
                    mem2 += result;
                    labelMem2.setText("       " + mem2.toString());
                }
                else {
                    mem3 += result;
                    labelMem3.setText("       " + mem3.toString());
                }
            }
        });

        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(ev -> {
            if (memId==1) {
                mem1 = 0.0;
                labelMem1.setText("       " + mem1.toString());
            }
            else {
                if (memId == 2) {
                    mem2 = 0.0;
                    labelMem2.setText("       " + mem2.toString());
                }
                else {
                    mem3 = 0.0;
                    labelMem3.setText("       " + mem3.toString());
                }
            }
        });

        Box hboxMC = Box.createHorizontalBox();
        hboxMC.add(Box.createHorizontalGlue());
        hboxMC.add(buttonMP);
        hboxButtons.add(Box.createHorizontalStrut(10));
        hboxMC.add(buttonMC);
        hboxMC.add(Box.createHorizontalGlue());

        Box content2Box = Box.createVerticalBox();
        content2Box.add(Box.createVerticalGlue());
        content2Box.add(buttonMem1);
        content2Box.add(labelMem1);
        content2Box.add(buttonMem2);
        content2Box.add(labelMem2);
        content2Box.add(buttonMem3);
        content2Box.add(labelMem3);
        content2Box.add(hboxMC);
        content2Box.add(Box.createVerticalGlue());

        Box content3Box = Box.createHorizontalBox();
        content3Box.add(Box.createHorizontalGlue());
        content3Box.add(contentBox);
        content3Box.add(Box.createHorizontalStrut(10));
        content3Box.add(content2Box);
        content3Box.add(Box.createHorizontalGlue());
        getContentPane().add(content3Box, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}