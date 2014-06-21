import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;


public class NumberButton extends JButton {
	private int number = -1;
	private boolean selected = false;
	Font font = new Font("Arial", Font.PLAIN, 26);
	
	
	public NumberButton(int n)
	{
		setNumber(n);
		colorButton();
		this.setFont(font);
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public void setSelected(boolean s)
	{
		selected = s;
		colorButton();
	}
	
	public void setNumber(int n)
	{
		number = n;
		this.setText(n+"");
	}
	
	public void buttonClicked()
	{
		selected = !selected;
		colorButton();
	}
	
	public boolean buttonCurrentlySelected()
	{
		return selected;
	}
	
	private void colorButton()
	{
		if (selected)
		{
			this.setForeground(Color.BLUE);
		}
		else
		{
			this.setForeground(Color.MAGENTA);
		}
		//this.setOpaque(true);
		//this.setForeground(Color.red);
		//this.setContentAreaFilled(false);
		//this.setBorderPainted(false);
	}
}
