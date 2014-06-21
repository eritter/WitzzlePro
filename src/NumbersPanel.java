import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;


public class NumbersPanel extends JPanel{
	NumberButton[] buttons;
	public NumbersPanel(ActionListener a, int[] card)
	{
		buttons = new NumberButton[9];
		this.setLayout(new GridLayout(3,3));
		for (int i = 0; i < 9; i++)
		{
			NumberButton b = new NumberButton(card[i]);
			b.addActionListener(a);
			this.add(b);
			buttons[i] = b;
			
		}
	}
	
	public int[] getChosenNumbers()
	{
		ArrayList<NumberButton> arrList = new ArrayList<NumberButton>();
		for (int i = 0; i < buttons.length; i++)
		{
			if (buttons[i].buttonCurrentlySelected())
				arrList.add(buttons[i]);
		}
		
		int[] ret = new int[arrList.size()];
		
		for(int i = 0; i < ret.length; i++)
			ret[i] = arrList.get(i).getNumber();
		
		return ret;
	}
	
	public void setNumbers(int[] nums)
	{
		for (int i = 0; i < buttons.length; i++)
			buttons[i].setNumber(nums[i]);
		
		for (int i = 0; i < buttons.length; i++)
			buttons[i].setSelected(false);
	}
}
