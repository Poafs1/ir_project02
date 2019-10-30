//Name: 
//Section: 
//ID: 

import java.util.*;

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
		List<SearchResult> recvAllResults = new ArrayList<SearchResult>();
		List<String> tokens = Searcher.tokenize(queryString);
		Set<String> queryTerms = new HashSet<String>(tokens);

		for(int i=0; i<parse.size(); i++) {
			Set<String> intersecDT = new HashSet<String>(parse.get(i).getTokens());
			Set<String> unionDT = new HashSet<String>(parse.get(i).getTokens());
			intersecDT.retainAll(queryTerms);
			unionDT.addAll(queryTerms);
			double calJaccard = new Double(intersecDT.size()) / new Double(unionDT.size());
			SearchResult calAllResults = new SearchResult(parse.get(i), calJaccard);
			recvAllResults.add(calAllResults);
		}
		Collections.sort(recvAllResults);
		List<SearchResult> output = new ArrayList<SearchResult>();
		for(int i=0; i<10; i++) {
			output.add(recvAllResults.get(i));
		}
		return output;
		/***********************************************/
	}

}
