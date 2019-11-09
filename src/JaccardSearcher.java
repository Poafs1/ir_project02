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
//		get all documents from Searcher
		this.doc = this.documents;
		/***********************************************/
	}

	@Override
	public List<SearchResult> search(String queryString, int k) {
		/************* YOUR CODE HERE ******************/
//		Split word in queryString
		List<String> tokens = Searcher.tokenize(queryString);
//		Get only uniq tokens (terms)
		Set<String> queryTerms = new HashSet<String>(tokens);
		List<SearchResult> allResults = new ArrayList<SearchResult>();
		List<SearchResult> output = new ArrayList<SearchResult>();

//		Loop all documents
		for(int i=0; i<doc.size(); i++) {
//			Create two set of tokens from documents
			Set<String> intersecDocument = new HashSet<String>(doc.get(i).getTokens());
			Set<String> unionDocument = new HashSet<String>(doc.get(i).getTokens());
//			Intersec set with queryTerms
			intersecDocument.retainAll(queryTerms);
//			Union set with queryTerms
			unionDocument.addAll(queryTerms);
//			Compute a score
			double calculateScore = new Double(intersecDocument.size()) / new Double(unionDocument.size());
//			Make a result
			SearchResult getResult = new SearchResult(doc.get(i), calculateScore);
//			Store result in allResults list
			allResults.add(getResult);
		}

//		Sort all list of allResults
		Collections.sort(allResults);
		for(int i=0; i<k; i++) {
//			loop and get only k value then store in output
			output.add(allResults.get(i));
		}

		return output;
		/***********************************************/
	}

}
