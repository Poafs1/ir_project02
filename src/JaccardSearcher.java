//Name: 
//Section: 
//ID: 

import java.util.List;

public class JaccardSearcher extends Searcher{
	List<Document> parse;
	public JaccardSearcher(String docFilename) {
		super(docFilename);
		/************* YOUR CODE HERE ******************/
		parse = Searcher.parseDocumentFromFile(docFilename);
		/***********************************************/
	}

	@Override
	public List<SearchResult> search(String queryString, int k) {
		/************* YOUR CODE HERE ******************/
		List<String> tokens = Searcher.tokenize(queryString);
		for(int i=0; i<k; i++) {
			if(tokens.size() == 0) break;
		}

		return null;
		/***********************************************/
	}

}
