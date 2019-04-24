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
	int pointer;

	public TextEditor() {
		frame = new JFrame("editor");
		textArea = new JTextArea();

		textArea.getDocument().addDocumentListener(new MyDocumentListener());

        textArea.addCaretListener(new CaretListener());
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
		textArea.setCaretPosition(pointer);
	}
	class MyDocumentListener implements DocumentListener {
		final String newline = "\n";

		public void insertUpdate(DocumentEvent e) {
			operation = 'i';

//			pointer = textArea.getCaretPosition();
//			System.out.println(pointer);
//			try {
//				insertLocal(textArea.getText().charAt(pointer), pointer);
//
//			} catch (IOException err){
//				System.out.println(err);
//			}

		}

		public void removeUpdate(DocumentEvent e) {
//			updateLog(e, "removed from" + textArea.getText().charAt(pointer - 1));
			operation = 'd';
//			pointer = textArea.getCaretPosition();
//			System.out.println(pointer);
//			try{
//				deleteLocal(pointer - 1);
//			}catch (IOException err){
//				System.out.println(err);
//			}

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


			pointer = e.getDot();
			if(operation == 'i'){
				int p = e.getDot() - 1;
				if(e.getDot() != 0) {
					try {
						insertLocal(textArea.getText().charAt(p), p);
					} catch (IOException err){
						System.out.println(err);
					}
				}

			}else if(operation == 'd') {
				int p = e.getDot();
//				System.out.println(pointer);
				try {
					deleteLocal(p);
				} catch (IOException err) {
					System.out.println(err);
				}
//				try{
//					deleteLocal(pointer);
//				}catch (IOException err){
//					System.out.println(err);
//				}
				operation = ' ';
			}
			operation = ' ';
		}

	}

}