package com.kit.mBranchAndBoundMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MMethodGui {

    private JFrame MMethodStart;
    private JFrame MMethodCore;
    private JFrame MMethodResult;
    private JFrame MMethodDone;
    private SpinnerNumberModel variablesNumber = new SpinnerNumberModel(0, 0, 1000, 1);
    private SpinnerNumberModel conditionsNumber = new SpinnerNumberModel(0, 0, 1000, 1);
    //private SpinnerNumberModel xChangeM = new SpinnerNumberModel(0, -1000, 1000, 1);
    private JSpinner variableSp = new JSpinner(variablesNumber);
    private JSpinner conditionsSp = new JSpinner(conditionsNumber);
    private List<JSpinner> list = new ArrayList<JSpinner>();

    private JPanel corePanelOne = new JPanel();
    private JPanel coreTargetFunction = new JPanel();
    //private JPanel coreAddition = new JPanel();
    private JPanel resultPanel = new JPanel();
    private JPanel calculatedPanel = new JPanel();
    private int conditionNumberN;
    private int variableNumberN;

    private MMethodConversion methodConversion;

    public MMethodGui(final MMethodConversion mMethodConversion){

        this.methodConversion = mMethodConversion;

        MMethodStart = new JFrame("MMethodStart");
        MMethodCore = new JFrame("MMethodCore");
        MMethodResult = new JFrame("MMethodResult");
        MMethodDone = new JFrame("MMethodDone");

        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));

        JLabel variablesLabel = new JLabel("Variables number");
        JLabel conditionsLabel = new JLabel("Conditions number");
        startPanel.add(variablesLabel);
        startPanel.add(variableSp);
        startPanel.add(conditionsLabel);
        startPanel.add(conditionsSp);

        JButton enterButton = new JButton("Enter");
        startPanel.add(enterButton);

        //coreAddition.setLayout(new BoxLayout(coreAddition, BoxLayout.Y_AXIS));

        JButton secondEnterButton = new JButton("Enter");
        //coreAddition.add(secondEnterButton);

        JButton calculateButton = new JButton("Calculate");
        //calculatedPanel.add(calculateButton);

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conditionNumberN = (Integer)conditionsSp.getValue();
                variableNumberN = (Integer)variableSp.getValue();
                for (int i = 0; i < conditionNumberN; i++){
                    for (int j = 0; j <= variableNumberN; j++){
                        if (j == variableNumberN){
                            JSpinner numSpinner = new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 1));
                            corePanelOne.add(new JPanel().add(new JLabel(" = ")));
                            corePanelOne.add(numSpinner);
                            list.add(numSpinner);
                        }
                        else {
                            JSpinner numSpinner = new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 1));
                            corePanelOne.add(new JLabel("x" + (j + 1)));
                            corePanelOne.add(numSpinner);
                            list.add(numSpinner);
                        }
                    }
                }
                for (int i =  0; i <= variableNumberN; i++){
                    if (i == variableNumberN){
                        JSpinner numSpinner = new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 1));
                        coreTargetFunction.add(new JPanel().add(new JLabel(" = ")));
                        coreTargetFunction.add(numSpinner);
                        list.add(numSpinner);
                    } else {
                        JSpinner numSpinner = new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 1));
                        coreTargetFunction.add(new JLabel("x" + (i + 1)));
                        coreTargetFunction.add(numSpinner);
                        list.add(numSpinner);
                    }
                }
                MMethodStart.setVisible(false);
                MMethodCore.setVisible(true);
            }
        });

        secondEnterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                methodConversion.convertToM(list, conditionNumberN, variableNumberN);
                int[] MMas = methodConversion.getMMas();
                int[][] xMas = methodConversion.getXMas();
                int[] xMasResCon =  methodConversion.getXMasResCon();
                for (int i = 0; i < conditionNumberN; i++){
                    for (int j = 0; j <= variableNumberN + conditionNumberN; j++){
                        if (j == variableNumberN + conditionNumberN){
                            JEditorPane editorPane = new JEditorPane();
                            editorPane.setText(Integer.toString(xMas[i][j]));
                            editorPane.setEditable(false);
                            resultPanel.add(new JPanel().add(new JLabel(" = ")));
                            resultPanel.add(editorPane);
                        }
                        else {
                            JEditorPane editorPane = new JEditorPane();
                            editorPane.setText(Integer.toString(xMas[i][j]));
                            editorPane.setEditable(false);
                            resultPanel.add(new JLabel("x" + (j + 1)));
                            resultPanel.add(editorPane);
                        }
                    }
                }
                for (int i =  0; i < 1; i++){
                    for (int j = 0; j <= variableNumberN + conditionNumberN; j++) {
                        if (j == variableNumberN + conditionNumberN) {
                            JEditorPane editorPane = new JEditorPane();
                            editorPane.setText(Integer.toString(MMas[j]));
                            editorPane.setEditable(false);
                            resultPanel.add(new JPanel().add(new JLabel(" = ")));
                            resultPanel.add(editorPane);
                        } else {
                            JEditorPane editorPane = new JEditorPane();
                            editorPane.setText(Integer.toString(MMas[j]));
                            editorPane.setEditable(false);
                            resultPanel.add(new JLabel("M" + (j + 1)));
                            resultPanel.add(editorPane);
                        }
                    }
                }

                for (int i =  0; i < 1; i++){
                    for (int j = 0; j <= variableNumberN + conditionNumberN; j++) {
                        if (j == variableNumberN + conditionNumberN) {
                            JEditorPane editorPane = new JEditorPane();
                            editorPane.setText(Integer.toString(xMasResCon[j]));
                            editorPane.setEditable(false);
                            resultPanel.add(new JPanel().add(new JLabel(" = ")));
                            resultPanel.add(editorPane);
                        } else {
                            JEditorPane editorPane = new JEditorPane();
                            editorPane.setText(Integer.toString(xMasResCon[j]));
                            editorPane.setEditable(false);
                            resultPanel.add(new JLabel("ResCon" + (j + 1)));
                            resultPanel.add(editorPane);
                        }
                    }
                }

                MMethodCore.setVisible(false);
                MMethodResult.setVisible(true);
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] MMas = methodConversion.getMMas();
                int[][] xMas = methodConversion.getXMas();
                int[] xMasResCon =  methodConversion.getXMasResCon();
                methodConversion.calculateM(xMas, xMasResCon, MMas, conditionNumberN, variableNumberN);
                MMas = methodConversion.getMMas();
                xMas = methodConversion.getXMas();
                xMasResCon =  methodConversion.getXMasResCon();
                for (int i = 0; i < conditionNumberN; i++){
                    for (int j = 0; j <= variableNumberN + conditionNumberN; j++){
                        if (j == variableNumberN + conditionNumberN){
                            JEditorPane editorPane = new JEditorPane();
                            editorPane.setText(Integer.toString(xMas[i][j]));
                            editorPane.setEditable(false);
                            calculatedPanel.add(new JPanel().add(new JLabel(" = ")));
                            calculatedPanel.add(editorPane);
                        }
                        else {
                            JEditorPane editorPane = new JEditorPane();
                            editorPane.setText(Integer.toString(xMas[i][j]));
                            editorPane.setEditable(false);
                            calculatedPanel.add(new JLabel("x" + (j + 1)));
                            calculatedPanel.add(editorPane);
                        }
                    }
                }
                for (int i =  0; i < 1; i++){
                    for (int j = 0; j <= variableNumberN + conditionNumberN; j++) {
                        if (j == variableNumberN + conditionNumberN) {
                            JEditorPane editorPane = new JEditorPane();
                            editorPane.setText(Integer.toString(MMas[j]));
                            editorPane.setEditable(false);
                            calculatedPanel.add(new JPanel().add(new JLabel(" = ")));
                            calculatedPanel.add(editorPane);
                        } else {
                            JEditorPane editorPane = new JEditorPane();
                            editorPane.setText(Integer.toString(MMas[j]));
                            editorPane.setEditable(false);
                            calculatedPanel.add(new JLabel("M" + (j + 1)));
                            calculatedPanel.add(editorPane);
                        }
                    }
                }

                for (int i =  0; i < 1; i++){
                    for (int j = 0; j <= variableNumberN + conditionNumberN; j++) {
                        if (j == variableNumberN + conditionNumberN) {
                            JEditorPane editorPane = new JEditorPane();
                            editorPane.setText(Integer.toString(xMasResCon[j]));
                            editorPane.setEditable(false);
                            calculatedPanel.add(new JPanel().add(new JLabel(" = ")));
                            calculatedPanel.add(editorPane);
                        } else {
                            JEditorPane editorPane = new JEditorPane();
                            editorPane.setText(Integer.toString(xMasResCon[j]));
                            editorPane.setEditable(false);
                            calculatedPanel.add(new JLabel("ResCon" + (j + 1)));
                            calculatedPanel.add(editorPane);
                        }
                    }
                }

                MMethodResult.setVisible(false);
                MMethodDone.setVisible(true);
            }
        });

        MMethodStart.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MMethodStart.getContentPane().add(BorderLayout.CENTER, startPanel);
        MMethodStart.setBounds(800, 500, 300, 200);
        MMethodStart.pack();
        MMethodStart.setVisible(true);

        MMethodCore.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MMethodCore.getContentPane().add(BorderLayout.CENTER, corePanelOne);
        MMethodCore.getContentPane().add(BorderLayout.NORTH, coreTargetFunction);
        MMethodCore.getContentPane().add(BorderLayout.SOUTH, secondEnterButton);
        MMethodCore.setBounds(800, 500, 580, 300);
        MMethodStart.pack();

        MMethodResult.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MMethodResult.getContentPane().add(BorderLayout.CENTER, resultPanel);
        MMethodResult.getContentPane().add(BorderLayout.SOUTH, calculateButton);
        MMethodResult.setBounds(800, 500, 580, 300);
        MMethodResult.pack();

        MMethodDone.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MMethodDone.getContentPane().add(BorderLayout.CENTER, calculatedPanel);
        MMethodDone.setBounds(800, 500, 580, 300);
        MMethodDone.pack();
    }
}
