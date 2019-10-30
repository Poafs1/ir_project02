//Name: Rattanavaree Muangamai - Pantita Wang - Prach Yothaprasert
//Section: 3
//ID: 6088092 - 6088219 - 6088234

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Collections;

public class JaccardSearcher extends Searcher{
	private List<Document> doc;
	public JaccardSearcher(String docFilename) {
		super(docFilename);
		/************* YOUR CODE HERE ******************/
//		this.documents = Searcher.parseDocumentFromFile(docFilename);
		this.doc = this.documents;
		/***********************************************/
	}

	@Override
	public List<SearchResult> search(String queryString, int k) {
		/************* YOUR CODE HERE ******************/
		List<String> tokens = Searcher.tokenize(queryString);
		Set<String> queryTerms = new HashSet<String>(tokens);
		List<SearchResult> allResults = new ArrayList<SearchResult>();
		List<SearchResult> output = new ArrayList<SearchResult>();

		for(int i=0; i<doc.size(); i++) {
			Set<String> intersecDocument = new HashSet<String>(doc.get(i).getTokens());
			Set<String> unionDocument = new HashSet<String>(doc.get(i).getTokens());
			intersecDocument.retainAll(queryTerms);
			unionDocument.addAll(queryTerms);
			double calculateScore = new Double(intersecDocument.size()) / new Double(unionDocument.size());
			SearchResult getResult = new SearchResult(doc.get(i), calculateScore);
			allResults.add(getResult);
		}

		Collections.sort(allResults);

		for(int i=0; i<k; i++) {
			output.add(allResults.get(i));
		}

		return output;
		/***********************************************/
	}

}
