package com.company;

import javax.swing.*;
import javax.swing.event.CaretEvent;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.io.IOException;
import java.util.ArrayList;

import static com.company.Controller.deleteLocal;
import static com.company.Controller.insertLocal;


public class TextEditor extends JPanel  {
	JTextArea textArea;
	JFrame frame;
	char operation;

	public TextEditor() {
		frame = new JFrame("editor");
		textArea = new JTextArea();
		textArea.addCaretListener(new CaretListener());
		textArea.getDocument().addDocumentListener(new MyDocumentListener());

		frame.add(textArea);
		frame.setSize(500, 500);
		frame.show();
	}
	public void update(ArrayList<Character> document){
		String text = "";
		for(int i = 0; i < document.size(); i++){
			text += document.get(i).getValue();
		}
		textArea.setText(text);
	}
	class MyDocumentListener implements DocumentListener {
		final String newline = "\n";

		public void insertUpdate(DocumentEvent e) {
			operation = 'i';

		}

		public void removeUpdate(DocumentEvent e) {
//			updateLog(e, "removed from" + textArea.getText().charAt(pointer - 1));
			operation = 'd';

		}

		public void changedUpdate(DocumentEvent e) {
			//Plain text components don't fire these events.
		}

		public void updateLog(DocumentEvent e, String action) {
			System.out.println(action );
		}
	}
	class CaretListener implements javax.swing.event.CaretListener {


		@Override
		public void caretUpdate(CaretEvent e) {
//			System.out.println(e.getDot() + " " + e.getMark());
//			System.out.println(operation + " " + e.getDot());



			if(operation == 'i'){
				int pointer = e.getDot() - 1;
				if(e.getDot() != 0) {
					try {
						insertLocal(textArea.getText().charAt(pointer), pointer);
						operation = ' ';
					} catch (IOException err){

					}
				}

			}else if(operation == 'd'){
				int pointer = e.getDot();
//				System.out.println(pointer);
				try{
					deleteLocal(pointer);
				}catch (IOException err){

				}

				operation = ' ';
			}

		}
	}

}