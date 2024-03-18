package MyNotePad;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import MyNotePad.MyNotePadView;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MyNotepadController implements ActionListener {
	MyNotePadView myNotepadView;
	JFileChooser fc = new JFileChooser();

	public MyNotepadController(MyNotePadView myNotepadView) {
		this.myNotepadView = myNotepadView;
        createShortcuts();

	}

	private void createShortcuts() {
	    // Tạo các phím tắt
	    InputMap inputMap = myNotepadView.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	    ActionMap actionMap = myNotepadView.getRootPane().getActionMap();

	    // Phím tắt cho "Open" (Ctrl + O)
	    KeyStroke openKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);
	    inputMap.put(openKeyStroke, "open");
	    actionMap.put("open", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	OpenFile();
	        }
	    });

	    // Phím tắt cho "Save" (Ctrl + S)
	    KeyStroke saveKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
	    inputMap.put(saveKeyStroke, "save");
	    actionMap.put("save", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            SaveFile();
	        }
	    });

	    // Phím tắt cho "New" (Ctrl + N)
	    KeyStroke newKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);
	    inputMap.put(newKeyStroke, "new");
	    actionMap.put("new", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	NewFile();
	        }
	    });
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		if (command.equals("Open")) {
			OpenFile();
		} else if (command.equals("Save")) {
			SaveFile();
	    }else if (command.equals("New")) {
		   NewFile();
	    }
	}
	
	public void NewFile() {
	    myNotepadView.model.setFileName(""); // Đặt tên tệp là rỗng
	    myNotepadView.lblNewLabel.setText("Address"); // Xóa nội dung hiển thị tên tệp
	    myNotepadView.model.setContent(""); // Xóa nội dung văn bản
	    myNotepadView.textArea.setText(""); // Xóa nội dung hiển thị trong textArea

	}
		
	public void save(String fileName) {
		try {
			PrintWriter pw = new PrintWriter(fileName, "UTF-8");
			String data = this.myNotepadView.textArea.getText();
			pw.print(data);
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}
	
	public void SaveFile() {
		if(this.myNotepadView.model.getFileName().length()>0) {
			save(this.myNotepadView.model.getFileName());
		}else {
			int returnVal = fc.showSaveDialog(this.myNotepadView);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				save(file.getAbsolutePath());
				this.myNotepadView.lblNewLabel.setText(file.getAbsolutePath());
			} 
		}
	}
	
	public void OpenFile() {
		int returnVal = fc.showOpenDialog(this.myNotepadView);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			String fileName = file.getName();
			this.myNotepadView.model.setFileName(file.getAbsolutePath());
			this.myNotepadView.lblNewLabel.setText(this.myNotepadView.model.getFileName());
			if(fileName.endsWith(".txt") || fileName.endsWith(".docx")) {
				try {
					List<String> allText = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
					String result = "";
					for (String line : allText) {
						result+=line;
						result+="\n";
					}
					this.myNotepadView.model.setContent(result);
					this.myNotepadView.textArea.setText(this.myNotepadView.model.getContent());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	
	}
}