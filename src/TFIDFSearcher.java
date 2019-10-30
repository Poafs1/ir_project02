//Name: Rattanavaree Muangamai - Pantita Wang - Prach Yothaprasert
//Section: 3
//ID: 6088092 - 6088219 - 6088234

import java.util.List;

public class TFIDFSearcher extends Searcher
{
	List<Document> parse;
	public TFIDFSearcher(String docFilename) {
		super(docFilename);
		/************* YOUR CODE HERE ******************/
		parse = Searcher.parseDocumentFromFile(docFilename);
		/***********************************************/
	}
	
	@Override
	public List<SearchResult> search(String queryString, int k) {
		/************* YOUR CODE HERE ******************/

		return null;
		/***********************************************/
	}
}
