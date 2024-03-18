package MyNotePad;

import java.awt.EventQueue;

import MyNotePad.MyNotePadView;

public class main {
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
				try {
					MyNotePadView frame = new MyNotePadView();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		
	}
}
