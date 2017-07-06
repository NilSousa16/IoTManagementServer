package br.ufba.dcc.wiser.fot.manager.test;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;

public class TestImpl {

	public static void main(String[] args) {
		
		Gateway gtw = null;
		
		gtw = new Gateway();
		
		if(gtw == null) {
			System.out.println("Gtw is null.");
		} else {
			System.out.println("Gtw is not null");
		}
		
		System.out.println("End the execution.");

	}

}
