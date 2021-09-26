package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import core.filters.FilterRunnerParallel;
import core.filters.ImageViewMatrix;
import core.filters.algorithms.IntensityThresholdFilter;
import core.filters.algorithms.bonus.ConwaysGameOfLife;
import de.informatics4kids.Picture;

public class GameOfLifeDemo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JBackgroundPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOfLifeDemo frame = new GameOfLifeDemo(FilterRunnerParallel.applyFilter(new Picture("C:\\Users\\lukas\\Desktop\\Carolina-Dog2.jpg"), new IntensityThresholdFilter()));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameOfLifeDemo(Picture input) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JBackgroundPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(input.getPicture());
		setContentPane(contentPane);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				long timeAtLastUpdate = 0;
				Picture pic = input;
				while(true) {
					timeAtLastUpdate = System.currentTimeMillis();
					pic = FilterRunnerParallel.applyFilter(pic, ConwaysGameOfLife.getInstance(), ImageViewMatrix.EDGE_BEHAVIOUR_BLACK);
					long timeTaken = System.currentTimeMillis() - timeAtLastUpdate;
					if(timeTaken < 16)
						try {
							Thread.sleep(16 - timeTaken);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					final BufferedImage newPic = pic.getPicture();
					EventQueue.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							contentPane.setBackground(newPic);
							System.out.println("Update took " + timeTaken + " ms");
						}
					});
				}
			}
		}).start();
	}

}
