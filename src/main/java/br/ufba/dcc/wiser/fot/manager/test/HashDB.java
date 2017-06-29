package br.ufba.dcc.wiser.fot.manager.test;

public class HashDB {
	
	public static void insertBook(BookVO bookVO) {
		System.out.println("Book inserted.");
	}
	
	public static BookVO getBook(String bookName) {
		BookVO bookVO = new BookVO();
		bookVO.setBookName("Stored.");
		return bookVO;
	}

}
