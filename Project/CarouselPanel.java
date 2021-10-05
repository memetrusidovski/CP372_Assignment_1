import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CarouselPanel extends JPanel {

	private static final long serialVersionUID = 5170646771086997361L;
	private JPanel carousel;
	
	public CarouselPanel() {
		this.initUI(new JPanel[0]);
	}
	
	public CarouselPanel(JPanel[] cards) {
		this.initUI(cards);
	}
	
	private void initUI(JPanel[] cards) {
		
		CardLayout cardLayout = new CardLayout();
		carousel = new JPanel(cardLayout);
		
		for(JPanel card:cards) {
			carousel.add(card);
		}
		
		JButton prev = new JButton("Previous");
		JButton next = new JButton("Next");
		
		prev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.previous(carousel);
			}
			
		});
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.next(carousel);
			}
			
		});
		
		add(prev);
		add(carousel);
		add(next);
		
	}
	
	public void setContent(JPanel[] cards) {
		carousel.removeAll();
		for(JPanel card:cards) {
			carousel.add(card);
		}
	}
	
}
