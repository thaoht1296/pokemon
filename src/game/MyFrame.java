/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
/**
 *
 * @author Hoa Nguyen
 */

public class MyFrame extends JFrame implements ActionListener, Runnable {
    private static final long serialVersionUID = 1L;
    private String author = "Group 1_OOP_Mr.Hoang Anh";
    private int maxTime = 300;
    public int time = maxTime;
    private int row = 10;
    private int col = 10;
    private int width = 1000;
    private int height = 700;
    private JLabel lbScore;
    private JLabel highScore;
    private JProgressBar progressTime;
    private JButton btnNewGame;
    private MyGraphics graphicsPanel;
    private JPanel mainPanel;
    private AudioClip clip;
    private JButton ON;
    private JButton OFF;
    private JButton Help;

    public MyFrame() throws MalformedURLException {
        add(mainPanel = createMainPanel());
        setTitle("Pokemon Game");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
        File file = new File("E:\\OOP\\Game\\src\\game\\pika.wav");
        URL url = null;
        if (file.canRead()) {url = file.toURI().toURL();}
        System.out.println(url);
        clip = Applet.newAudioClip(url);
        clip.play();
    }

    private JPanel createMainPanel() {
	JPanel panel = new JPanel(new BorderLayout());
	panel.add(createGraphicsPanel(), BorderLayout.CENTER);
	panel.add(createControlPanel(), BorderLayout.EAST);
	panel.add(createStatusPanel(), BorderLayout.PAGE_END);
	return panel;
    }

    private JPanel createGraphicsPanel() {
	graphicsPanel = new MyGraphics(this, row, col);
	JPanel panel = new JPanel(new GridBagLayout());
	// panel.setBorder(new EmptyBorder(20, 20, 20, 20));
	panel.setBackground(Color.gray);
	panel.add(graphicsPanel);
	return panel;
    }

    private JPanel createControlPanel() {
        highScore = new JLabel(String.valueOf(docDiem()));
	lbScore = new JLabel("0");
	progressTime = new JProgressBar(0, 100);
	progressTime.setValue(100);
        progressTime.setStringPainted(true);

	// create panel container score and time
	JPanel panelLeft = new JPanel(new GridLayout(0, 1, 5, 5));
        panelLeft.add(new JLabel("High Score:"));
	panelLeft.add(new JLabel("Score:"));
	panelLeft.add(new JLabel("Time:"));

	JPanel panelCenter = new JPanel(new GridLayout(0, 1, 5, 5));
        panelCenter.add(highScore);
	panelCenter.add(lbScore);
	panelCenter.add(progressTime);

	JPanel panelScoreAndTime = new JPanel(new BorderLayout(5, 0));
	panelScoreAndTime.add(panelLeft, BorderLayout.WEST);
	panelScoreAndTime.add(panelCenter, BorderLayout.CENTER);

	// create panel container panelScoreAndTime and button new game
	JPanel panelControl = new JPanel(new BorderLayout(10, 10));
	panelControl.setBorder(new EmptyBorder(10, 3, 5, 3));
	panelControl.add(panelScoreAndTime, BorderLayout.CENTER);
        panelControl.add(btnNewGame = createButton("New Game"),
				BorderLayout.PAGE_END);
	panelControl.add(ON = createButton("Sound ON"), BorderLayout.WEST);
        panelControl.add(OFF = createButton("Sound OFF"), BorderLayout.EAST);
        panelControl.add(Help = createButton("Help"), BorderLayout.PAGE_START);

	Icon icon = new ImageIcon(getClass().getResource(
				"icon"));

	// use panel set Layout BorderLayout to panel control in top
	JPanel panel = new JPanel(new BorderLayout());
	panel.setBorder(new TitledBorder("Status"));
	panel.add(panelControl, BorderLayout.PAGE_START);
	panel.add(new JLabel(icon), BorderLayout.CENTER);
	return panel;
    }

	// create status panel container author
    private JPanel createStatusPanel() {
	JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	panel.setBackground(Color.lightGray);
	JLabel lbAuthor = new JLabel(author);
	lbAuthor.setForeground(Color.blue);
	panel.add(lbAuthor);
	return panel;
    }

	// create a button
    private JButton createButton(String buttonName) {
	JButton btn = new JButton(buttonName);
	btn.addActionListener(this);
	return btn;
    }
    public int docDiem(){
        int n = 0;
        try {
            Scanner inp = new Scanner(Paths.get("HighScore.txt"), "UTF-8");
            n = inp.nextInt();
            inp.close();
        } catch (Exception e) {
            System.out.println("Error!");
        }
        return n;
    }
    
    public void ghiDiem(int i){
        try {
            PrintWriter inp = new PrintWriter("HighSore.txt", "UTF-8");
            inp.print(i);
            inp.close();
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    public void newGame() throws MalformedURLException {
	time = maxTime;
	graphicsPanel.removeAll();
	mainPanel.add(createGraphicsPanel(), BorderLayout.CENTER);
	mainPanel.validate();
	mainPanel.setVisible(true);
	lbScore.setText("0");
        highScore.setText(String.valueOf(docDiem()));
        clip.stop();
        File file = new File("C:\\Users\\Admin\\Downloads\\pika.wav");
        URL url = null;
        if (file.canRead()) {url = file.toURI().toURL();}
        System.out.println(url);
//        clip = Applet.newAudioClip(url);
 //       clip.play();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == btnNewGame) {
            try {
                newGame();
            } catch (MalformedURLException ex) {
                Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
        if(e.getSource() == ON){
            clip.stop();
            clip.play();
        }
        if(e.getSource() == OFF){
            clip.stop();
        }
        if(e.getSource() == Help){
            showDialogHelp("Find the pairs of matching picture, the path betwen "
                    + "\ntwo pictures must be clear from other blocks and "
                    + "\nthe path cannot bend three or more time, clear all block "
                    + "\nto go to win", "Help");
        }
    }

    @Override
    public void run() {
	while (true) {
            try {
		Thread.sleep(1000);
            } catch (InterruptedException e) {
		e.printStackTrace();
            }
            progressTime.setValue((int) ((double) time / maxTime * 100));
	}
    }

    public JLabel getLbScore() {
	return lbScore;
    }

    public void setLbScore(JLabel lbScore) {
	this.lbScore = lbScore;
    }

    public JProgressBar getProgressTime() {
	return progressTime;
    }

    public void setProgressTime(JProgressBar progressTime) {
	this.progressTime = progressTime;
    }

    public void setTime(int time) {
	this.time = time;
    }

    public int getTime() {
	return time;
    }

    public JLabel getHighScore() {
        return highScore;
    }

    public void setHighScore(JLabel highScore) {
        this.highScore = highScore;
    }
    
    public void showDialogNewGame(String message, String title) throws MalformedURLException {
	int select = JOptionPane.showOptionDialog(null, message, title,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
			null, null);
	if (select == 0) {
            newGame();
	} else {
            System.exit(0);
	}
    }
    
    public void showDialogHelp(String name, String title){
        JOptionPane.showMessageDialog(rootPane, name, title, 0);
    }

}